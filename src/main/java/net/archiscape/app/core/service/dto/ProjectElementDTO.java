package net.archiscape.app.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;
import net.archiscape.app.core.domain.enumeration.ElementType;

/**
 * A DTO for the {@link net.archiscape.app.core.domain.ProjectElement} entity.
 */
public class ProjectElementDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private ElementType type;

    private String documentation;

    @Size(max = 30)
    private String landscapeElementId;

    private ProjectDTO project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLandscapeElementId() {
        return landscapeElementId;
    }

    public void setLandscapeElementId(String landscapeElementId) {
        this.landscapeElementId = landscapeElementId;
    }

    public ProjectDTO getProject() {
        return project;
    }

    public void setProject(ProjectDTO project) {
        this.project = project;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectElementDTO)) {
            return false;
        }

        ProjectElementDTO projectElementDTO = (ProjectElementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, projectElementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectElementDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", documentation='" + getDocumentation() + "'" +
            ", landscapeElementId='" + getLandscapeElementId() + "'" +
            ", project=" + getProject() +
            "}";
    }
}
