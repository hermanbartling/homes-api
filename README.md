# Homes REST API
... is a simple CRUD interface for home related data. Two types of homes exists: 
* brs (bostadsrätt)
* house

MySQL is used as data store.

## Run locally
* Check that the default config values in ```application.properties``` fits your needs, otherwise override them with env variables.
* Start a local MySQL DB with docker compose ```docker-compose down && docker-compose up```

## Deploy
The bundled script ```deploy.sh``` builds a docker image, saves it to a file then copies the file to the chosen environment and runs the docker container there. Make sure to have correct values sen in your local environment variables used by the script.




