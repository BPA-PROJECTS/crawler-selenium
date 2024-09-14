# Используем базовый образ с OpenJDK 17
FROM openjdk:17-jdk-slim

# Установка необходимых системных библиотек
RUN apt-get update \
    && apt-get install -y libglib2.0-0 libnss3 libxcb1

# Установка рабочей директории в /app
WORKDIR /app

# Определение аргумента сборки
ARG HOST_SELENIUM_GRID_URL

# Установка переменной окружения на основе аргумента
ENV SELENIUM_GRID_URL=${HOST_SELENIUM_GRID_URL}

# Копирование драйвера и JAR-файла в контейнер
COPY build/libs/tg-crawler-selenium-1.0.0.jar /app/app.jar

# Указываем порт, который будет использоваться в приложении
EXPOSE 8300

# Команда для запуска Spring Boot приложения с указанием профилей
CMD ["java", "-jar", "-Dspring.profiles.active=params-prod", "app.jar"]
