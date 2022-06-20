package net.archiscape.app.core.service;

import java.util.Optional;
import net.archiscape.app.core.service.dto.ProjectElementDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link net.archiscape.app.core.domain.ProjectElement}.
 */
public interface ProjectElementService {
    /**
     * Save a projectElement.
     *
     * @param projectElementDTO the entity to save.
     * @return the persisted entity.
     */
    ProjectElementDTO save(ProjectElementDTO projectElementDTO);

    /**
     * Updates a projectElement.
     *
     * @param projectElementDTO the entity to update.
     * @return the persisted entity.
     */
    ProjectElementDTO update(ProjectElementDTO projectElementDTO);

    /**
     * Partially updates a projectElement.
     *
     * @param projectElementDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProjectElementDTO> partialUpdate(ProjectElementDTO projectElementDTO);

    /**
     * Get all the projectElements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProjectElementDTO> findAll(Pageable pageable);

    /**
     * Get the "id" projectElement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProjectElementDTO> findOne(Long id);

    /**
     * Delete the "id" projectElement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
