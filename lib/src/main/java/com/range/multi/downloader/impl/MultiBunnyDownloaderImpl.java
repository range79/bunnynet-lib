package com.range.multi.downloader.impl;

import com.range.common.download.AbstractBunnyDownloader;
import com.range.common.enums.Region;
import com.range.common.http.BunnyHttpClient;
import com.range.multi.config.MultiBunnyNetConfig;
import com.range.multi.downloader.MultiBunnyDownloader;

import java.io.InputStream;

public class MultiBunnyDownloaderImpl extends AbstractBunnyDownloader implements MultiBunnyDownloader {

    private final BunnyHttpClient httpClient;
    public MultiBunnyDownloaderImpl(
            MultiBunnyNetConfig multiBunnyNetConfig,
            int connectionTimeout,
            int readTimeout
    ) {

        this.httpClient=new BunnyHttpClient(multiBunnyNetConfig.apiKey(),connectionTimeout,readTimeout);

    }


    @Override
    public InputStream download(String storageZoneName, String key, Region storageRegion) {

        if (storageZoneName == null || storageZoneName.isBlank()) {
            throw new IllegalArgumentException("Storage zone cannot be null or empty");
        }

        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }

        if (storageRegion == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }

        String url = String.format(
                "%s/%s/%s",
                storageRegion.getEndpoint(),
                storageZoneName,
                key
        );

        return httpClient.downloadAsStream(url);
    }

}
