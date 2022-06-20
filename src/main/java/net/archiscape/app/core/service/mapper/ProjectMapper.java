package net.archiscape.app.core.service.mapper;

import net.archiscape.app.core.domain.Landscape;
import net.archiscape.app.core.domain.Project;
import net.archiscape.app.core.domain.Team;
import net.archiscape.app.core.service.dto.LandscapeDTO;
import net.archiscape.app.core.service.dto.ProjectDTO;
import net.archiscape.app.core.service.dto.TeamDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Project} and its DTO {@link ProjectDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProjectMapper extends EntityMapper<ProjectDTO, Project> {
    @Mapping(target = "landscape", source = "landscape", qualifiedByName = "landscapeId")
    @Mapping(target = "team", source = "team", qualifiedByName = "teamId")
    ProjectDTO toDto(Project s);

    @Named("landscapeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LandscapeDTO toDtoLandscapeId(Landscape landscape);

    @Named("teamId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TeamDTO toDtoTeamId(Team team);
}
