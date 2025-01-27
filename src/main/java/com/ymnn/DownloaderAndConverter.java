package com.ymnn;

import java.io.*;

public class DownloaderAndConverter {
    //Windows
    public static String SOURCE_PATH = "C:\\Users\\yaman\\OneDrive\\Desktop\\mp4-to-mp3-java\\mp4\\";
    public static String TARGET_PATH = "C:\\Users\\yaman\\OneDrive\\Desktop\\mp4-to-mp3-java\\mp3\\";
    //Linux
//  public static String SOURCE_PATH= "/home/gengar/java-project/converter/mp4/";
//  public static String TARGET_PATH = "/home/gengar/java-project/converter/mp3/";
    public static String VIDEO_URL = "https://www.youtube.com/watch?v=KRlNvOSmcKI&ab_channel=righteous-hak%C5%9Finas";

    public void downloadAndConvert() {
        if(downloadVideo()) {
            convertToMp3();
        }
    }

    private boolean downloadVideo() {
        String outputPath = SOURCE_PATH + "test.mp4";
        String[] command = {
                //yt-dlp - Linux
                "C:\\yt-dlp\\yt-dlp.exe",
                //"bestvideo+bestaudio"
                "-f","best",
                "--merge-output-format","mp4",
                "-o", outputPath,
                VIDEO_URL
        };
        return executeProcess(command);
    }

    private boolean convertToMp3() {
        String inputPath = SOURCE_PATH + "test.mp4";
        String outputPath = TARGET_PATH + "test.mp3";

        String[] command = {
                //ffmpeg - Linux
                "C:\\ffmpeg\\bin\\ffmpeg.exe",
                "-i", inputPath,
                "-vn",
                "-acodec","libmp3lame",
                "-ar","44100",
                "-ac","2",
                "-ab","192k",
                outputPath
        };
        return executeProcess(command);
    }

    private boolean executeProcess(String[] command) {
        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();

            try(BufferedReader reader =  new BufferedReader(new InputStreamReader(process.getInputStream()))){
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            int exitCode = process.waitFor();
            if(exitCode != 0) {
                System.out.println("Process exited with code : " + exitCode);
                return false;
            } else {
                System.out.println("Process exited with code : " + exitCode);
                return true;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
