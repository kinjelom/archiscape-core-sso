package net.archiscape.app.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import net.archiscape.app.core.repository.LandscapeElementRepository;
import net.archiscape.app.core.service.LandscapeElementService;
import net.archiscape.app.core.service.dto.LandscapeElementDTO;
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
 * REST controller for managing {@link net.archiscape.app.core.domain.LandscapeElement}.
 */
@RestController
@RequestMapping("/api")
public class LandscapeElementResource {

    private final Logger log = LoggerFactory.getLogger(LandscapeElementResource.class);

    private static final String ENTITY_NAME = "landscapeElement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LandscapeElementService landscapeElementService;

    private final LandscapeElementRepository landscapeElementRepository;

    public LandscapeElementResource(
        LandscapeElementService landscapeElementService,
        LandscapeElementRepository landscapeElementRepository
    ) {
        this.landscapeElementService = landscapeElementService;
        this.landscapeElementRepository = landscapeElementRepository;
    }

    /**
     * {@code POST  /landscape-elements} : Create a new landscapeElement.
     *
     * @param landscapeElementDTO the landscapeElementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new landscapeElementDTO, or with status {@code 400 (Bad Request)} if the landscapeElement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/landscape-elements")
    public ResponseEntity<LandscapeElementDTO> createLandscapeElement(@Valid @RequestBody LandscapeElementDTO landscapeElementDTO)
        throws URISyntaxException {
        log.debug("REST request to save LandscapeElement : {}", landscapeElementDTO);
        if (landscapeElementDTO.getId() != null) {
            throw new BadRequestAlertException("A new landscapeElement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LandscapeElementDTO result = landscapeElementService.save(landscapeElementDTO);
        return ResponseEntity
            .created(new URI("/api/landscape-elements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /landscape-elements/:id} : Updates an existing landscapeElement.
     *
     * @param id the id of the landscapeElementDTO to save.
     * @param landscapeElementDTO the landscapeElementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated landscapeElementDTO,
     * or with status {@code 400 (Bad Request)} if the landscapeElementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the landscapeElementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/landscape-elements/{id}")
    public ResponseEntity<LandscapeElementDTO> updateLandscapeElement(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody LandscapeElementDTO landscapeElementDTO
    ) throws URISyntaxException {
        log.debug("REST request to update LandscapeElement : {}, {}", id, landscapeElementDTO);
        if (landscapeElementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, landscapeElementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!landscapeElementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LandscapeElementDTO result = landscapeElementService.update(landscapeElementDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, landscapeElementDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /landscape-elements/:id} : Partial updates given fields of an existing landscapeElement, field will ignore if it is null
     *
     * @param id the id of the landscapeElementDTO to save.
     * @param landscapeElementDTO the landscapeElementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated landscapeElementDTO,
     * or with status {@code 400 (Bad Request)} if the landscapeElementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the landscapeElementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the landscapeElementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/landscape-elements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LandscapeElementDTO> partialUpdateLandscapeElement(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody LandscapeElementDTO landscapeElementDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update LandscapeElement partially : {}, {}", id, landscapeElementDTO);
        if (landscapeElementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, landscapeElementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!landscapeElementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LandscapeElementDTO> result = landscapeElementService.partialUpdate(landscapeElementDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, landscapeElementDTO.getId())
        );
    }

    /**
     * {@code GET  /landscape-elements} : get all the landscapeElements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of landscapeElements in body.
     */
    @GetMapping("/landscape-elements")
    public ResponseEntity<List<LandscapeElementDTO>> getAllLandscapeElements(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of LandscapeElements");
        Page<LandscapeElementDTO> page = landscapeElementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /landscape-elements/:id} : get the "id" landscapeElement.
     *
     * @param id the id of the landscapeElementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the landscapeElementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/landscape-elements/{id}")
    public ResponseEntity<LandscapeElementDTO> getLandscapeElement(@PathVariable String id) {
        log.debug("REST request to get LandscapeElement : {}", id);
        Optional<LandscapeElementDTO> landscapeElementDTO = landscapeElementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(landscapeElementDTO);
    }

    /**
     * {@code DELETE  /landscape-elements/:id} : delete the "id" landscapeElement.
     *
     * @param id the id of the landscapeElementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/landscape-elements/{id}")
    public ResponseEntity<Void> deleteLandscapeElement(@PathVariable String id) {
        log.debug("REST request to delete LandscapeElement : {}", id);
        landscapeElementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
