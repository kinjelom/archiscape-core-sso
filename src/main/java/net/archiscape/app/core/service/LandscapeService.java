package net.archiscape.app.core.service;

import java.util.Optional;
import net.archiscape.app.core.service.dto.LandscapeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link net.archiscape.app.core.domain.Landscape}.
 */
public interface LandscapeService {
    /**
     * Save a landscape.
     *
     * @param landscapeDTO the entity to save.
     * @return the persisted entity.
     */
    LandscapeDTO save(LandscapeDTO landscapeDTO);

    /**
     * Updates a landscape.
     *
     * @param landscapeDTO the entity to update.
     * @return the persisted entity.
     */
    LandscapeDTO update(LandscapeDTO landscapeDTO);

    /**
     * Partially updates a landscape.
     *
     * @param landscapeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LandscapeDTO> partialUpdate(LandscapeDTO landscapeDTO);

    /**
     * Get all the landscapes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LandscapeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" landscape.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LandscapeDTO> findOne(String id);

    /**
     * Delete the "id" landscape.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
