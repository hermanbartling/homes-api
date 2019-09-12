package se.difo.hemnetapi.api.validate.admin;


import se.difo.hemnetapi.api.dto.admin.AdminBrCreateReqDto;
import se.difo.hemnetapi.api.dto.admin.AdminBrUpdateReqDto;
import se.difo.hemnetapi.api.validate.BaseValidator;

public class AdminBrValidator extends BaseValidator {

    public static void validateCreate(AdminBrCreateReqDto createDto){
        validateTimeStamp(createDto.timeAdded);
        validateTimeStamp(createDto.timeUpdated);
        validateTimeStamp(createDto.timeRemoved);
    }

    public static void validateUpdate(AdminBrUpdateReqDto createDto){
        validateTimeStamp(createDto.timeAdded);
        validateTimeStamp(createDto.timeUpdated);
        validateTimeStamp(createDto.timeRemoved);
    }

}
