#!/usr/bin/env bash

#curl -v \
#--user john:doe \
#-X POST \
#-H 'Content-Type: application/json' \
#-d '{"address":"Paradisäppelvägen 314","area":"Gnesta","url":"http://br.home.com","price":1234000,"fee":1234000,"sqmLiving":67,"rooms":6,"municipality":"Gnesta","coordinate":"[56.23,34.6788]","brokerPersonName":"Ingela Mäklaren","brokerFirmName":"Mecklare","brokerFirmWebPage":"http://www.porr.com","timeUpdated":"2019-01-01 11:30:00","timeRemoved":"2019-01-01 12:00:00","timeAdded":"2019-01-01 11:00:00"}' \
#http://localhost:8080/api/v1/admin/brs \

curl -v \
--user john:doe \
-X POST \
-H 'Content-Type: application/json' \
-d '{"address":"Husvägen 314","area":"Gnesta","url":"http://br.home.com","price":1234000,"rooms":6,"type":"Villa","costYear":12155,"builtYear":1950,"sqmLiving":67,"sqmLivingAdditional":5,"sqmLand":4006,"municipality":"Gnesta","coordinate":"[56.23,34.6788]","brokerPersonName":"Ronald Macdonald","brokerFirmName":"Mecklenburg","brokerFirmWebPage":"http://maklig.takt.se","timeRemoved":"2019-01-01T12:00:00Z","timeAdded":"2019-01-01T11:00:00Z","timeUpdated":"2019-01-01T11:30:00Z"}' \
http://localhost:8080/api/v1/admin/houses \


#curl -v \
#--user john:doe \
#-X POST \
#-H 'Content-Type: application/json' \
#-d '{"objectId":"d55fc2d1-980e-436f-830f-36e604b55424","path":"/to/br.png"}' \
#http://localhost:8080/api/v1/admin/images \
#
#curl -v \
#--user john:doe \
#-X POST \
#-H 'Content-Type: application/json' \
#-d '{"objectId":"dbd0d3b9-e9af-44a2-9355-f6e9c2377850","path":"/to/house.png"}' \
#http://localhost:8080/api/v1/admin/images \
#
