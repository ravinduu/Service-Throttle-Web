FROM openjdk:11
ADD service-throttle-backend/target/*.jar service-throttle-backend.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","service-throttle-backend.jar"]
