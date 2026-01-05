package com.range.single.downloader;

import com.range.single.config.SingleBunnyNetClient;

public class SingleBunnyDownloaderImpl implements SingleBunnyDownloader{
    private final SingleBunnyNetClient singleBunnyNetClient;
    public SingleBunnyDownloaderImpl(SingleBunnyNetClient singleBunnyNetClient){
        this.singleBunnyNetClient=singleBunnyNetClient;
    }
}
