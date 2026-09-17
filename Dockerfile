FROM maven:3.9-eclipse-temurin-17-alpine AS dependencies

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline -B

FROM dependencies AS build

WORKDIR /app

COPY src ./src

RUN mvn clean package -DskipTests


FROM build AS layers

WORKDIR /app

RUN java -Djarmode=layertools -jar target/*.jar extract --destination extracted

FROM gcr.io/distroless/java17-debian12 AS runtime

WORKDIR /app

COPY --from=layers /app/extracted/dependencies/ ./
COPY --from=layers /app/extracted/spring-boot-loader/ ./
COPY --from=layers /app/extracted/snapshot-dependencies/ ./
COPY --from=layers /app/extracted/application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

