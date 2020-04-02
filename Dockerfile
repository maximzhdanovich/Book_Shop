FROM openjdk:8
ADD target/project-mysql.jar project-mysql.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","project-mysql.jar"]