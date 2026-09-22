FROM maven:3.9.11-eclipse-temurin-21 as build

WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests -B

FROM eclipse-temurin:21-jre-jammy
RUN groupadd --system --gid 10001 spring \
    && useradd --system \
       --uid 10001 \
       --gid 10001 \
       --no-create-home \
       --shell /usr/sbin/nologin \
       spring

WORKDIR /app
COPY --from=build /build/target/*.jar /app/app.jar
RUN chown -R spring:spring /app

USER 10001:10001
EXPOSE 8081

ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=70 \
                       -XX:InitialRAMPercentage=20 \
                       -XX:+UseG1GC \
                       -XX:+ExitOnOutOfMemoryError"

ENTRYPOINT ["java", "-jar", "/app/app.jar"]