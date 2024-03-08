FROM openjdk:17.0-slim
WORKDIR /app
RUN mkdir log
RUN mkdir scouter

COPY ./*-SNAPSHOT.jar ./app.jar
COPY ./scouter/agent.java ./scouter

VOLUME /app/log

EXPOSE 8080

ENTRYPOINT [ \
   "java", \
   "-jar", \
   "-javaagent:./scouter/scouter.agent.jar", \
   "-Dscouter.config=./scouter/conf/scouter.conf", \
   "-Djava.security.egd=file:/dev/./urandom", \
   "-Dsun.net.inetaddr.ttl=0", \
   "-Dcom.amazonaws.sdk.disableEc2Metadata=true", \
   "-Djava.net.preferIPv4Stack=true", \
   "-Xms1024m", \
   "-Xmx1024m", \
   "app.jar" \
]