FROM --platform=linux/amd64 gradle:8.1.1-jdk17 AS builder

WORKDIR /build

COPY adapter/driven/mysql/build.gradle ./adapter/driven/mysql/build.gradle
COPY adapter/driven/redis/build.gradle ./adapter/driven/redis/build.gradle
COPY adapter/driver/rest/build.gradle ./adapter/driver/rest/build.gradle
COPY application/build.gradle ./application/build.gradle
COPY commons/domain-common/build.gradle ./commons/domain-common/build.gradle
COPY commons/mapper-common/build.gradle ./commons/mapper-common/build.gradle
COPY commons/rest-common/build.gradle ./commons/rest-common/build.gradle
COPY launcher/build.gradle ./launcher/build.gradle
COPY build.gradle .
COPY settings.gradle .

RUN gradle clean build --no-daemon > /dev/null 2>&1 || true

COPY . .

RUN gradle clean build --no-daemon




FROM --platform=linux/amd64 openjdk:17-alpine

WORKDIR /app

COPY --from=builder /build/launcher/build/libs/launcher-1.0.0.jar ./tech-challenge.jar

RUN /bin/sh -c 'touch /app/tech-challenge.jar'

CMD ["java", "-jar", "tech-challenge.jar"]