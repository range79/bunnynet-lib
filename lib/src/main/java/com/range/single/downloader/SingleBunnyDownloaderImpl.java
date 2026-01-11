package com.range.single.downloader;

import com.range.common.dto.SingleDownloadObjectRequest;
import com.range.common.http.BunnyHttpClient;
import com.range.single.config.SingleBunnyNetClient;

import java.io.InputStream;

class SingleBunnyDownloaderImpl implements SingleBunnyDownloader{
    private final SingleBunnyNetClient singleBunnyNetClient;
    private final BunnyHttpClient bunnyHttpClient;
    public SingleBunnyDownloaderImpl(SingleBunnyNetClient singleBunnyNetClient,int connectionTimeout,int connectionReadTimeout){
        this.singleBunnyNetClient=singleBunnyNetClient;
        bunnyHttpClient=new BunnyHttpClient(singleBunnyNetClient.apiKey(),connectionTimeout,connectionReadTimeout);
    }

    @Override
    public InputStream download(SingleDownloadObjectRequest singleDownloadObjectRequest) {
return null;
//        bunnyHttpClient.
    }
}
