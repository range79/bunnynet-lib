package com.range.single.downloader;

import com.range.single.config.SingleBunnyNetClient;

public interface SingleBunnyDownloader {
   static SingleBunnyDownloader create(SingleBunnyNetClient singleBunnyNetClient){
       return new SingleBunnyDownloaderImpl(singleBunnyNetClient);
   }

}
