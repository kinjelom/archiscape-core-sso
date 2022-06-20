package net.archiscape.app.core.service;

import java.util.Optional;
import net.archiscape.app.core.service.dto.TeamDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link net.archiscape.app.core.domain.Team}.
 */
public interface TeamService {
    /**
     * Save a team.
     *
     * @param teamDTO the entity to save.
     * @return the persisted entity.
     */
    TeamDTO save(TeamDTO teamDTO);

    /**
     * Updates a team.
     *
     * @param teamDTO the entity to update.
     * @return the persisted entity.
     */
    TeamDTO update(TeamDTO teamDTO);

    /**
     * Partially updates a team.
     *
     * @param teamDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TeamDTO> partialUpdate(TeamDTO teamDTO);

    /**
     * Get all the teams.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TeamDTO> findAll(Pageable pageable);

    /**
     * Get the "id" team.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TeamDTO> findOne(String id);

    /**
     * Delete the "id" team.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
