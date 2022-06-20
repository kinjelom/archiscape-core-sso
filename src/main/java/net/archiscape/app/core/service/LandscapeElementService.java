package net.archiscape.app.core.service;

import java.util.Optional;
import net.archiscape.app.core.service.dto.LandscapeElementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link net.archiscape.app.core.domain.LandscapeElement}.
 */
public interface LandscapeElementService {
    /**
     * Save a landscapeElement.
     *
     * @param landscapeElementDTO the entity to save.
     * @return the persisted entity.
     */
    LandscapeElementDTO save(LandscapeElementDTO landscapeElementDTO);

    /**
     * Updates a landscapeElement.
     *
     * @param landscapeElementDTO the entity to update.
     * @return the persisted entity.
     */
    LandscapeElementDTO update(LandscapeElementDTO landscapeElementDTO);

    /**
     * Partially updates a landscapeElement.
     *
     * @param landscapeElementDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LandscapeElementDTO> partialUpdate(LandscapeElementDTO landscapeElementDTO);

    /**
     * Get all the landscapeElements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LandscapeElementDTO> findAll(Pageable pageable);

    /**
     * Get the "id" landscapeElement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LandscapeElementDTO> findOne(String id);

    /**
     * Delete the "id" landscapeElement.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
