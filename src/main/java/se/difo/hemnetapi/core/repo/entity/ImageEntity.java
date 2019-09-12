package se.difo.hemnetapi.core.repo.entity;


import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity(name = "image")
@Table(name = "image")
@EntityListeners(AuditingEntityListener.class)
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "object_id", columnDefinition = "BINARY(16) NOT NULL")
    private UUID objectId;

    private String path;

    @Column(name = "time_added")
    private String timeAdded;

    public Long getId() {
        return id;
    }

    public UUID getObjectId() {
        return objectId;
    }

    public String getPath() {
        return path;
    }

    public String getTimeAdded() {
        return timeAdded;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setObjectId(UUID objectId) {
        this.objectId = objectId;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTimeAdded(String timeAdded) {
        this.timeAdded = timeAdded;
    }

    @Override
    public String toString() {
        return String.format(
                "ImageEntity(%d, %s, %s)",
                id,
                objectId,
                path
        );
    }
}
