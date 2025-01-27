package com.ymnn;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        DownloaderAndConverter ytMp4toMp3 = new DownloaderAndConverter();
        ytMp4toMp3.downloadAndConvert();
    }
}
