FROM openjdk:8-jdk
MAINTAINER Mustafa Haluk Tiras (haluktiras@gmail.com)
RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml /usr/local/service/pom.xml
COPY src /usr/local/service/src
WORKDIR /usr/local/service
RUN mvn package
CMD ["java","-jar","target/tictactoe-1.0-SNAPSHOT-jar-with-dependencies.jar"]