package net.archiscape.app.core.service.mapper;

import net.archiscape.app.core.domain.ContentStoreInternal;
import net.archiscape.app.core.service.dto.ContentStoreInternalDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContentStoreInternal} and its DTO {@link ContentStoreInternalDTO}.
 */
@Mapper(componentModel = "spring")
public interface ContentStoreInternalMapper extends EntityMapper<ContentStoreInternalDTO, ContentStoreInternal> {}
