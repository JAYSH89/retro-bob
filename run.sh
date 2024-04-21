#!/bin/bash

./gradlew build

if [ $? -ne 0 ]; then
    echo "Gradle build failed. Exiting."
    exit 1
fi

docker-compose build
docker-compose up
