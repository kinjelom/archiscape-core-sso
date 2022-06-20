package net.archiscape.app.core.service.impl;

import java.util.Optional;
import net.archiscape.app.core.domain.LandscapeElement;
import net.archiscape.app.core.repository.LandscapeElementRepository;
import net.archiscape.app.core.service.LandscapeElementService;
import net.archiscape.app.core.service.dto.LandscapeElementDTO;
import net.archiscape.app.core.service.mapper.LandscapeElementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link LandscapeElement}.
 */
@Service
@Transactional
public class LandscapeElementServiceImpl implements LandscapeElementService {

    private final Logger log = LoggerFactory.getLogger(LandscapeElementServiceImpl.class);

    private final LandscapeElementRepository landscapeElementRepository;

    private final LandscapeElementMapper landscapeElementMapper;

    public LandscapeElementServiceImpl(
        LandscapeElementRepository landscapeElementRepository,
        LandscapeElementMapper landscapeElementMapper
    ) {
        this.landscapeElementRepository = landscapeElementRepository;
        this.landscapeElementMapper = landscapeElementMapper;
    }

    @Override
    public LandscapeElementDTO save(LandscapeElementDTO landscapeElementDTO) {
        log.debug("Request to save LandscapeElement : {}", landscapeElementDTO);
        LandscapeElement landscapeElement = landscapeElementMapper.toEntity(landscapeElementDTO);
        landscapeElement = landscapeElementRepository.save(landscapeElement);
        return landscapeElementMapper.toDto(landscapeElement);
    }

    @Override
    public LandscapeElementDTO update(LandscapeElementDTO landscapeElementDTO) {
        log.debug("Request to save LandscapeElement : {}", landscapeElementDTO);
        LandscapeElement landscapeElement = landscapeElementMapper.toEntity(landscapeElementDTO);
        landscapeElement.setIsPersisted();
        landscapeElement = landscapeElementRepository.save(landscapeElement);
        return landscapeElementMapper.toDto(landscapeElement);
    }

    @Override
    public Optional<LandscapeElementDTO> partialUpdate(LandscapeElementDTO landscapeElementDTO) {
        log.debug("Request to partially update LandscapeElement : {}", landscapeElementDTO);

        return landscapeElementRepository
            .findById(landscapeElementDTO.getId())
            .map(existingLandscapeElement -> {
                landscapeElementMapper.partialUpdate(existingLandscapeElement, landscapeElementDTO);

                return existingLandscapeElement;
            })
            .map(landscapeElementRepository::save)
            .map(landscapeElementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LandscapeElementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LandscapeElements");
        return landscapeElementRepository.findAll(pageable).map(landscapeElementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LandscapeElementDTO> findOne(String id) {
        log.debug("Request to get LandscapeElement : {}", id);
        return landscapeElementRepository.findById(id).map(landscapeElementMapper::toDto);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete LandscapeElement : {}", id);
        landscapeElementRepository.deleteById(id);
    }
}
