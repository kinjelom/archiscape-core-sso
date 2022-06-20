package net.archiscape.app.core.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.archiscape.app.core.domain.ContentStoreInternal} entity.
 */
public class ContentStoreInternalDTO implements Serializable {

    @NotNull
    private UUID id;

    @NotNull
    private Integer version;

    @NotNull
    private LocalDate insertDate;

    private String fileName;

    @Lob
    private String content;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDate getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDate insertDate) {
        this.insertDate = insertDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContentStoreInternalDTO)) {
            return false;
        }

        ContentStoreInternalDTO contentStoreInternalDTO = (ContentStoreInternalDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, contentStoreInternalDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContentStoreInternalDTO{" +
            "id='" + getId() + "'" +
            ", version=" + getVersion() +
            ", insertDate='" + getInsertDate() + "'" +
            ", fileName='" + getFileName() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
