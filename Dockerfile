FROM icr.io/appcafe/open-liberty:kernel-slim-java21-openj9-ubi-minimal
# :COPY --chown=1001:0 postgresql-*.jar /opt/ol/wlp/lib/
COPY --chown=1001:0 target/quizamity-1.0-SNAPSHOT.war /config/dropins/
COPY --chown=1001:0 server.xml /config