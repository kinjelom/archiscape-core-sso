package net.archiscape.app.core.service;

import java.util.Optional;
import java.util.UUID;
import net.archiscape.app.core.service.dto.ContentStoreInternalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link net.archiscape.app.core.domain.ContentStoreInternal}.
 */
public interface ContentStoreInternalService {
    /**
     * Save a contentStoreInternal.
     *
     * @param contentStoreInternalDTO the entity to save.
     * @return the persisted entity.
     */
    ContentStoreInternalDTO save(ContentStoreInternalDTO contentStoreInternalDTO);

    /**
     * Updates a contentStoreInternal.
     *
     * @param contentStoreInternalDTO the entity to update.
     * @return the persisted entity.
     */
    ContentStoreInternalDTO update(ContentStoreInternalDTO contentStoreInternalDTO);

    /**
     * Partially updates a contentStoreInternal.
     *
     * @param contentStoreInternalDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ContentStoreInternalDTO> partialUpdate(ContentStoreInternalDTO contentStoreInternalDTO);

    /**
     * Get all the contentStoreInternals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ContentStoreInternalDTO> findAll(Pageable pageable);

    /**
     * Get the "id" contentStoreInternal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContentStoreInternalDTO> findOne(UUID id);

    /**
     * Delete the "id" contentStoreInternal.
     *
     * @param id the id of the entity.
     */
    void delete(UUID id);
}
