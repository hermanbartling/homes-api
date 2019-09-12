package se.difo.hemnetapi.api.validate.admin;


import se.difo.hemnetapi.api.dto.admin.AdminHouseCreateReqDto;
import se.difo.hemnetapi.api.dto.admin.AdminHouseUpdateReqDto;
import se.difo.hemnetapi.api.validate.BaseValidator;

public class AdminHouseValidator extends BaseValidator {

    public static void validateCreate(AdminHouseCreateReqDto createDto) {
        validateTimeStamp(createDto.getTimeAdded());
        validateTimeStamp(createDto.getTimeUpdated());
        validateTimeStamp(createDto.getTimeRemoved());
    }

    public static void validateUpdate(AdminHouseUpdateReqDto createDto) {
        validateTimeStamp(createDto.getTimeAdded());
        validateTimeStamp(createDto.getTimeUpdated());
        validateTimeStamp(createDto.getTimeRemoved());
    }

}
