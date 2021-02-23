FROM openjdk:11
ADD target/*.jar service-throttle-backend.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","service-throttle-backend.jar"]
