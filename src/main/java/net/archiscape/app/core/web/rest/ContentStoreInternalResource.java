package net.archiscape.app.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import net.archiscape.app.core.repository.ContentStoreInternalRepository;
import net.archiscape.app.core.service.ContentStoreInternalService;
import net.archiscape.app.core.service.dto.ContentStoreInternalDTO;
import net.archiscape.app.core.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link net.archiscape.app.core.domain.ContentStoreInternal}.
 */
@RestController
@RequestMapping("/api")
public class ContentStoreInternalResource {

    private final Logger log = LoggerFactory.getLogger(ContentStoreInternalResource.class);

    private static final String ENTITY_NAME = "contentStoreInternal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContentStoreInternalService contentStoreInternalService;

    private final ContentStoreInternalRepository contentStoreInternalRepository;

    public ContentStoreInternalResource(
        ContentStoreInternalService contentStoreInternalService,
        ContentStoreInternalRepository contentStoreInternalRepository
    ) {
        this.contentStoreInternalService = contentStoreInternalService;
        this.contentStoreInternalRepository = contentStoreInternalRepository;
    }

    /**
     * {@code POST  /content-store-internals} : Create a new contentStoreInternal.
     *
     * @param contentStoreInternalDTO the contentStoreInternalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contentStoreInternalDTO, or with status {@code 400 (Bad Request)} if the contentStoreInternal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/content-store-internals")
    public ResponseEntity<ContentStoreInternalDTO> createContentStoreInternal(
        @Valid @RequestBody ContentStoreInternalDTO contentStoreInternalDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ContentStoreInternal : {}", contentStoreInternalDTO);
        if (contentStoreInternalDTO.getId() != null) {
            throw new BadRequestAlertException("A new contentStoreInternal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContentStoreInternalDTO result = contentStoreInternalService.save(contentStoreInternalDTO);
        return ResponseEntity
            .created(new URI("/api/content-store-internals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /content-store-internals/:id} : Updates an existing contentStoreInternal.
     *
     * @param id the id of the contentStoreInternalDTO to save.
     * @param contentStoreInternalDTO the contentStoreInternalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contentStoreInternalDTO,
     * or with status {@code 400 (Bad Request)} if the contentStoreInternalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contentStoreInternalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/content-store-internals/{id}")
    public ResponseEntity<ContentStoreInternalDTO> updateContentStoreInternal(
        @PathVariable(value = "id", required = false) final UUID id,
        @Valid @RequestBody ContentStoreInternalDTO contentStoreInternalDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ContentStoreInternal : {}, {}", id, contentStoreInternalDTO);
        if (contentStoreInternalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contentStoreInternalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contentStoreInternalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ContentStoreInternalDTO result = contentStoreInternalService.update(contentStoreInternalDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contentStoreInternalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /content-store-internals/:id} : Partial updates given fields of an existing contentStoreInternal, field will ignore if it is null
     *
     * @param id the id of the contentStoreInternalDTO to save.
     * @param contentStoreInternalDTO the contentStoreInternalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contentStoreInternalDTO,
     * or with status {@code 400 (Bad Request)} if the contentStoreInternalDTO is not valid,
     * or with status {@code 404 (Not Found)} if the contentStoreInternalDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the contentStoreInternalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/content-store-internals/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContentStoreInternalDTO> partialUpdateContentStoreInternal(
        @PathVariable(value = "id", required = false) final UUID id,
        @NotNull @RequestBody ContentStoreInternalDTO contentStoreInternalDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContentStoreInternal partially : {}, {}", id, contentStoreInternalDTO);
        if (contentStoreInternalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contentStoreInternalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contentStoreInternalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContentStoreInternalDTO> result = contentStoreInternalService.partialUpdate(contentStoreInternalDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contentStoreInternalDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /content-store-internals} : get all the contentStoreInternals.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contentStoreInternals in body.
     */
    @GetMapping("/content-store-internals")
    public ResponseEntity<List<ContentStoreInternalDTO>> getAllContentStoreInternals(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ContentStoreInternals");
        Page<ContentStoreInternalDTO> page = contentStoreInternalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /content-store-internals/:id} : get the "id" contentStoreInternal.
     *
     * @param id the id of the contentStoreInternalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contentStoreInternalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/content-store-internals/{id}")
    public ResponseEntity<ContentStoreInternalDTO> getContentStoreInternal(@PathVariable UUID id) {
        log.debug("REST request to get ContentStoreInternal : {}", id);
        Optional<ContentStoreInternalDTO> contentStoreInternalDTO = contentStoreInternalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contentStoreInternalDTO);
    }

    /**
     * {@code DELETE  /content-store-internals/:id} : delete the "id" contentStoreInternal.
     *
     * @param id the id of the contentStoreInternalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/content-store-internals/{id}")
    public ResponseEntity<Void> deleteContentStoreInternal(@PathVariable UUID id) {
        log.debug("REST request to delete ContentStoreInternal : {}", id);
        contentStoreInternalService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
