package se.difo.hemnetapi.api.mapping.admin;

import se.difo.hemnetapi.api.dto.admin.AdminHouseCreateReqDto;
import se.difo.hemnetapi.api.dto.admin.AdminHouseGetRspDto;
import se.difo.hemnetapi.api.dto.admin.AdminHouseUpdateReqDto;
import se.difo.hemnetapi.api.mapping.common.BrokerDtoDomainMapper;
import se.difo.hemnetapi.api.mapping.common.CoordinateDtoDomainMapper;
import se.difo.hemnetapi.core.domain.House;
import se.difo.hemnetapi.util.Temporal;

public class AdminHouseMapper {

    public static House toDomain(AdminHouseCreateReqDto dto) {
        return new House(
                null,
                dto.getAddress(),
                dto.getArea(),
                dto.getUrl(),
                dto.getPrice(),
                dto.getType(),
                dto.getSqmLiving(),
                dto.getSqmLivingAdditional(),
                dto.getSqmLand(),
                dto.getMunicipality(),
                CoordinateDtoDomainMapper.toDomain(dto.getCoordinate()),
                dto.getRooms(),
                dto.getCostYear(),
                dto.getBuiltYear(),
                BrokerDtoDomainMapper.toDomain(dto.getBroker()),
                Temporal.fromDbString(dto.getTimeAdded()),
                Temporal.fromDbString(dto.getTimeUpdated()),
                Temporal.fromDbString(dto.getTimeRemoved()),
                null,
                dto.getVisitCount()
        );
    }

    public static House toDomain(AdminHouseUpdateReqDto dto) {
        return new House(
                null,
                dto.getAddress(),
                dto.getArea(),
                null,
                dto.getPrice(),
                dto.getType(),
                dto.getSqmLiving(),
                dto.getSqmLivingAdditional(),
                dto.getSqmLand(),
                dto.getMunicipality(),
                CoordinateDtoDomainMapper.toDomain(dto.getCoordinate()),
                dto.getRooms(),
                dto.getCostYear(),
                dto.getBuiltYear(),
                BrokerDtoDomainMapper.toDomain(dto.getBroker()),
                Temporal.fromDbString(dto.getTimeAdded()),
                Temporal.fromDbString(dto.getTimeUpdated()),
                Temporal.fromDbString(dto.getTimeRemoved()),
                null,
                dto.getVisitCount()
        );
    }

    public static AdminHouseGetRspDto toDto(House domain) {
        AdminHouseGetRspDto dto = new AdminHouseGetRspDto();

        dto.setId(domain.getId().toString());
        dto.setAddress(domain.getAddress());
        dto.setArea(domain.getArea());
        dto.setUrl(domain.getUrl());
        dto.setPrice(domain.getPrice());
        dto.setRooms(domain.getRooms());
        dto.setType(domain.getType());
        dto.setCostYear(domain.getCostYear());
        dto.setBuiltYear(domain.getBuiltYear());

        dto.setBroker(BrokerDtoDomainMapper.toDto(domain.getBroker()));

        dto.setSqmLiving(domain.getSqmLiving());
        dto.setSqmLand(domain.getSqmLand());
        dto.setSqmLivingAdditional(domain.getSqmLivingAdditional());

        dto.setMunicipality(domain.getMunicipality());
        dto.setCoordinate(CoordinateDtoDomainMapper.toDto(domain.getCoordinate()));

        dto.setTimeRemoved(Temporal.asString(domain.getTimeRemoved()));
        dto.setTimeAdded(Temporal.asString(domain.getTimeAdded()));
        dto.setTimeUpdated(Temporal.asString(domain.getTimeUpdated()));

        dto.setImageUrls(domain.getImagePaths());
        return dto;
    }
}
