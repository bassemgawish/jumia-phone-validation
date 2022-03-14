FROM openjdk:8
EXPOSE 8080
ADD target/phoneValidation.jar phoneValidation.jar
ADD sample.db sample.db
# ARG WAR_FILE=target/phoneValidation.war
# WORKDIR /opt/phoneValidation
# COPY ${WAR_FILE} app.jar
ENTRYPOINT [ "java","-jar","phoneValidation.jar" ]
