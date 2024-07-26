## Use the official OpenJDK image as a base image
#FROM openjdk:17
#
## Set the working directory
#WORKDIR /app
#
## Copy the built jar file
#COPY build/libs/*.jar app.jar
#
## Run the jar file
#ENTRYPOINT ["java", "-jar", "app.jar"]


# Sử dụng image OpenJDK làm base image
FROM eclipse-temurin:17-jdk-focal

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép các file Gradle wrapper và build script
COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle

# Tải xuống các dependencies cần thiết
RUN ./gradlew build --no-daemon --parallel --build-cache

# Sao chép mã nguồn ứng dụng
COPY src ./src

# Build ứng dụng
RUN ./gradlew build --no-daemon --parallel --build-cache

# Thiết lập lệnh mặc định để chạy ứng dụng
CMD ["java", "-jar", "build/libs/discovery-server-0.0.1-SNAPSHOT.jar"]
