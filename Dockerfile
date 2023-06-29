FROM --platform=linux/amd64 openjdk:17-alpine

WORKDIR /app

ADD launcher/build/libs/tech-challenge.jar tech-challenge.jar

RUN /bin/sh -c 'touch /app/tech-challenge.jar'

CMD ["java", "-jar", "tech-challenge.jar"]