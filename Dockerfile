FROM eclipse-temurin:21
MAINTAINER jaysh.com
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
