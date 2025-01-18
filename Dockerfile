# Stage 1: Build with Maven
FROM maven:latest AS build
LABEL authors="SugarDaddy"
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

# Stage 2: Run with JDK 21
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar /app/glowandrise.jar
CMD ["java", "-jar", "glowandrise.jar", "com.glowandrise.glowandrise_backend.GlowandriseBackendApplication"]