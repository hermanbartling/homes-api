package se.difo.hemnetapi.api.dto.admin.brf;

public class AdminBrfCreateReqDto extends AdminBrfUpdateReqDto {

    public final String name;

    public AdminBrfCreateReqDto(String name, String status, Integer yearRegistered, Integer memberCount) {
        super(status, yearRegistered, memberCount);
        this.name = name;
    }
}
