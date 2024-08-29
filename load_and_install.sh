#!/bin/bash

# Обновляет локальный репозиторий до последних изменений из удаленного репозитория.
git pull

# Очищает проект Gradle, удаляя сгенерированные файлы и каталоги.
gradle clean

# Собирает проект Gradle, компилируя исходный код и создавая артефакты.
gradle build

# Останавливает и удаляет контейнеры и сети, определенные в docker-compose.yml
docker-compose down --rmi all --volumes --remove-orphans

# Удаление контейнеров
docker container prune -f

# Удаление образов
docker image prune -f

docker-compose up -d --force-recreate