#!/usr/bin/env bash
./build.sh
docker stop difo-homes-api
docker rm difo-homes-api
docker run \
--rm \
--name difo-homes-api \
-e DATABASE_HOST=192.168.1.116:3306 \
-e DB_USERNAME=user \
-e DB_PASSWORD=password \
-e ADMIN_API_UID=admin \
-e ADMIN_API_PWD=admin \
-p 8080:8080 \
difo-homes-api:latest


#https://www.tutorialkart.com/docker/docker-image-with-python-application-example/
#docker save -o /home/arjun/workspace/docker/python-application.tar python-application
# docker load -i target/difo-homes-api.tar