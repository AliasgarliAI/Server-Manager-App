#FROM alpine:latest
#RUN apk add --no-cache openjdk11
#COPY build/libs/server-0.0.1-SNAPSHOT.jar /app/
#WORKDIR /app/
#ENTRYPOINT ["java"]
#CMD ["-jar", "/app/server-0.0.1-SNAPSHOT.jar"]


FROM openjdk:11
WORKDIR /app
COPY build/libs/server-0.0.1-SNAPSHOT.jar /app
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk
ENV PATH=$PATH:$JAVA_HOME/bin
# Copy the JAR file from the host to the image
COPY build/libs/server-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar","/app/server-0.0.1-SNAPSHOT.jar"]