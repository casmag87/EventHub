# Build stage
FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:23.1-java17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

# Runtime stage
FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /work/
COPY --from=build /app/target/quarkus-app/lib/ /work/lib/
COPY --from=build /app/target/quarkus-app/*.jar /work/
COPY --from=build /app/target/quarkus-app/app/ /work/app/
COPY --from=build /app/target/quarkus-app/quarkus/ /work/quarkus/

EXPOSE 8080

CMD ["java", "-jar", "/work/quarkus-run.jar"]