# Stage 1: Build dự án bằng Maven
FROM maven:3.9.8-amazoncorretto-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Chạy ứng dụng bằng JDK rút gọn (nhẹ hơn)
FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
# Copy file .jar từ bước build sang bước chạy
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]