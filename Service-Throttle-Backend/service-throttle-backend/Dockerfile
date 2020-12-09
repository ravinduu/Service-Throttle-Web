FROM openjdk:11

# WORKDIR /app

# COPY mvnw .
# COPY .mvn .mvn
# COPY pom.xml .

# RUN chmod +x ./mvnw
# RUN ./mvnw dependency:go-offline -B

# COPY src src

# RUN ./mvnw package -DskipTests
# RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# FROM openjdk:11 as production

# ARG DEPENDENCY=/app/target/dependency

# COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
# COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
# COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app


# ENTRYPOINT ["java", "-cp", "app:app/lib/*","com.servicethrottle.servicethrottlebackend.ServiceThrottleBackendApplication"]
ADD target/service-throttle-backend.jar service-throttle-backend.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","service-throttle-backend.jar"]