FROM openjdk:11
ARG JAR_FILE=./Service-Throttle-Backend/service-throttle-backend/target/*.jar
COPY ${JAR_FILE} service-throttle-backend.jar
# ADD service-throttle-backend/target/*.jar service-throttle-backend.jar
# EXPOSE 8081
ENTRYPOINT ["java","-jar","service-throttle-backend.jar"]
