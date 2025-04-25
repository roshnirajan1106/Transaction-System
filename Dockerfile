# Use a base image with JDK

FROM openjdk:17-jdk-slim

LABEL maintainer="rosh"
LABEL version="1.0"
LABEL description="to run spring boot app"

RUN addgroup --system javauser && adduser --system --ingroup javauser javauser
USER javauser
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar","app.jar"]
