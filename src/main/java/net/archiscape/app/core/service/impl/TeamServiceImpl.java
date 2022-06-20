package net.archiscape.app.core.service.impl;

import java.util.Optional;
import net.archiscape.app.core.domain.Team;
import net.archiscape.app.core.repository.TeamRepository;
import net.archiscape.app.core.service.TeamService;
import net.archiscape.app.core.service.dto.TeamDTO;
import net.archiscape.app.core.service.mapper.TeamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Team}.
 */
@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    private final Logger log = LoggerFactory.getLogger(TeamServiceImpl.class);

    private final TeamRepository teamRepository;

    private final TeamMapper teamMapper;

    public TeamServiceImpl(TeamRepository teamRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public TeamDTO save(TeamDTO teamDTO) {
        log.debug("Request to save Team : {}", teamDTO);
        Team team = teamMapper.toEntity(teamDTO);
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    @Override
    public TeamDTO update(TeamDTO teamDTO) {
        log.debug("Request to save Team : {}", teamDTO);
        Team team = teamMapper.toEntity(teamDTO);
        team.setIsPersisted();
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    @Override
    public Optional<TeamDTO> partialUpdate(TeamDTO teamDTO) {
        log.debug("Request to partially update Team : {}", teamDTO);

        return teamRepository
            .findById(teamDTO.getId())
            .map(existingTeam -> {
                teamMapper.partialUpdate(existingTeam, teamDTO);

                return existingTeam;
            })
            .map(teamRepository::save)
            .map(teamMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TeamDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Teams");
        return teamRepository.findAll(pageable).map(teamMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TeamDTO> findOne(String id) {
        log.debug("Request to get Team : {}", id);
        return teamRepository.findById(id).map(teamMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Team : {}", id);
        teamRepository.deleteById(id);
    }
}
