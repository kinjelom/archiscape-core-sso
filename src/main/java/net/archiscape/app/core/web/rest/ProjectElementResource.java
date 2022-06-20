package net.archiscape.app.core.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import net.archiscape.app.core.repository.ProjectElementRepository;
import net.archiscape.app.core.service.ProjectElementService;
import net.archiscape.app.core.service.dto.ProjectElementDTO;
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
 * REST controller for managing {@link net.archiscape.app.core.domain.ProjectElement}.
 */
@RestController
@RequestMapping("/api")
public class ProjectElementResource {

    private final Logger log = LoggerFactory.getLogger(ProjectElementResource.class);

    private static final String ENTITY_NAME = "projectElement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProjectElementService projectElementService;

    private final ProjectElementRepository projectElementRepository;

    public ProjectElementResource(ProjectElementService projectElementService, ProjectElementRepository projectElementRepository) {
        this.projectElementService = projectElementService;
        this.projectElementRepository = projectElementRepository;
    }

    /**
     * {@code POST  /project-elements} : Create a new projectElement.
     *
     * @param projectElementDTO the projectElementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new projectElementDTO, or with status {@code 400 (Bad Request)} if the projectElement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/project-elements")
    public ResponseEntity<ProjectElementDTO> createProjectElement(@Valid @RequestBody ProjectElementDTO projectElementDTO)
        throws URISyntaxException {
        log.debug("REST request to save ProjectElement : {}", projectElementDTO);
        if (projectElementDTO.getId() != null) {
            throw new BadRequestAlertException("A new projectElement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectElementDTO result = projectElementService.save(projectElementDTO);
        return ResponseEntity
            .created(new URI("/api/project-elements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /project-elements/:id} : Updates an existing projectElement.
     *
     * @param id the id of the projectElementDTO to save.
     * @param projectElementDTO the projectElementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectElementDTO,
     * or with status {@code 400 (Bad Request)} if the projectElementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the projectElementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/project-elements/{id}")
    public ResponseEntity<ProjectElementDTO> updateProjectElement(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProjectElementDTO projectElementDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ProjectElement : {}, {}", id, projectElementDTO);
        if (projectElementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectElementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectElementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ProjectElementDTO result = projectElementService.update(projectElementDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectElementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /project-elements/:id} : Partial updates given fields of an existing projectElement, field will ignore if it is null
     *
     * @param id the id of the projectElementDTO to save.
     * @param projectElementDTO the projectElementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated projectElementDTO,
     * or with status {@code 400 (Bad Request)} if the projectElementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the projectElementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the projectElementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/project-elements/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ProjectElementDTO> partialUpdateProjectElement(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProjectElementDTO projectElementDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProjectElement partially : {}, {}", id, projectElementDTO);
        if (projectElementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, projectElementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!projectElementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ProjectElementDTO> result = projectElementService.partialUpdate(projectElementDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, projectElementDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /project-elements} : get all the projectElements.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of projectElements in body.
     */
    @GetMapping("/project-elements")
    public ResponseEntity<List<ProjectElementDTO>> getAllProjectElements(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of ProjectElements");
        Page<ProjectElementDTO> page = projectElementService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /project-elements/:id} : get the "id" projectElement.
     *
     * @param id the id of the projectElementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the projectElementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/project-elements/{id}")
    public ResponseEntity<ProjectElementDTO> getProjectElement(@PathVariable Long id) {
        log.debug("REST request to get ProjectElement : {}", id);
        Optional<ProjectElementDTO> projectElementDTO = projectElementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(projectElementDTO);
    }

    /**
     * {@code DELETE  /project-elements/:id} : delete the "id" projectElement.
     *
     * @param id the id of the projectElementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/project-elements/{id}")
    public ResponseEntity<Void> deleteProjectElement(@PathVariable Long id) {
        log.debug("REST request to delete ProjectElement : {}", id);
        projectElementService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
