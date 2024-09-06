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

COPY --from=builder ${DEPENDENCY}/libs/visualizer-1.jar /opt/visualizer/visualizer.jar

WORKDIR /opt/visualizer
CMD java -jar visualizer.jar