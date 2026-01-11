package com.range.single.downloader;

import com.range.common.dto.SingleDownloadObjectRequest;
import com.range.common.http.BunnyHttpClient;
import com.range.single.config.SingleBunnyNetConfig;

import java.io.InputStream;

class SingleBunnyDownloaderImpl implements SingleBunnyDownloader{
    private final SingleBunnyNetConfig singleBunnyNetConfig;
    private final BunnyHttpClient bunnyHttpClient;
    public SingleBunnyDownloaderImpl(SingleBunnyNetConfig singleBunnyNetConfig, int connectionTimeout, int connectionReadTimeout){
        this.singleBunnyNetConfig = singleBunnyNetConfig;
        bunnyHttpClient=new BunnyHttpClient(singleBunnyNetConfig.apiKey(),connectionTimeout,connectionReadTimeout);
    }

    @Override
    public InputStream download(SingleDownloadObjectRequest singleDownloadObjectRequest) {
return null;
//        bunnyHttpClient.
    }
}
