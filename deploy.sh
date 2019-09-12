#!/usr/bin/env bash

docker_image_name=difo-homes-api
docker_image_filename="/tmp/$docker_image_name-dockerimage.tar"

if [[ "$1" == "prd" ]]; then
    server='åsen'

    difo_api_db_host=192.168.1.13:3306

    # docker build -t "$docker_image_name":latest .
    ./build.sh || { echo 'Aborting deploy' ; exit 1; } ; \
    docker save -o "$docker_image_filename" "$docker_image_name":latest || { echo 'Aborting deploy' ; exit 1; } ; \
    scp "$docker_image_filename" "$server:$docker_image_filename" || { echo 'Aborting deploy' ; exit 1; } ; \

    ssh -A "$server" "docker load -i $docker_image_filename

    docker stop "$docker_image_name"
    docker rm "$docker_image_name"

    echo "Starting $docker_image_name using:"
    echo " - api db host: $difo_api_db_host"
    echo " - api db uid: $DB_USERNAME"
    echo " - api uid: $ADMIN_API_UID"

    docker run \
    --rm \
    --name $docker_image_name \
    -e DATABASE_HOST=$difo_api_db_host \
    -e DB_USERNAME=$DB_USERNAME \
    -e DB_PASSWORD=$DB_PASSWORD \
    -e ADMIN_API_UID=$ADMIN_API_UID \
    -e ADMIN_API_PWD=$ADMIN_API_PWD \
    -p 8080:8080 \
    -d \
    $docker_image_name:latest" || { echo 'Aborting deploy' ; exit 1; } ; \

    echo ""
    echo "DONE!"
    echo ""
else
    echo "$1 not recognized environment, we only support: [prd]"
fi

