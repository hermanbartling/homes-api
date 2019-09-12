package se.difo.hemnetapi.api.dto.admin.brf;

import se.difo.hemnetapi.api.dto.common.BrfListDto;
import se.difo.hemnetapi.api.dto.common.PaginationDto;

import java.util.List;

public class AdminBrfListRspDto {


    public final List<BrfListDto> brfs;
    public final PaginationDto pagination;

    public AdminBrfListRspDto(
            List<BrfListDto> brfs,
            PaginationDto pagination
    ) {
        this.brfs = brfs;
        this.pagination = pagination;
    }

}
