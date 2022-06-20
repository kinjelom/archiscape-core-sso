package net.archiscape.app.core.service.mapper;

import net.archiscape.app.core.domain.Team;
import net.archiscape.app.core.service.dto.TeamDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Team} and its DTO {@link TeamDTO}.
 */
@Mapper(componentModel = "spring")
public interface TeamMapper extends EntityMapper<TeamDTO, Team> {}
