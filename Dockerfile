FROM openjdk:8
ADD target/spring-rest-service.jar spring-rest-service.jar 
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-rest-service.jar"]