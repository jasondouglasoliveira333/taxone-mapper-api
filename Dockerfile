FROM openjdk:8
COPY target/taxone-mapper-api.jar taxone.mv.db /app/
WORKDIR /app/
CMD ["java", "-jar", "taxone-mapper-api.jar"]

