# --- Stage 1: Build ---
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Maven wrapper + pom dosyasını kopyala (cache için)
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./
RUN chmod +x mvnw

# Bağımlılıkları indir (cache layer)
RUN ./mvnw dependency:resolve -B

# Kaynak kodu kopyala ve paketle
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# --- Stage 2: Run ---
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Sadece jar'ı kopyala (küçük image)
COPY --from=build /app/target/api-0.0.1-SNAPSHOT.jar app.jar

# Upload klasörü
RUN mkdir -p /app/uploads

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
