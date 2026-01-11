package com.range.single.downloader;

import com.range.common.dto.SingleDownloadObjectRequest;
import com.range.single.config.SingleBunnyNetConfig;

import java.io.InputStream;

public interface SingleBunnyDownloader {
   static SingleBunnyDownloader create(SingleBunnyNetConfig singleBunnyNetConfig,
                                       int connectionTimeout, int readTimeout){
       return new SingleBunnyDownloaderImpl(singleBunnyNetConfig,connectionTimeout,readTimeout);
   }
   static SingleBunnyDownloader create(SingleBunnyNetConfig singleBunnyNetConfig){
       return new SingleBunnyDownloaderImpl(singleBunnyNetConfig,15_000,45_000);
   }
   InputStream download(SingleDownloadObjectRequest singleDownloadObjectRequest);

}
