package com.range.multi.downloader;

import com.range.common.enums.Region;
import com.range.multi.config.MultiBunnyNetConfig;
import com.range.multi.downloader.impl.MultiBunnyDownloaderImpl;

import java.io.InputStream;

public interface MultiBunnyDownloader
{
    static MultiBunnyDownloader create(MultiBunnyNetConfig multiBunnyNetConfig,int connectionTimeout,int readTimeout){
        return new MultiBunnyDownloaderImpl(multiBunnyNetConfig,connectionTimeout,readTimeout);
    }
    static MultiBunnyDownloader create(MultiBunnyNetConfig multiBunnyNetConfig){
        return new MultiBunnyDownloaderImpl(multiBunnyNetConfig,15_000,45_000);
    }
    InputStream download(String storageZoneName, String key, Region storageRegion);

}
