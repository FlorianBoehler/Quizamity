# === BUILD STAGE ===
FROM maven:3.9.6-eclipse-temurin-21 AS build

WORKDIR /app

COPY ./backend/pom.xml .
COPY src ./backend/src

RUN mvn clean package -DskipTests

# === RUNTIME STAGE ===
FROM eclipse-temurin:21-jdk

# Download Payara Micro
RUN curl -L -o /payara-micro.jar https://repo1.maven.org/maven2/fish/payara/extras/payara-micro/6.2024.2/payara-micro-6.2024.2.jar

# Set working directory
WORKDIR /app

# Copy WAR from build stage
COPY --from=build /app/target/quizamity-1.0-SNAPSHOT.war .
COPY postboot.asadmin .

# Start the application with Payara Micro with JNDI data source config on port 8080
CMD ["/bin/sh", "-c", "java \
    -DDB_HOST=${DB_HOST} \
    -DDB_PORT=${DB_PORT} \
    -DDB_NAME=${DB_NAME} \
    -DDB_USER=${DB_USER} \
    -DDB_PASSWORD=${DB_PASSWORD} \
    -jar /payara-micro.jar \
    --deploy /app/quizamity-1.0-SNAPSHOT.war \
    --port 8080 \
    --postbootcommandfile /app/postboot.asadmin \
    --nohazelcast"]
