package se.difo.hemnetapi.core.mapper;

import se.difo.hemnetapi.core.domain.Br;
import se.difo.hemnetapi.core.domain.Broker;
import se.difo.hemnetapi.core.repo.entity.BrEntity;
import se.difo.hemnetapi.util.Temporal;

import java.util.stream.Collectors;

public class BrMapper {

    public static Br toDomain(BrEntity entity) {
        return new Br(
                entity.getId(),
                entity.getAddress(),
                entity.getArea(),
                entity.getUrl(),
                entity.getPrice(),
                entity.getFee(),
                entity.getSqm(),
                entity.getMunicipality(),
                CoordinateMapper.toDomain(entity.getCoordinate()),
                entity.getRooms(),
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
                entity.getVisitCount(),
                entity.getBrfId()
        );
    }

    public static BrEntity toEntity(Br domain) {
        BrEntity entity = new BrEntity();
        entity.setId(domain.getId());
        entity.setAddress(domain.getAddress());
        entity.setArea(domain.getArea());
        entity.setUrl(domain.getUrl());
        entity.setPrice(domain.getPrice());
        entity.setSqm(domain.getSqmLiving());
        entity.setRooms(domain.getRooms());
        entity.setFee(domain.getFee());
        entity.setMunicipality(domain.getMunicipality());
        entity.setCoordinate(CoordinateMapper.toEntity(domain.getCoordinate()));
        entity.setVisitCount(domain.getVisitCount());

        entity.setTimeRemoved(Temporal.asDbString(domain.getTimeRemoved()));
        entity.setTimeAdded(Temporal.asDbString(domain.getTimeAdded()));
        entity.setTimeUpdated(Temporal.asDbString(domain.getTimeUpdated()));

        entity.setBrfId(domain.getBrfId());

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
