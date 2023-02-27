# IMAGE pour VM CLOUD
#FROM openjdk:17.0.1-oraclelinux8@sha256:8a99742c7c38a7b0826c8a47cacbca18af5c3a60e6eba18e75d6a8c28b9fa9b4

# IMAGE pour LOCAL
FROM openjdk:17.0.1-jdk-oraclelinux8

MAINTAINER jchaysinh
COPY /home/runner/.m2/repository/com/bcefit/movie-manager-iam/1.0-SNAPSHOT/movie-manager-iam-1.0-SNAPSHOT.jar docker-ARM-Movie-App-IAM-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/docker-ARM-Movie-App-IAM-0.0.1-SNAPSHOT.jar"]