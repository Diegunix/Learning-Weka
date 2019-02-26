FROM java:8-jdk-alpine

COPY ./target/learning-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch learning-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","learning-0.0.1-SNAPSHOT.jar"]  