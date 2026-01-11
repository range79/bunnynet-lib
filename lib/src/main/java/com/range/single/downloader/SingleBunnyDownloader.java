package com.range.single.downloader;

import com.range.common.dto.SingleDownloadObjectRequest;
import com.range.single.config.SingleBunnyNetClient;
import com.range.single.upload.SingleBunnyUploader;

import java.io.InputStream;

public interface SingleBunnyDownloader {
   static SingleBunnyDownloader create(SingleBunnyNetClient singleBunnyNetClient,
                                       int connectionTimeout,int readTimeout){
       return new SingleBunnyDownloaderImpl(singleBunnyNetClient,connectionTimeout,readTimeout);
   }
   static SingleBunnyDownloader create(SingleBunnyNetClient singleBunnyNetClient){
       return new SingleBunnyDownloaderImpl(singleBunnyNetClient,15_000,45_000);
   }
   InputStream download(SingleDownloadObjectRequest singleDownloadObjectRequest);

}
