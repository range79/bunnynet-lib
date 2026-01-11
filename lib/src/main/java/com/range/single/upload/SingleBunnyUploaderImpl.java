package com.range.single.upload;

import com.range.common.exception.BunnyFileUploadFailedException;
import com.range.common.exception.BunnyInvalidCredentialsException;
import com.range.common.http.BunnyHttpClient;
import com.range.single.config.SingleBunnyNetClient;
import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;

class SingleBunnyUploaderImpl implements SingleBunnyUploader {
    private final SingleBunnyNetClient singleBunnyNetClient;
    private final BunnyHttpClient httpClient;


    public SingleBunnyUploaderImpl(SingleBunnyNetClient singleBunnyNetClient, int connectionTimeout, int readTimeout) {
        this.singleBunnyNetClient = singleBunnyNetClient;
        httpClient = new BunnyHttpClient(singleBunnyNetClient.apiKey(), connectionTimeout, readTimeout);
    }

    @Override
    public PutObjectResponse uploadFile(PutObjectRequest putObjectRequest) {
        String url = singleBunnyNetClient.region().getEndpoint() + "/"
                + singleBunnyNetClient.storageZone() + "/" + putObjectRequest.getKey();
        var connection = httpClient.createPutConnection(url, putObjectRequest.getContentType(), putObjectRequest.getMetadata());
        httpClient.uploadData(connection, putObjectRequest);
        int code = httpClient.getResponseCode(connection);
        if (code == 401) throw new BunnyInvalidCredentialsException("Invalid AccessKey, region hostname, or file passed in a non raw binary format.");
        if (code != 200 && code != 201) throw new BunnyFileUploadFailedException("BunnyCDN upload failed: HTTP " + code);
        return new PutObjectResponse(singleBunnyNetClient.storageZone(),
                putObjectRequest.getKey(),
                "https://"+singleBunnyNetClient.storageZone()+".b-cdn.net/" + putObjectRequest.getKey());
    }
}
