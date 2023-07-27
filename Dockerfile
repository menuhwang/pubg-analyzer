FROM openjdk:11.0-slim
WORKDIR /app
COPY ./*-SNAPSHOT.jar ./app.jar
RUN mkdir log

VOLUME /app/log

EXPOSE 8080

ENTRYPOINT [                                                \
   "java",                                                 \
   "-jar",                                                 \
   "-Djava.security.egd=file:/dev/./urandom",              \
   "-Dsun.net.inetaddr.ttl=0",                             \
   "-Dcom.amazonaws.sdk.disableEc2Metadata=true",          \
   "-Djava.net.preferIPv4Stack=true",          \
   "-Xms1024m",               \
   "-Xmx1024m",               \
   "app.jar"              \
]