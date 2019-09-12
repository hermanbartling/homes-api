package se.difo.hemnetapi.api.dto.admin.brf;

public class AdminBrfUpdateReqDto {

    public final String status;
    public final Integer yearRegistered;
    public final Integer memberCount;

    public AdminBrfUpdateReqDto(String status, Integer yearRegistered, Integer memberCount) {
        this.status = status;
        this.yearRegistered = yearRegistered;
        this.memberCount = memberCount;
    }
}
