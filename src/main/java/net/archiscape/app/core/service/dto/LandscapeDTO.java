package net.archiscape.app.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.archiscape.app.core.domain.Landscape} entity.
 */
public class LandscapeDTO implements Serializable {

    @NotNull
    @Size(max = 10)
    private String id;

    private String description;

    @Size(max = 4096)
    private String configuration;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LandscapeDTO)) {
            return false;
        }

        LandscapeDTO landscapeDTO = (LandscapeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, landscapeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LandscapeDTO{" +
            "id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", configuration='" + getConfiguration() + "'" +
            "}";
    }
}
