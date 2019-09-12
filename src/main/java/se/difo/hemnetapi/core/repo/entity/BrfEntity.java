package se.difo.hemnetapi.core.repo.entity;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "brf")
@Table(name = "brf")
@EntityListeners(AuditingEntityListener.class)
public class BrfEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "BINARY(16) NOT NULL")
    private UUID id;

    private String name;
    private String status;
    @Column(name = "year_registered")
    private Integer yearRegistered;
    @Column(name = "member_count")
    private Integer memberCount;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "brf_id",
            // no foreign key involved
            foreignKey = @javax.persistence.ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<BrEntity> brs = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getYearRegistered() {
        return yearRegistered;
    }

    public void setYearRegistered(Integer yearRegistered) {
        this.yearRegistered = yearRegistered;
    }

    public List<BrEntity> getBrs() {
        return brs;
    }

    public void setBrs(List<BrEntity> brs) {
        this.brs = brs;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    @Override
    public String toString() {
        return String.format(
                "BrfEntity(%s, %s)",
                id,
                name
        );
    }
}
