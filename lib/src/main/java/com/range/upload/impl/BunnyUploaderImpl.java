package com.range.upload.impl;

import com.range.config.BunnyNetClient;
import com.range.dto.PutObjectRequest;
import com.range.dto.PutObjectResponse;
import com.range.enums.Region;
import com.range.exception.BunnyConnectionFailedException;
import com.range.exception.BunnyFileUploadFailedException;
import com.range.exception.BunnyInvalidCredentialsException;
import com.range.upload.BunnyUploader;
import org.jspecify.annotations.NonNull;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

class BunnyUploaderImpl implements BunnyUploader {
    private final BunnyNetClient bunnyNetClient;

    private final int connectionTimeOut;
    private final int connectionReadTimeOut;
    public BunnyUploaderImpl(BunnyNetClient bunnyNetClient, int connectionTimeOut, int connectionReadTimeOut) {
        this.bunnyNetClient = bunnyNetClient;
        this.connectionTimeOut = connectionTimeOut;
        this.connectionReadTimeOut = connectionReadTimeOut;
    }
    public BunnyUploaderImpl(BunnyNetClient bunnyNetClient) {
        this.bunnyNetClient = bunnyNetClient;
        connectionTimeOut=15_000;
        connectionReadTimeOut =60_000;
    }

    @Override
    public PutObjectResponse uploadFileBunny(
            PutObjectRequest putObjectRequest,
            String storageZone,
            Region storageRegion
    )throws BunnyFileUploadFailedException  {

        HttpURLConnection connection;
        try {
            connection = getHttpURLConnection(putObjectRequest, storageZone, storageRegion);
        } catch (IOException e) {
            throw new BunnyConnectionFailedException(e);
        }


        try (InputStream inputStream = putObjectRequest.getInputStream();
             BufferedOutputStream outputStream =
                     new BufferedOutputStream(connection.getOutputStream())) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new BunnyFileUploadFailedException("Failed Upload File To BunnyCDN",e);
        }

        int statusCode;
        try {
            statusCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (statusCode==401){
            throw new  BunnyInvalidCredentialsException("Invalid credentials error code:401");
        }
        if (statusCode != 200 && statusCode != 201) {
            throw new BunnyFileUploadFailedException("BunnyCDN upload failed: HTTP " + statusCode);
        }


        String fileUrl = "https://" + storageZone + ".b-cdn.net/" + putObjectRequest.getKey();

        return new PutObjectResponse(
                storageZone,
                putObjectRequest.getKey(),
                fileUrl
        );
    }

    private @NonNull HttpURLConnection getHttpURLConnection(PutObjectRequest putObjectRequest, String storageZone, Region storageRegion) throws IOException {
        String uploadUrl = storageRegion.getEndpoint() +
                "/" + storageZone +
                "/" + putObjectRequest.getKey();

        URL url = new URL(uploadUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("PUT");
        connection.setRequestProperty("AccessKey", bunnyNetClient.getApiKey());
        connection.setRequestProperty("Content-Type", putObjectRequest.getContentType());
        connection.setDoOutput(true);
        connection.setConnectTimeout(connectionTimeOut);
        connection.setReadTimeout(connectionReadTimeOut);


        Map<String, String> metadata = putObjectRequest.getMetadata();
        if (metadata != null) {
            for (Map.Entry<String, String> entry : metadata.entrySet()) {
                connection.setRequestProperty("Meta-" + entry.getKey(), entry.getValue());
            }
        }
        return connection;
    }

    @Override
    public PutObjectResponse uploadFileBunnyWithDefaultRegion(
            PutObjectRequest putObjectRequest,
            String storageZoneName
    )  {
        try {
            return uploadFileBunny(putObjectRequest, storageZoneName, Region.FRANKFURT_DE);
        } catch (BunnyFileUploadFailedException ex) {
            throw new BunnyFileUploadFailedException("Upload failed", ex);
        }
    }
}
