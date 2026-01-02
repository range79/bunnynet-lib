package com.range.upload.impl;

import com.range.config.BunnyNetClient;
import com.range.dto.PutObjectRequest;
import com.range.dto.PutObjectResponse;
import com.range.upload.BunnyUploader;

public class BunnyUploaderImpl implements BunnyUploader {
    private final BunnyNetClient bunnyNetClient;
    public BunnyUploaderImpl(BunnyNetClient bunnyNetClient){
        this.bunnyNetClient = bunnyNetClient;
    }


    @Override
    public PutObjectResponse uploadFileBunny(PutObjectRequest putObjectRequest) {

    }
}
