package se.difo.hemnetapi.core.domain;


import java.time.Instant;
import java.util.UUID;

public class Image {

    private Long id;
    private UUID objectId;
    private String path;
    private Instant timeAdded;

    public Image(Long id, UUID objectId, String path, Instant timeAdded) {
        this.id = id;
        this.objectId = objectId;
        this.path = path;
        this.timeAdded = timeAdded;
    }

    public Long getId() {
        return id;
    }

    public UUID getObjectId() {
        return objectId;
    }

    public String getPath() {
        return path;
    }

    public Instant getTimeAdded() {
        return timeAdded;
    }
}
