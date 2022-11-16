FROM openjdk:8u121-jre-alpine

VOLUME /tmp

ARG JASYPT_ENCRYPTOR_PASSWORD
ARG SLS_ACCESS_ID
ARG SLS_ACCESS_SECRET

ADD target/**.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
EXPOSE 8080

# Build a shell script because the ENTRYPOINT command doesn't like using ENV
RUN echo -e "#!/bin/sh\njava ${JAVA_OPTS} -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom  -DCOMMON_SERVICE_IP=${COMMON_SERVICE_IP} -DSLS_ACCESS_ID=${SLS_ACCESS_ID} -DSLS_ACCESS_SECRET=${SLS_ACCESS_SECRET} -DJASYPT_ENCRYPTOR_PASSWORD=${JASYPT_ENCRYPTOR_PASSWORD} -Dsun.net.httpserver.nodelay=true -jar /app.jar" > ./entrypoint.sh
RUN chmod +x /entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]

