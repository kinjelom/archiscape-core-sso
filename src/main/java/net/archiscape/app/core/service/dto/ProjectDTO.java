package net.archiscape.app.core.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link net.archiscape.app.core.domain.Project} entity.
 */
public class ProjectDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 50)
    private String name;

    private String description;

    @Size(max = 4096)
    private String configuration;

    @NotNull
    private Boolean active;

    @Size(max = 2048)
    private String contentStoreUrl;

    private LandscapeDTO landscape;

    private TeamDTO team;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getContentStoreUrl() {
        return contentStoreUrl;
    }

    public void setContentStoreUrl(String contentStoreUrl) {
        this.contentStoreUrl = contentStoreUrl;
    }

    public LandscapeDTO getLandscape() {
        return landscape;
    }

    public void setLandscape(LandscapeDTO landscape) {
        this.landscape = landscape;
    }

    public TeamDTO getTeam() {
        return team;
    }

    public void setTeam(TeamDTO team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectDTO)) {
            return false;
        }

        ProjectDTO projectDTO = (ProjectDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, projectDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", configuration='" + getConfiguration() + "'" +
            ", active='" + getActive() + "'" +
            ", contentStoreUrl='" + getContentStoreUrl() + "'" +
            ", landscape=" + getLandscape() +
            ", team=" + getTeam() +
            "}";
    }
}
