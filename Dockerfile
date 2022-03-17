
FROM adoptopenjdk:8
ARG JAR_FILE=build/libs/P8_tripPricer-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} tripPricer.jar
ENTRYPOINT ["java","-jar","/tripPricer.jar"]