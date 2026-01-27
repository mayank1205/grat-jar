# Use a lightweight JDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /notes

# Copy the built JAR file into the container
COPY target/notes-0.0.1-SNAPSHOT.jar notes.jar

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","notes.jar"]
