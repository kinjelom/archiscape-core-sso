package net.archiscape.app.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import net.archiscape.app.core.domain.enumeration.ElementType;

/**
 * A DTO for the {@link net.archiscape.app.core.domain.LandscapeElement} entity.
 */
public class LandscapeElementDTO implements Serializable {

    @NotNull
    @Size(max = 30)
    private String id;

    @NotNull
    private ElementType type;

    private String documentation;

    private LandscapeDTO landscape;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public LandscapeDTO getLandscape() {
        return landscape;
    }

    public void setLandscape(LandscapeDTO landscape) {
        this.landscape = landscape;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LandscapeElementDTO)) {
            return false;
        }

        LandscapeElementDTO landscapeElementDTO = (LandscapeElementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, landscapeElementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LandscapeElementDTO{" +
            "id='" + getId() + "'" +
            ", type='" + getType() + "'" +
            ", documentation='" + getDocumentation() + "'" +
            ", landscape=" + getLandscape() +
            "}";
    }
}
