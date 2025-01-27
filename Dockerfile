FROM openjdk:22-jdk-slim

WORKDIR /app

USER root
RUN apt-get update && apt-get install -y \
    apt-utils \
    python3 \
    python3-pip \
    python3-venv \
    ffmpeg \
    git  \
    && rm -rf /var/lib/apt/lists/*

RUN pip3 install -U yt-dlp --break-system-packages

RUN mkdir -p /mnt/c/Users/yaman/OneDrive/Desktop/mp4-to-mp3-java/mp3/

COPY target/converter-1.0-SNAPSHOT.jar /app/converter-1.0-SNAPSHOT.jar

ENTRYPOINT ["java","-jar", "/app/converter-1.0-SNAPSHOT.jar"]