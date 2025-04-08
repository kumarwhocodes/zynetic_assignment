FROM amazoncorretto:21-alpine
LABEL maintainer="Kumar Sambhav sambhav26k@gmail.com" \
      description="Zynetic Assignment"
COPY build/libs/bookstore-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]