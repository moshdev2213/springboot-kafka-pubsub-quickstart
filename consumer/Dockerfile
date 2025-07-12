# Use an official OpenJDK image as a base
FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

# Copy the built jar file into the container
COPY target/consumer-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 9229

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]