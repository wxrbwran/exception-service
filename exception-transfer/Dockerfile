FROM openjdk:13-alpine3.9
ARG PKG
ARG ENV
COPY ${PKG} app.jar
ENTRYPOINT [ "java", "-jar", "app.jar", "--spring.cloud.config.profile=${ENV}"]