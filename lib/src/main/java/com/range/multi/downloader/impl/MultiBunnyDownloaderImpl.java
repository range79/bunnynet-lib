package com.range.multi.downloader.impl;

import com.range.common.download.AbstractBunnyDownloader;
import com.range.common.enums.Region;
import com.range.multi.config.MultiBunnyNetConfig;
import com.range.multi.downloader.MultiBunnyDownloader;

import java.io.InputStream;

public class MultiBunnyDownloaderImpl extends AbstractBunnyDownloader implements MultiBunnyDownloader {
    private MultiBunnyNetConfig multiBunnyNetConfig;
    private int connectionTimeout;
    private int readTimeout;

    public MultiBunnyDownloaderImpl(
            MultiBunnyNetConfig multiBunnyNetConfig,
            int connectionTimeout,
            int readTimeout
    ) {

        this.multiBunnyNetConfig = multiBunnyNetConfig;
        this.readTimeout = readTimeout;
        this.connectionTimeout = connectionTimeout;
    }


    @Override
    public InputStream download(String storageZoneName, String key, Region storageRegion) {

    }
}
