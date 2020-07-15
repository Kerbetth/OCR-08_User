FROM openjdk:14
ADD build/libs/tourguide-user-1.0.0.jar tourguide-user-1.0.0.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "tourguide-user-1.0.0.jar"]
