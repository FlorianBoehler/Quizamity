#!/bin/sh
envsubst < /app/create-datasource.asadmin > /app/bootcmd.asadmin

exec java -jar /payara-micro.jar \
     --deploy /app/quizamity.war \
     --addlibs /postgresql.jar \
     --postbootcommandfile /app/bootcmd.asadmin \
     --port 8080 \
     --nohazelcast
