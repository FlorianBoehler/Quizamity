# === BUILD STAGE ===
# Use Maven with JDK 21 to build the project
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src
COPY pgds.properties /app/pgds.properties

# Build the project and skip tests
RUN mvn clean package -DskipTests

# === RUNTIME STAGE ===
# Use a lightweight JDK 21 image for running the app
FROM eclipse-temurin:21-jdk

# Download Payara Micro server
RUN curl -L -o /payara-micro.jar https://repo1.maven.org/maven2/fish/payara/extras/payara-micro/6.2024.2/payara-micro-6.2024.2.jar

# Set working directory
WORKDIR /app

# Copy the generated WAR file from the build stage
COPY --from=build /app/target/quizamity-1.0-SNAPSHOT.war .

# Start the application with Payara Micro on port 8080
CMD ["java", "-jar", "/payara-micro.jar",
    "--deploy", "/app/quizamity-1.0-SNAPSHOT.war",
    "--port", "8080", "--nohazelcast",
    "--datasourceconfiguration", "pgds.properties"]
