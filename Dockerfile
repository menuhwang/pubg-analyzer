FROM openjdk:11.0-slim
WORKDIR /app
COPY ./*-SNAPSHOT.jar ./app.jar
ADD https://truststore.pki.rds.amazonaws.com/global/global-bundle.pem /app
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
   "-Xms512m",               \
   "-Xmx512m",               \
   "app.jar"              \
]