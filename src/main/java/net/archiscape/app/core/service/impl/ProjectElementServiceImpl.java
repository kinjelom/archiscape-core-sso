package net.archiscape.app.core.service.impl;

import java.util.Optional;
import net.archiscape.app.core.domain.ProjectElement;
import net.archiscape.app.core.repository.ProjectElementRepository;
import net.archiscape.app.core.service.ProjectElementService;
import net.archiscape.app.core.service.dto.ProjectElementDTO;
import net.archiscape.app.core.service.mapper.ProjectElementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ProjectElement}.
 */
@Service
@Transactional
public class ProjectElementServiceImpl implements ProjectElementService {

    private final Logger log = LoggerFactory.getLogger(ProjectElementServiceImpl.class);

    private final ProjectElementRepository projectElementRepository;

    private final ProjectElementMapper projectElementMapper;

    public ProjectElementServiceImpl(ProjectElementRepository projectElementRepository, ProjectElementMapper projectElementMapper) {
        this.projectElementRepository = projectElementRepository;
        this.projectElementMapper = projectElementMapper;
    }

    @Override
    public ProjectElementDTO save(ProjectElementDTO projectElementDTO) {
        log.debug("Request to save ProjectElement : {}", projectElementDTO);
        ProjectElement projectElement = projectElementMapper.toEntity(projectElementDTO);
        projectElement = projectElementRepository.save(projectElement);
        return projectElementMapper.toDto(projectElement);
    }

    @Override
    public ProjectElementDTO update(ProjectElementDTO projectElementDTO) {
        log.debug("Request to save ProjectElement : {}", projectElementDTO);
        ProjectElement projectElement = projectElementMapper.toEntity(projectElementDTO);
        projectElement = projectElementRepository.save(projectElement);
        return projectElementMapper.toDto(projectElement);
    }

    @Override
    public Optional<ProjectElementDTO> partialUpdate(ProjectElementDTO projectElementDTO) {
        log.debug("Request to partially update ProjectElement : {}", projectElementDTO);

        return projectElementRepository
            .findById(projectElementDTO.getId())
            .map(existingProjectElement -> {
                projectElementMapper.partialUpdate(existingProjectElement, projectElementDTO);

                return existingProjectElement;
            })
            .map(projectElementRepository::save)
            .map(projectElementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProjectElementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProjectElements");
        return projectElementRepository.findAll(pageable).map(projectElementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProjectElementDTO> findOne(Long id) {
        log.debug("Request to get ProjectElement : {}", id);
        return projectElementRepository.findById(id).map(projectElementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjectElement : {}", id);
        projectElementRepository.deleteById(id);
    }
}
