# Use a base image with JDK

FROM maven:3.9.6-eclipse-temurin-17 AS builder
workdir /build
copy . .
run mvn clean package -DskipTests


FROM openjdk:17-jdk-slim

LABEL maintainer="rosh"
LABEL version="1.0"
LABEL description="to run spring boot app"

RUN addgroup --system javauser && adduser --system --ingroup javauser javauser
USER javauser
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
ENTRYPOINT ["java", "-jar","app.jar"]
