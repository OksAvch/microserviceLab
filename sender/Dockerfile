ARG GRADLE_IMG=gradle:jdk21
ARG JAVA_IMG=openjdk:21-jdk-slim

FROM $GRADLE_IMG AS builder
WORKDIR /workdir
COPY build.gradle .
COPY settings.gradle .
COPY gradle.properties .
COPY src src

ENV GRADLE_OPTS -Dorg.gradle.daemon=false

RUN gradle build -x check

FROM $JAVA_IMG
ARG DEPENDENCY=/workdir/build

COPY --from=builder ${DEPENDENCY}/libs/sender-1.jar /opt/sender/sender.jar

WORKDIR /opt/sender
CMD java -jar sender.jar