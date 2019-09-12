package se.difo.hemnetapi.core.domain;


public class Broker {

    private String personName;
    private String firmName;
    private String firmWebPage;

    public Broker(String personName, String firmName, String firmWebPage) {
        this.personName = personName;
        this.firmName = firmName;
        this.firmWebPage = firmWebPage;
    }

    public String getPersonName() {
        return personName;
    }

    public String getFirmName() {
        return firmName;
    }

    public String getFirmWebPage() {
        return firmWebPage;
    }

    public void setPersonNameIfNotNull(String personName) {
        if(personName == null) {
            return;
        }
        this.personName = personName;
    }

    public void setFirmNameIfNotNull(String firmName) {
        if(firmName == null) {
            return;
        }
        this.firmName = firmName;
    }

    public void setFirmWebPageIfNotNull(String firmWebPage) {
        if(firmWebPage == null) {
            return;
        }
        this.firmWebPage = firmWebPage;
    }
}
