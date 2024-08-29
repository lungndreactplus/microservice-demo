# Use the official OpenJDK image as a base image
FROM openjdk:21

# Set the working directory
WORKDIR /app

# Copy the built jar file
COPY build/libs/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
