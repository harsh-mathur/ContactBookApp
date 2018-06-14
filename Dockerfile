FROM openjdk:8-jre-alpine
WORKDIR /opt/api
EXPOSE 8080
ADD build/libs/ContactBookApp-0.0.1-SNAPSHOT.jar /opt/api/ContactBookApp-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh", "-c", "java -jar /opt/api/ContactBookApp-0.0.1-SNAPSHOT.jar"]