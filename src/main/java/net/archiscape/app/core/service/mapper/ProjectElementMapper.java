package net.archiscape.app.core.service.mapper;

import net.archiscape.app.core.domain.Project;
import net.archiscape.app.core.domain.ProjectElement;
import net.archiscape.app.core.service.dto.ProjectDTO;
import net.archiscape.app.core.service.dto.ProjectElementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ProjectElement} and its DTO {@link ProjectElementDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProjectElementMapper extends EntityMapper<ProjectElementDTO, ProjectElement> {
    @Mapping(target = "project", source = "project", qualifiedByName = "projectId")
    ProjectElementDTO toDto(ProjectElement s);

    @Named("projectId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProjectDTO toDtoProjectId(Project project);
}
