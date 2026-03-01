package com.range.common.dto;

public class PutObjectResponse {
    private final String bucket;
    private final String key;
    private final String url;

    public PutObjectResponse(String bucket, String key, String url) {
        this.bucket = bucket;
        this.key = key;
        this.url = url;
    }

    public String getBucket() { return bucket; }
    public String getKey() { return key; }
    public String getUrl() { return url; }
}
