# FROM icr.io/appcafe/open-liberty:kernel-slim-java21-openj9-ubi-minimal
FROM icr.io/appcafe/open-liberty:full-java21-openj9-ubi-minimal
RUN features.sh --acceptLicense jakartaee-10.0 microProfile-7.0 jdbc-4.3

# :COPY --chown=1001:0 postgresql-*.jar /opt/ol/wlp/lib/
COPY --chown=1001:0 /config/resources/postgresql-*.jar /config/resources/

COPY --chown=1001:0 target/quizamity-1.0-SNAPSHOT.war /config/dropins/
COPY --chown=1001:0 server.xml /config