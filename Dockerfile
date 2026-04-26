# Use official OpenJDK image
FROM eclipse-temurin:17-jdk

# Set working directory inside container
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build the application inside container
RUN apt-get update && apt-get install -y maven
RUN mvn clean package -DskipTests

# Run the generated JAR file
COPY target/*.jar app.jar

# Expose port (Spring Boot default)
EXPOSE 8080

# Start application
ENTRYPOINT ["java", "-jar", "app.jar"]