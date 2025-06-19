# Use a lightweight Java runtime image
FROM eclipse-temurin:17-jre-jammy

# Define build argument for the jar file name (with default)
ARG JAR_FILE=camunda-animal-app-1.0.0.jar

# Copy the Spring Boot jar into the container
COPY target/${JAR_FILE} /animal-app.jar

# Expose the port your app runs on (default 8080)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/animal-app.jar"]
