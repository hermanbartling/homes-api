package se.difo.hemnetapi.core.domain;


import java.util.List;
import java.util.UUID;

public class Brf {

    private UUID id;
    private String name;
    private String status;
    private Integer registeredYear;
    private Integer memberCount;
    private List<Br> brs;

    public Brf(UUID id, String name, String status, Integer registeredYear, Integer memberCount) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.registeredYear = registeredYear;
        this.memberCount = memberCount;
        this.brs = null;
    }

    public Brf(UUID id, String name, String status, Integer registeredYear, Integer memberCount, List<Br> brs) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.registeredYear = registeredYear;
        this.memberCount = memberCount;
        this.brs = brs;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getRegisteredYear() {
        return registeredYear;
    }

    public List<Br> getBrs() {
        return brs;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setIdIfNotNull(UUID id) {
        if (id == null) {
            return;
        }
        this.id = id;
    }

    public void setNameIfNotNull(String name) {
        if (name == null) {
            return;
        }
        this.name = name;
    }

    public void setStatusIfNotNull(String status) {
        if (status == null) {
            return;
        }
        this.status = status;
    }

    public void setRegisteredYearIfNotNull(Integer registeredYear) {
        if (registeredYear == null) {
            return;
        }
        this.registeredYear = registeredYear;
    }

    public void setMemberCountIfNotNull(Integer memberCount) {
        if (memberCount == null) {
            return;
        }
        this.memberCount = memberCount;
    }

    @Override
    public String toString() {
        return "Brf(" +
                "id=" + id +
                ", name='" + name + '\'' +
                ')';
    }
}
