# Use an official Java runtime as a base image
FROM openjdk:20-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the host into the container
COPY target/app-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port the app will run on (e.g., 8080)
EXPOSE 8081

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
