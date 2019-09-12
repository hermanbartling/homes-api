package se.difo.hemnetapi.api.mapping.external;

import se.difo.hemnetapi.api.dto.external.ExtBrfRspDto;
import se.difo.hemnetapi.core.domain.Brf;

import java.util.List;
import java.util.stream.Collectors;

public class BrfDtoDomainMapper extends BaseMapper {

    public static ExtBrfRspDto toDto(Brf domain) {

        List<String> brIds = domain.getBrs().stream()
                .map(br -> br.getId().toString())
                .collect(Collectors.toList());

        return new ExtBrfRspDto(
                domain.getId().toString(),
                domain.getName(),
                domain.getStatus(),
                domain.getRegisteredYear(),
                domain.getMemberCount(),
                brIds
        );
    }


}
