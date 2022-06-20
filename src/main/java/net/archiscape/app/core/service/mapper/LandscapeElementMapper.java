package net.archiscape.app.core.service.mapper;

import net.archiscape.app.core.domain.Landscape;
import net.archiscape.app.core.domain.LandscapeElement;
import net.archiscape.app.core.service.dto.LandscapeDTO;
import net.archiscape.app.core.service.dto.LandscapeElementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LandscapeElement} and its DTO {@link LandscapeElementDTO}.
 */
@Mapper(componentModel = "spring")
public interface LandscapeElementMapper extends EntityMapper<LandscapeElementDTO, LandscapeElement> {
    @Mapping(target = "landscape", source = "landscape", qualifiedByName = "landscapeId")
    LandscapeElementDTO toDto(LandscapeElement s);

    @Named("landscapeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LandscapeDTO toDtoLandscapeId(Landscape landscape);
}
