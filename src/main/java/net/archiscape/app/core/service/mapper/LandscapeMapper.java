package net.archiscape.app.core.service.mapper;

import net.archiscape.app.core.domain.Landscape;
import net.archiscape.app.core.service.dto.LandscapeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Landscape} and its DTO {@link LandscapeDTO}.
 */
@Mapper(componentModel = "spring")
public interface LandscapeMapper extends EntityMapper<LandscapeDTO, Landscape> {}
