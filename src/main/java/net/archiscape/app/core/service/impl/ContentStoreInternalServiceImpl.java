package net.archiscape.app.core.service.impl;

import java.util.Optional;
import java.util.UUID;
import net.archiscape.app.core.domain.ContentStoreInternal;
import net.archiscape.app.core.repository.ContentStoreInternalRepository;
import net.archiscape.app.core.service.ContentStoreInternalService;
import net.archiscape.app.core.service.dto.ContentStoreInternalDTO;
import net.archiscape.app.core.service.mapper.ContentStoreInternalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ContentStoreInternal}.
 */
@Service
@Transactional
public class ContentStoreInternalServiceImpl implements ContentStoreInternalService {

    private final Logger log = LoggerFactory.getLogger(ContentStoreInternalServiceImpl.class);

    private final ContentStoreInternalRepository contentStoreInternalRepository;

    private final ContentStoreInternalMapper contentStoreInternalMapper;

    public ContentStoreInternalServiceImpl(
        ContentStoreInternalRepository contentStoreInternalRepository,
        ContentStoreInternalMapper contentStoreInternalMapper
    ) {
        this.contentStoreInternalRepository = contentStoreInternalRepository;
        this.contentStoreInternalMapper = contentStoreInternalMapper;
    }

    @Override
    public ContentStoreInternalDTO save(ContentStoreInternalDTO contentStoreInternalDTO) {
        log.debug("Request to save ContentStoreInternal : {}", contentStoreInternalDTO);
        ContentStoreInternal contentStoreInternal = contentStoreInternalMapper.toEntity(contentStoreInternalDTO);
        contentStoreInternal = contentStoreInternalRepository.save(contentStoreInternal);
        return contentStoreInternalMapper.toDto(contentStoreInternal);
    }

    @Override
    public ContentStoreInternalDTO update(ContentStoreInternalDTO contentStoreInternalDTO) {
        log.debug("Request to save ContentStoreInternal : {}", contentStoreInternalDTO);
        ContentStoreInternal contentStoreInternal = contentStoreInternalMapper.toEntity(contentStoreInternalDTO);
        contentStoreInternal = contentStoreInternalRepository.save(contentStoreInternal);
        return contentStoreInternalMapper.toDto(contentStoreInternal);
    }

    @Override
    public Optional<ContentStoreInternalDTO> partialUpdate(ContentStoreInternalDTO contentStoreInternalDTO) {
        log.debug("Request to partially update ContentStoreInternal : {}", contentStoreInternalDTO);

        return contentStoreInternalRepository
            .findById(contentStoreInternalDTO.getId())
            .map(existingContentStoreInternal -> {
                contentStoreInternalMapper.partialUpdate(existingContentStoreInternal, contentStoreInternalDTO);

                return existingContentStoreInternal;
            })
            .map(contentStoreInternalRepository::save)
            .map(contentStoreInternalMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ContentStoreInternalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ContentStoreInternals");
        return contentStoreInternalRepository.findAll(pageable).map(contentStoreInternalMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ContentStoreInternalDTO> findOne(UUID id) {
        log.debug("Request to get ContentStoreInternal : {}", id);
        return contentStoreInternalRepository.findById(id).map(contentStoreInternalMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        log.debug("Request to delete ContentStoreInternal : {}", id);
        contentStoreInternalRepository.deleteById(id);
    }
}
