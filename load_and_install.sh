#!/bin/bash

# Обновляет локальный репозиторий до последних изменений из удаленного репозитория.
git pull

# Очищает проект Gradle, удаляя сгенерированные файлы и каталоги.
gradle clean

# Собирает проект Gradle, компилируя исходный код и создавая артефакты.
gradle build

if docker images | awk '{print $1}' | grep -q "^back-tg-crawler-selenium$"; then
    docker rmi -f back-tg-crawler-selenium
fi

docker-compose down
docker-compose up -d --force-recreate