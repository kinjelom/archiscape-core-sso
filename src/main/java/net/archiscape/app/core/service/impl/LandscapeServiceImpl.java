package net.archiscape.app.core.service.impl;

import java.util.Optional;
import net.archiscape.app.core.domain.Landscape;
import net.archiscape.app.core.repository.LandscapeRepository;
import net.archiscape.app.core.service.LandscapeService;
import net.archiscape.app.core.service.dto.LandscapeDTO;
import net.archiscape.app.core.service.mapper.LandscapeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Landscape}.
 */
@Service
@Transactional
public class LandscapeServiceImpl implements LandscapeService {

    private final Logger log = LoggerFactory.getLogger(LandscapeServiceImpl.class);

    private final LandscapeRepository landscapeRepository;

    private final LandscapeMapper landscapeMapper;

    public LandscapeServiceImpl(LandscapeRepository landscapeRepository, LandscapeMapper landscapeMapper) {
        this.landscapeRepository = landscapeRepository;
        this.landscapeMapper = landscapeMapper;
    }

    @Override
    public LandscapeDTO save(LandscapeDTO landscapeDTO) {
        log.debug("Request to save Landscape : {}", landscapeDTO);
        Landscape landscape = landscapeMapper.toEntity(landscapeDTO);
        landscape = landscapeRepository.save(landscape);
        return landscapeMapper.toDto(landscape);
    }

    @Override
    public LandscapeDTO update(LandscapeDTO landscapeDTO) {
        log.debug("Request to save Landscape : {}", landscapeDTO);
        Landscape landscape = landscapeMapper.toEntity(landscapeDTO);
        landscape.setIsPersisted();
        landscape = landscapeRepository.save(landscape);
        return landscapeMapper.toDto(landscape);
    }

    @Override
    public Optional<LandscapeDTO> partialUpdate(LandscapeDTO landscapeDTO) {
        log.debug("Request to partially update Landscape : {}", landscapeDTO);

        return landscapeRepository
            .findById(landscapeDTO.getId())
            .map(existingLandscape -> {
                landscapeMapper.partialUpdate(existingLandscape, landscapeDTO);

                return existingLandscape;
            })
            .map(landscapeRepository::save)
            .map(landscapeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LandscapeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Landscapes");
        return landscapeRepository.findAll(pageable).map(landscapeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LandscapeDTO> findOne(String id) {
        log.debug("Request to get Landscape : {}", id);
        return landscapeRepository.findById(id).map(landscapeMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Landscape : {}", id);
        landscapeRepository.deleteById(id);
    }
}
