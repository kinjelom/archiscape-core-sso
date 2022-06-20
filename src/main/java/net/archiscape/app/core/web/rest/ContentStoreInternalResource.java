package net.archiscape.app.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import net.archiscape.app.core.domain.ContentStoreInternal;
import net.archiscape.app.core.repository.ContentStoreInternalRepository;
import net.archiscape.app.core.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
public class ContentStoreInternalResource {

    private final Logger log = LoggerFactory.getLogger(ContentStoreInternalResource.class);

    private static final String ENTITY_NAME = "contentStoreInternal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContentStoreInternalRepository contentStoreInternalRepository;

    public ContentStoreInternalResource(ContentStoreInternalRepository contentStoreInternalRepository) {
        this.contentStoreInternalRepository = contentStoreInternalRepository;
    }

    /**
     * {@code POST  /content-store-internals} : Create a new contentStoreInternal.
     *
     * @param contentStoreInternal the contentStoreInternal to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contentStoreInternal, or with status {@code 400 (Bad Request)} if the contentStoreInternal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/content-store-internals")
    public ResponseEntity<ContentStoreInternal> createContentStoreInternal(@Valid @RequestBody ContentStoreInternal contentStoreInternal)
        throws URISyntaxException {
        log.debug("REST request to save ContentStoreInternal : {}", contentStoreInternal);
        if (contentStoreInternal.getId() != null) {
            throw new BadRequestAlertException("A new contentStoreInternal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContentStoreInternal result = contentStoreInternalRepository.save(contentStoreInternal);
        return ResponseEntity
            .created(new URI("/api/content-store-internals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /content-store-internals/:id} : Updates an existing contentStoreInternal.
     *
     * @param id the id of the contentStoreInternal to save.
     * @param contentStoreInternal the contentStoreInternal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contentStoreInternal,
     * or with status {@code 400 (Bad Request)} if the contentStoreInternal is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contentStoreInternal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/content-store-internals/{id}")
    public ResponseEntity<ContentStoreInternal> updateContentStoreInternal(
        @PathVariable(value = "id", required = false) final UUID id,
        @Valid @RequestBody ContentStoreInternal contentStoreInternal
    ) throws URISyntaxException {
        log.debug("REST request to update ContentStoreInternal : {}, {}", id, contentStoreInternal);
        if (contentStoreInternal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contentStoreInternal.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contentStoreInternalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ContentStoreInternal result = contentStoreInternalRepository.save(contentStoreInternal);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contentStoreInternal.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /content-store-internals/:id} : Partial updates given fields of an existing contentStoreInternal, field will ignore if it is null
     *
     * @param id the id of the contentStoreInternal to save.
     * @param contentStoreInternal the contentStoreInternal to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contentStoreInternal,
     * or with status {@code 400 (Bad Request)} if the contentStoreInternal is not valid,
     * or with status {@code 404 (Not Found)} if the contentStoreInternal is not found,
     * or with status {@code 500 (Internal Server Error)} if the contentStoreInternal couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/content-store-internals/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ContentStoreInternal> partialUpdateContentStoreInternal(
        @PathVariable(value = "id", required = false) final UUID id,
        @NotNull @RequestBody ContentStoreInternal contentStoreInternal
    ) throws URISyntaxException {
        log.debug("REST request to partial update ContentStoreInternal partially : {}, {}", id, contentStoreInternal);
        if (contentStoreInternal.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, contentStoreInternal.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!contentStoreInternalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ContentStoreInternal> result = contentStoreInternalRepository
            .findById(contentStoreInternal.getId())
            .map(existingContentStoreInternal -> {
                if (contentStoreInternal.getVersion() != null) {
                    existingContentStoreInternal.setVersion(contentStoreInternal.getVersion());
                }
                if (contentStoreInternal.getInsertDate() != null) {
                    existingContentStoreInternal.setInsertDate(contentStoreInternal.getInsertDate());
                }
                if (contentStoreInternal.getFileName() != null) {
                    existingContentStoreInternal.setFileName(contentStoreInternal.getFileName());
                }
                if (contentStoreInternal.getContent() != null) {
                    existingContentStoreInternal.setContent(contentStoreInternal.getContent());
                }

                return existingContentStoreInternal;
            })
            .map(contentStoreInternalRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contentStoreInternal.getId().toString())
        );
    }

    /**
     * {@code GET  /content-store-internals} : get all the contentStoreInternals.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contentStoreInternals in body.
     */
    @GetMapping("/content-store-internals")
    public ResponseEntity<List<ContentStoreInternal>> getAllContentStoreInternals(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of ContentStoreInternals");
        Page<ContentStoreInternal> page = contentStoreInternalRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /content-store-internals/:id} : get the "id" contentStoreInternal.
     *
     * @param id the id of the contentStoreInternal to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contentStoreInternal, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/content-store-internals/{id}")
    public ResponseEntity<ContentStoreInternal> getContentStoreInternal(@PathVariable UUID id) {
        log.debug("REST request to get ContentStoreInternal : {}", id);
        Optional<ContentStoreInternal> contentStoreInternal = contentStoreInternalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(contentStoreInternal);
    }

    /**
     * {@code DELETE  /content-store-internals/:id} : delete the "id" contentStoreInternal.
     *
     * @param id the id of the contentStoreInternal to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/content-store-internals/{id}")
    public ResponseEntity<Void> deleteContentStoreInternal(@PathVariable UUID id) {
        log.debug("REST request to delete ContentStoreInternal : {}", id);
        contentStoreInternalRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
