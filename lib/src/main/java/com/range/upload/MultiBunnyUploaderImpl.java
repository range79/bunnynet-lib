package com.range.upload;

import com.range.config.MultiBunnyNetClient;
import com.range.dto.PutObjectRequest;
import com.range.dto.PutObjectResponse;
import com.range.enums.Region;
import com.range.exception.BunnyConnectionFailedException;
import com.range.exception.BunnyFileUploadFailedException;
import com.range.exception.BunnyInvalidCredentialsException;
import org.jspecify.annotations.NonNull;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

class MultiBunnyUploaderImpl implements MultiBunnyUploader {
    private final MultiBunnyNetClient multiBunnyNetClient;

    private final int connectionTimeOut;
    private final int connectionReadTimeOut;

    public MultiBunnyUploaderImpl(MultiBunnyNetClient multiBunnyNetClient, int connectionTimeOut, int connectionReadTimeOut) {
        this.multiBunnyNetClient = multiBunnyNetClient;
        this.connectionTimeOut = connectionTimeOut;
        this.connectionReadTimeOut = connectionReadTimeOut;
    }


    @Override
    public PutObjectResponse uploadFileBunny(PutObjectRequest putObjectRequest, String storageZone, Region storageRegion) throws BunnyFileUploadFailedException {

        HttpURLConnection connection;
        connection = getHttpURLConnection(putObjectRequest, storageZone, storageRegion);


        try (InputStream inputStream = putObjectRequest.getInputStream(); BufferedOutputStream outputStream = new BufferedOutputStream(connection.getOutputStream())) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new BunnyFileUploadFailedException("Failed Upload File To BunnyCDN", e);
        }

        int statusCode;
        try {
            statusCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (statusCode == 401) {
            throw new BunnyInvalidCredentialsException("Invalid credentials error code:401");
        }
        if (statusCode != 200 && statusCode != 201) {
            throw new BunnyFileUploadFailedException("BunnyCDN upload failed: HTTP " + statusCode);
        }

        String fileUrl = "https://" + storageZone + ".b-cdn.net/" + putObjectRequest.getKey();
        return new PutObjectResponse(storageZone, putObjectRequest.getKey(), fileUrl);
    }

    private @NonNull HttpURLConnection getHttpURLConnection(@NonNull PutObjectRequest putObjectRequest, @NonNull String storageZone, @NonNull Region storageRegion) {

        String uploadUrl = storageRegion.getEndpoint() + "/" + storageZone + "/" + putObjectRequest.getKey();

        try {
            URL url = new URL(uploadUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("AccessKey", multiBunnyNetClient.getApiKey());
            connection.setRequestProperty("Content-Type", putObjectRequest.getContentType());
            connection.setDoOutput(true);
            connection.setConnectTimeout(connectionTimeOut);
            connection.setReadTimeout(connectionReadTimeOut);

            Map<String, String> metadata = putObjectRequest.getMetadata();
            if (metadata != null && !metadata.isEmpty()) {
                for (Map.Entry<String, String> entry : metadata.entrySet()) {
                    connection.setRequestProperty("Meta-" + entry.getKey(), entry.getValue());
                }
            }

            return connection;

        } catch (IOException e) {
            throw new BunnyConnectionFailedException("Failed to open connection to URL: " + uploadUrl, e);
        }
    }


    @Override
    public PutObjectResponse uploadFileBunnyWithDefaultRegion(PutObjectRequest putObjectRequest, String storageZoneName) {
        try {
            return uploadFileBunny(putObjectRequest, storageZoneName, Region.FRANKFURT_DE);
        } catch (BunnyFileUploadFailedException ex) {
            throw new BunnyFileUploadFailedException("Upload failed", ex);
        }
    }
}
