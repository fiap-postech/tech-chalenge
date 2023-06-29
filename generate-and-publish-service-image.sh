#!/bin/sh

echo "building jar file..."
./gradlew :launcher:build

echo "generating image..."
docker build -t tech-challenge-service .

VERSION=$(./gradlew :launcher:properties | sed -n '/^version/p' | sed "s/version: //")

echo "generating docker tag for version: $VERSION"
docker tag tech-challenge-service "fiapsoat2grupo13/tech-challenge-service:$VERSION"

# shellcheck disable=SC2039
source docker-hub-env.sh

echo "sing in into docker"
docker login -u "$DOCKER_HUB_USER" -p "$DOCKER_HUB_PWD"

echo "pushing image"
docker push "fiapsoat2grupo13/tech-challenge-service:$VERSION"