#!/bin/bash

# Обновляет локальный репозиторий до последних изменений из удаленного репозитория.
git pull

# Очищает проект Gradle, удаляя сгенерированные файлы и каталоги.
gradle clean

# Собирает проект Gradle, компилируя исходный код и создавая артефакты.
gradle build

docker-compose down

# Удаление контейнеров
for container in $(docker ps -a | grep 'tg-crawler-selenium' | awk '{print $1}'); do
    docker stop $container
    docker rm $container
done

# Удаление образов
for image in $(docker images | grep 'tg-crawler-selenium' | awk '{print $3}'); do
    docker rmi $image
done

docker-compose down
docker-compose up -d --force-recreate