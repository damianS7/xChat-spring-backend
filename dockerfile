FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/app.jar"]


# docker build -t springapp .
# docker run springapp
