FROM openjdk:17-oracle
ARG JAR_FILE=target/*.jar
ARG WAIT=target/*.sh
COPY ${JAR_FILE} app.jar
COPY ${WAIT} wait-for-it.sh
ENTRYPOINT ["./wait-for-it.sh", "db:3306", "--", "java", "-jar", "/app.jar"]
