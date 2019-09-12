package se.difo.hemnetapi.core.mapper;

import se.difo.hemnetapi.core.domain.Brf;
import se.difo.hemnetapi.core.repo.entity.BrfEntity;

import java.util.stream.Collectors;

public class BrfMapper {

    public static Brf toDomain(BrfEntity entity) {
        return new Brf(
                entity.getId(),
                entity.getName(),
                entity.getStatus(),
                entity.getYearRegistered(),
                entity.getMemberCount(),
                entity.getBrs().stream().map(BrMapper::toDomain).collect(Collectors.toList())
        );
    }

    public static BrfEntity toEntity(Brf domain) {
        BrfEntity entity = new BrfEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setStatus(domain.getStatus());
        entity.setYearRegistered(domain.getRegisteredYear());
        entity.setMemberCount(domain.getMemberCount());
        return entity;
    }


}
