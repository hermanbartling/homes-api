#!/usr/bin/env bash
mvn clean install || { echo 'Aborting build script' ; exit 1; } ; \
docker build -t difo-homes-api:latest .
