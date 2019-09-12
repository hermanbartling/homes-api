package se.difo.hemnetapi.api.mapping.common;

import se.difo.hemnetapi.api.dto.common.BrokerDto;
import se.difo.hemnetapi.core.domain.Broker;

public class BrokerDtoDomainMapper {

    public static BrokerDto toDto(Broker domain) {
        if(domain == null) {
            return null;
        }
        return new BrokerDto(domain.getPersonName(), domain.getFirmName(), domain.getFirmWebPage());
    }

    public static Broker toDomain(BrokerDto dto) {
        if(dto == null) {
            return null;
        }

        return new Broker(dto.personName, dto.firmName, dto.firmWebPage);
    }

}
