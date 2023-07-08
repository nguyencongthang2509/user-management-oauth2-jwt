FROM openjdk:17-jdk-alpine
ARG JAR_FILE=build/libs/server-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} user_management.jar
ENTRYPOINT ["java","-jar","/user_management.jar"]