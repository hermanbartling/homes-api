package se.difo.hemnetapi.api.validate.admin;


import se.difo.hemnetapi.api.dto.admin.brf.AdminBrfCreateReqDto;
import se.difo.hemnetapi.api.dto.admin.brf.AdminBrfUpdateReqDto;
import se.difo.hemnetapi.api.validate.BaseValidator;

public class AdminBrfValidator extends BaseValidator {

    public static void validateCreate(AdminBrfCreateReqDto createDto) {
        assertNotNull(createDto.name, "name");
        assertNotNull(createDto.status, "status");
        assertNotNull(createDto.yearRegistered, "yearRegistered");
    }

    public static void validateUpdate(AdminBrfUpdateReqDto dto) {

    }

}
