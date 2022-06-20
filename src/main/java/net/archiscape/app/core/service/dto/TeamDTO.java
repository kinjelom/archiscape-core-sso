package net.archiscape.app.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.archiscape.app.core.domain.Team} entity.
 */
public class TeamDTO implements Serializable {

    @NotNull
    @Size(max = 30)
    private String id;

    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TeamDTO)) {
            return false;
        }

        TeamDTO teamDTO = (TeamDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, teamDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TeamDTO{" +
            "id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
