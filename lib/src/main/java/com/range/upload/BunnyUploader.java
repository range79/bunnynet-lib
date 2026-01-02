package com.range.upload;

import com.range.dto.PutObjectRequest;
import com.range.dto.PutObjectResponse;

public interface BunnyUploader {
    PutObjectResponse uploadFileBunny(PutObjectRequest putObjectRequest);

}
