FROM openjdk:15

WORKDIR /usr/src/app
COPY gradlew* ./
COPY /gradle ./gradle
COPY *.gradle ./
RUN ./gradlew dependencies --write-locks
COPY /src ./src
RUN ./gradlew build
EXPOSE 6060
CMD ["java","-jar","/usr/src/app/build/libs/helloworld-0.0.1-SNAPSHOT.jar"]