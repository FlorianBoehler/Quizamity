FROM icr.io/appcafe/open-liberty
# COPY --chown=1001:0 postgresql-*.jar /opt/ol/wlp/lib/
COPY --chown=1001:0 target/quizamity.war /config/dropins/
COPY --chown=1001:0 server.xml /config