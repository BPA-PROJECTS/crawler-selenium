#!/bin/bash
# Остановка всех запущенных контейнеров
docker stop $(docker ps -a -q)

# Удаление всех контейнеров
docker rm -f $(docker ps -a -q)

# Удаление всех образов
docker rmi -f $(docker images -a -q)

# Удаление всех томов
docker volume rm $(docker volume ls -q)

echo "Docker cleanup completed!"