
# MP4 to MP3 CONVERTER - Java Application in Docker

This project is a Java-based application that downloads a Youtube video as an MP4 file using``yt-dlp`` and then converts it to MP3 using ``ffmpeg``. The application is packaged as a ``.jar`` file and runs inside a Docker container.


## Installation For Docker

Clone the repository to your local machine

```bash
  git clone https://github.com/yamanNuman/mp4-to-mp3-java.git
  cd mp4-to-mp3-java
```
 Build the Project

 ```bash
  mvn clean package
```

You can build the Docker image to run this Java application inside a container.

 ```bash
  docker build -t mp4-to-mp3-java .
```

Run the Docker container

 ```bash
  docker run -e SOURCE_PATH="/app/data/mp4" -e TARGET_PATH="/app/data/mp3" -e VIDEO_URL="youtube-link" mp4-to-mp3-java
```

## How It Works ?

- The application uses ``yt-dlp`` to download the video in MP4 format from the given Youtube URL (VIDEO_URL)

- After the video is downloaded, the application users ``ffmpeg`` to convert the MP4 file to MP3 format, saving it in the specified TARGET_PATH.

- You can customize the source, target paths, and YouTube URL by settings environment variables when running the Docker container.

