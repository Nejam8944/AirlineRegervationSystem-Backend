# Stage 1: Build the application
FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:17-jdk-slim
COPY --from=build target/ars.jar ars.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ars.jar"]
