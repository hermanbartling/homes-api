package se.difo.hemnetapi.api.mapping.admin;

import se.difo.hemnetapi.api.dto.admin.AdminBrCreateReqDto;
import se.difo.hemnetapi.api.dto.admin.AdminBrGetRspDto;
import se.difo.hemnetapi.api.dto.admin.AdminBrUpdateReqDto;
import se.difo.hemnetapi.api.dto.admin.AdminMinimalListRspDto;
import se.difo.hemnetapi.api.mapping.BaseMapper;
import se.difo.hemnetapi.api.mapping.common.BrokerDtoDomainMapper;
import se.difo.hemnetapi.api.mapping.common.CoordinateDtoDomainMapper;
import se.difo.hemnetapi.core.domain.Br;
import se.difo.hemnetapi.util.Temporal;

import java.util.UUID;

public class AdminBrMapper extends BaseMapper {

    public static Br toDomain(AdminBrCreateReqDto dto) {
        return new Br(
                null,
                dto.address,
                dto.area,
                dto.url,
                dto.price,
                dto.fee,
                dto.sqmLiving,
                dto.municipality,
                CoordinateDtoDomainMapper.toDomain(dto.coordinate),
                dto.rooms,
                BrokerDtoDomainMapper.toDomain(dto.broker),
                Temporal.fromDbString(dto.timeAdded),
                Temporal.fromDbString(dto.timeUpdated),
                Temporal.fromDbString(dto.timeRemoved),
                null,
                dto.visitCount,
                dto.brfId != null ? UUID.fromString(dto.brfId) : null
        );
    }

    public static Br toDomain(AdminBrUpdateReqDto dto) {
        return new Br(
                null,
                dto.address,
                dto.area,
                null,
                dto.price,
                dto.fee,
                dto.sqmLiving,
                dto.municipality,
                CoordinateDtoDomainMapper.toDomain(dto.coordinate),
                dto.rooms,
                BrokerDtoDomainMapper.toDomain(dto.broker),
                Temporal.fromDbString(dto.timeAdded),
                Temporal.fromDbString(dto.timeUpdated),
                Temporal.fromDbString(dto.timeRemoved),
                null,
                dto.visitCount,
                dto.brfId != null ? UUID.fromString(dto.brfId) : null
        );
    }

    public static AdminBrGetRspDto toDto(Br domain) {
        return new AdminBrGetRspDto(
                domain.getId().toString(),
                domain.getAddress(),
                domain.getArea(),
                domain.getUrl(),
                domain.getPrice(),
                domain.getFee(),
                domain.getRooms(),
                domain.getSqmLiving(),
                domain.getMunicipality(),
                CoordinateDtoDomainMapper.toDto(domain.getCoordinate()),
                BrokerDtoDomainMapper.toDto(domain.getBroker()),
                Temporal.asString(domain.getTimeAdded()),
                Temporal.asString(domain.getTimeUpdated()),
                Temporal.asString(domain.getTimeRemoved()),
                domain.getImagePaths(),
                domain.getBrfId() != null ? domain.getBrfId().toString() : null
        );
    }

    public static AdminMinimalListRspDto toMinimalListDto(Br domain) {
        return new AdminMinimalListRspDto(
                domain.getId().toString(),
                domain.getUrl(),
                Temporal.asString(domain.getTimeAdded()),
                Temporal.asString(domain.getTimeUpdated()),
                Temporal.asString(domain.getTimeRemoved())
        );
    }

}
