package se.difo.hemnetapi.core.mapper;

import se.difo.hemnetapi.core.domain.Br;
import se.difo.hemnetapi.core.domain.Broker;
import se.difo.hemnetapi.core.domain.House;
import se.difo.hemnetapi.core.repo.entity.BrEntity;
import se.difo.hemnetapi.core.repo.entity.HouseEntity;
import se.difo.hemnetapi.util.Temporal;

import java.util.stream.Collectors;

public class HouseMapper {

    public static House toDomain(HouseEntity entity) {
        return new House(
                entity.getId(),
                entity.getAddress(),
                entity.getArea(),
                entity.getUrl(),
                entity.getPrice(),
                entity.getType(),
                entity.getSqmLiving(),
                entity.getSqmLivingAdditional(),
                entity.getSqmLand(),
                entity.getMunicipality(),
                CoordinateMapper.toDomain(entity.getCoordinate()),
                entity.getRooms(),
                entity.getCostYear(),
                entity.getBuiltYear(),
                new Broker(
                        entity.getBrokerPersonName(),
                        entity.getBrokerFirmName(),
                        entity.getBrokerFirmWebPage()
                ),
                Temporal.fromDbString(entity.getTimeAdded()),
                Temporal.fromDbString(entity.getTimeUpdated()),
                Temporal.fromDbString(entity.getTimeRemoved()),
                entity.getImages().stream()
                        .map(ImageMapper::toDomain)
                        .collect(Collectors.toList()),
                entity.getVisitCount()
        );
    }

    public static HouseEntity toEntity(House domain) {
        HouseEntity entity = new HouseEntity();
        entity.setId(domain.getId());
        entity.setAddress(domain.getAddress());
        entity.setArea(domain.getArea());
        entity.setUrl(domain.getUrl());
        entity.setPrice(domain.getPrice());
        entity.setType(domain.getType());
        entity.setSqmLiving(domain.getSqmLiving());
        entity.setSqmLivingAdditional(domain.getSqmLivingAdditional());
        entity.setSqmLand(domain.getSqmLand());
        entity.setRooms(domain.getRooms());
        entity.setCostYear(domain.getCostYear());
        entity.setBuiltYear(domain.getBuiltYear());
        entity.setMunicipality(domain.getMunicipality());
        entity.setCoordinate(CoordinateMapper.toEntity(domain.getCoordinate()));
        entity.setVisitCount(domain.getVisitCount());

        entity.setTimeRemoved(Temporal.asDbString(domain.getTimeRemoved()));
        entity.setTimeAdded(Temporal.asDbString(domain.getTimeAdded()));
        entity.setTimeUpdated(Temporal.asDbString(domain.getTimeUpdated()));

        if (domain.getImages() != null) {
            entity.setImages(
                    domain.getImages().stream()
                            .map(ImageMapper::toEntity)
                            .collect(Collectors.toList())
            );
        }

        if (domain.getBroker() != null) {
            entity.setBrokerPersonName(domain.getBroker().getPersonName());
            entity.setBrokerFirmName(domain.getBroker().getFirmName());
            entity.setBrokerFirmWebPage(domain.getBroker().getFirmWebPage());
        }

        return entity;
    }


}
