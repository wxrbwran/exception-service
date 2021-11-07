FROM openjdk:8-jdk-alpine
ARG PKG
COPY ${PKG} app.jar
ENTRYPOINT [ "java", "-jar", "app.jar" ]