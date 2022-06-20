package net.archiscape.app.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import net.archiscape.app.core.repository.LandscapeRepository;
import net.archiscape.app.core.service.LandscapeService;
import net.archiscape.app.core.service.dto.LandscapeDTO;
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
 * REST controller for managing {@link net.archiscape.app.core.domain.Landscape}.
 */
@RestController
@RequestMapping("/api")
public class LandscapeResource {

    private final Logger log = LoggerFactory.getLogger(LandscapeResource.class);

    private static final String ENTITY_NAME = "landscape";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LandscapeService landscapeService;

    private final LandscapeRepository landscapeRepository;

    public LandscapeResource(LandscapeService landscapeService, LandscapeRepository landscapeRepository) {
        this.landscapeService = landscapeService;
        this.landscapeRepository = landscapeRepository;
    }

    /**
     * {@code POST  /landscapes} : Create a new landscape.
     *
     * @param landscapeDTO the landscapeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new landscapeDTO, or with status {@code 400 (Bad Request)} if the landscape has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/landscapes")
    public ResponseEntity<LandscapeDTO> createLandscape(@Valid @RequestBody LandscapeDTO landscapeDTO) throws URISyntaxException {
        log.debug("REST request to save Landscape : {}", landscapeDTO);
        if (landscapeDTO.getId() != null) {
            throw new BadRequestAlertException("A new landscape cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LandscapeDTO result = landscapeService.save(landscapeDTO);
        return ResponseEntity
            .created(new URI("/api/landscapes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
            .body(result);
    }

    /**
     * {@code PUT  /landscapes/:id} : Updates an existing landscape.
     *
     * @param id the id of the landscapeDTO to save.
     * @param landscapeDTO the landscapeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated landscapeDTO,
     * or with status {@code 400 (Bad Request)} if the landscapeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the landscapeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/landscapes/{id}")
    public ResponseEntity<LandscapeDTO> updateLandscape(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody LandscapeDTO landscapeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Landscape : {}, {}", id, landscapeDTO);
        if (landscapeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, landscapeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!landscapeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LandscapeDTO result = landscapeService.update(landscapeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, landscapeDTO.getId()))
            .body(result);
    }

    /**
     * {@code PATCH  /landscapes/:id} : Partial updates given fields of an existing landscape, field will ignore if it is null
     *
     * @param id the id of the landscapeDTO to save.
     * @param landscapeDTO the landscapeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated landscapeDTO,
     * or with status {@code 400 (Bad Request)} if the landscapeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the landscapeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the landscapeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/landscapes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LandscapeDTO> partialUpdateLandscape(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody LandscapeDTO landscapeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Landscape partially : {}, {}", id, landscapeDTO);
        if (landscapeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, landscapeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!landscapeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LandscapeDTO> result = landscapeService.partialUpdate(landscapeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, landscapeDTO.getId())
        );
    }

    /**
     * {@code GET  /landscapes} : get all the landscapes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of landscapes in body.
     */
    @GetMapping("/landscapes")
    public ResponseEntity<List<LandscapeDTO>> getAllLandscapes(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Landscapes");
        Page<LandscapeDTO> page = landscapeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /landscapes/:id} : get the "id" landscape.
     *
     * @param id the id of the landscapeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the landscapeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/landscapes/{id}")
    public ResponseEntity<LandscapeDTO> getLandscape(@PathVariable String id) {
        log.debug("REST request to get Landscape : {}", id);
        Optional<LandscapeDTO> landscapeDTO = landscapeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(landscapeDTO);
    }

    /**
     * {@code DELETE  /landscapes/:id} : delete the "id" landscape.
     *
     * @param id the id of the landscapeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/landscapes/{id}")
    public ResponseEntity<Void> deleteLandscape(@PathVariable String id) {
        log.debug("REST request to delete Landscape : {}", id);
        landscapeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
