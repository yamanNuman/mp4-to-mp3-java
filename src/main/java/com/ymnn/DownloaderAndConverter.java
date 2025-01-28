package com.ymnn;

import java.io.*;

public class DownloaderAndConverter {
    /*
        Windows System
        public static String SOURCE_PATH = "C:\\Users\\username\\.....\\mp4-to-mp3-java\\mp4\\;
        public static String TARGET_PATH = "C:\\Users\\username\\.....\\mp4-to-mp3-java\\mp3\\;
        public static String VIDEO_URL = "youtube-link";
     */
    /*
    Linux System
        public static String SOURCE_PATH= "/home/username/mp4-to-mp3-java/mp4/";
        public static String TARGET_PATH = "/home/username/mp4-to-mp3-java/mp3/";
        public static String VIDEO_URL = "youtube-link";
     */

    //Docker Env
    String SOURCE_PATH = System.getenv("SOURCE_PATH");
    String TARGET_PATH = System.getenv("TARGET_PATH");
    String VIDEO_URL = System.getenv("VIDEO_URL");

    public void downloadAndConvert() {
        if(downloadVideo()) {
            convertToMp3();
        }
    }

    private boolean downloadVideo() {
        String outputPath = SOURCE_PATH + "test.mp4";
        String[] command = {
              /*
                Linux -> yt-dlp
                Windows -> C:\\yt-dlp\\yt-dlp.exe
              */
                "yt-dlp",
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
              /*
                Linux -> ffmpeg
                Windows -> C:\\ffmpeg\\bin\\ffmpeg.exe
              */
                "ffmpeg",
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
