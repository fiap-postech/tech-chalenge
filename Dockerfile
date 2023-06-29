FROM --platform=linux/amd64 openjdk:17-alpine

ARG VERSION

WORKDIR /app

ADD launcher/build/libs/launcher-${VERSION}.jar tech-challenge.jar

RUN /bin/sh -c 'touch /app/tech-challenge.jar'

CMD ["java", "-jar", "tech-challenge.jar"]