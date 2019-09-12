package se.difo.hemnetapi.api.dto.common;

public class BrokerDto {

    public final String personName;
    public final String firmName;
    public final String firmWebPage;

    public BrokerDto(String personName, String firmName, String firmWebPage) {
        this.personName = personName;
        this.firmName = firmName;
        this.firmWebPage = firmWebPage;
    }
}
