#!/bin/sh

# Replace environment variables in the datasource creation script
envsubst < /app/create-datasource.asadmin > /app/bootcmd.asadmin

# Start Payara Micro WITHOUT deploying the app yet, but WITH the datasource config
java -jar /payara-micro.jar \
     --addlibs /postgresql.jar \
     --postbootcommandfile /app/bootcmd.asadmin \
     --port 8080 \
     --nohazelcast &

# Save Payara process ID
PID=$!

# Wait for Payara Micro to fully start up
echo "Waiting for Payara Micro to start..."
sleep 10

# Deploy the WAR file via the Admin REST API after datasource is ready
echo "Deploying application..."
curl -X POST -H "Content-Type: application/octet-stream" \
     --data-binary @/app/quizamity.war \
     http://localhost:8080/__admin/deploy

# Wait for the Payara process so container doesn't exit
wait $PID
