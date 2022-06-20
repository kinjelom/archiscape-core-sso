package net.archiscape.app.core.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import net.archiscape.app.core.IntegrationTest;
import net.archiscape.app.core.domain.ContentStoreInternal;
import net.archiscape.app.core.repository.ContentStoreInternalRepository;
import net.archiscape.app.core.service.dto.ContentStoreInternalDTO;
import net.archiscape.app.core.service.mapper.ContentStoreInternalMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link ContentStoreInternalResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContentStoreInternalResourceIT {

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final LocalDate DEFAULT_INSERT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_INSERT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/content-store-internals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ContentStoreInternalRepository contentStoreInternalRepository;

    @Autowired
    private ContentStoreInternalMapper contentStoreInternalMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContentStoreInternalMockMvc;

    private ContentStoreInternal contentStoreInternal;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContentStoreInternal createEntity(EntityManager em) {
        ContentStoreInternal contentStoreInternal = new ContentStoreInternal()
            .version(DEFAULT_VERSION)
            .insertDate(DEFAULT_INSERT_DATE)
            .fileName(DEFAULT_FILE_NAME)
            .content(DEFAULT_CONTENT);
        return contentStoreInternal;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContentStoreInternal createUpdatedEntity(EntityManager em) {
        ContentStoreInternal contentStoreInternal = new ContentStoreInternal()
            .version(UPDATED_VERSION)
            .insertDate(UPDATED_INSERT_DATE)
            .fileName(UPDATED_FILE_NAME)
            .content(UPDATED_CONTENT);
        return contentStoreInternal;
    }

    @BeforeEach
    public void initTest() {
        contentStoreInternal = createEntity(em);
    }

    @Test
    @Transactional
    void createContentStoreInternal() throws Exception {
        int databaseSizeBeforeCreate = contentStoreInternalRepository.findAll().size();
        // Create the ContentStoreInternal
        ContentStoreInternalDTO contentStoreInternalDTO = contentStoreInternalMapper.toDto(contentStoreInternal);
        restContentStoreInternalMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentStoreInternalDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ContentStoreInternal in the database
        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeCreate + 1);
        ContentStoreInternal testContentStoreInternal = contentStoreInternalList.get(contentStoreInternalList.size() - 1);
        assertThat(testContentStoreInternal.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testContentStoreInternal.getInsertDate()).isEqualTo(DEFAULT_INSERT_DATE);
        assertThat(testContentStoreInternal.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testContentStoreInternal.getContent()).isEqualTo(DEFAULT_CONTENT);
    }

    @Test
    @Transactional
    void createContentStoreInternalWithExistingId() throws Exception {
        // Create the ContentStoreInternal with an existing ID
        contentStoreInternalRepository.saveAndFlush(contentStoreInternal);
        ContentStoreInternalDTO contentStoreInternalDTO = contentStoreInternalMapper.toDto(contentStoreInternal);

        int databaseSizeBeforeCreate = contentStoreInternalRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContentStoreInternalMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentStoreInternalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContentStoreInternal in the database
        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkVersionIsRequired() throws Exception {
        int databaseSizeBeforeTest = contentStoreInternalRepository.findAll().size();
        // set the field null
        contentStoreInternal.setVersion(null);

        // Create the ContentStoreInternal, which fails.
        ContentStoreInternalDTO contentStoreInternalDTO = contentStoreInternalMapper.toDto(contentStoreInternal);

        restContentStoreInternalMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentStoreInternalDTO))
            )
            .andExpect(status().isBadRequest());

        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkInsertDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = contentStoreInternalRepository.findAll().size();
        // set the field null
        contentStoreInternal.setInsertDate(null);

        // Create the ContentStoreInternal, which fails.
        ContentStoreInternalDTO contentStoreInternalDTO = contentStoreInternalMapper.toDto(contentStoreInternal);

        restContentStoreInternalMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentStoreInternalDTO))
            )
            .andExpect(status().isBadRequest());

        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllContentStoreInternals() throws Exception {
        // Initialize the database
        contentStoreInternalRepository.saveAndFlush(contentStoreInternal);

        // Get all the contentStoreInternalList
        restContentStoreInternalMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contentStoreInternal.getId().toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].insertDate").value(hasItem(DEFAULT_INSERT_DATE.toString())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())));
    }

    @Test
    @Transactional
    void getContentStoreInternal() throws Exception {
        // Initialize the database
        contentStoreInternalRepository.saveAndFlush(contentStoreInternal);

        // Get the contentStoreInternal
        restContentStoreInternalMockMvc
            .perform(get(ENTITY_API_URL_ID, contentStoreInternal.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contentStoreInternal.getId().toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.insertDate").value(DEFAULT_INSERT_DATE.toString()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingContentStoreInternal() throws Exception {
        // Get the contentStoreInternal
        restContentStoreInternalMockMvc.perform(get(ENTITY_API_URL_ID, UUID.randomUUID().toString())).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewContentStoreInternal() throws Exception {
        // Initialize the database
        contentStoreInternalRepository.saveAndFlush(contentStoreInternal);

        int databaseSizeBeforeUpdate = contentStoreInternalRepository.findAll().size();

        // Update the contentStoreInternal
        ContentStoreInternal updatedContentStoreInternal = contentStoreInternalRepository.findById(contentStoreInternal.getId()).get();
        // Disconnect from session so that the updates on updatedContentStoreInternal are not directly saved in db
        em.detach(updatedContentStoreInternal);
        updatedContentStoreInternal
            .version(UPDATED_VERSION)
            .insertDate(UPDATED_INSERT_DATE)
            .fileName(UPDATED_FILE_NAME)
            .content(UPDATED_CONTENT);
        ContentStoreInternalDTO contentStoreInternalDTO = contentStoreInternalMapper.toDto(updatedContentStoreInternal);

        restContentStoreInternalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contentStoreInternalDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentStoreInternalDTO))
            )
            .andExpect(status().isOk());

        // Validate the ContentStoreInternal in the database
        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeUpdate);
        ContentStoreInternal testContentStoreInternal = contentStoreInternalList.get(contentStoreInternalList.size() - 1);
        assertThat(testContentStoreInternal.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testContentStoreInternal.getInsertDate()).isEqualTo(UPDATED_INSERT_DATE);
        assertThat(testContentStoreInternal.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testContentStoreInternal.getContent()).isEqualTo(UPDATED_CONTENT);
    }

    @Test
    @Transactional
    void putNonExistingContentStoreInternal() throws Exception {
        int databaseSizeBeforeUpdate = contentStoreInternalRepository.findAll().size();
        contentStoreInternal.setId(UUID.randomUUID());

        // Create the ContentStoreInternal
        ContentStoreInternalDTO contentStoreInternalDTO = contentStoreInternalMapper.toDto(contentStoreInternal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContentStoreInternalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contentStoreInternalDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentStoreInternalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContentStoreInternal in the database
        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContentStoreInternal() throws Exception {
        int databaseSizeBeforeUpdate = contentStoreInternalRepository.findAll().size();
        contentStoreInternal.setId(UUID.randomUUID());

        // Create the ContentStoreInternal
        ContentStoreInternalDTO contentStoreInternalDTO = contentStoreInternalMapper.toDto(contentStoreInternal);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContentStoreInternalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentStoreInternalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContentStoreInternal in the database
        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContentStoreInternal() throws Exception {
        int databaseSizeBeforeUpdate = contentStoreInternalRepository.findAll().size();
        contentStoreInternal.setId(UUID.randomUUID());

        // Create the ContentStoreInternal
        ContentStoreInternalDTO contentStoreInternalDTO = contentStoreInternalMapper.toDto(contentStoreInternal);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContentStoreInternalMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(contentStoreInternalDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContentStoreInternal in the database
        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContentStoreInternalWithPatch() throws Exception {
        // Initialize the database
        contentStoreInternalRepository.saveAndFlush(contentStoreInternal);

        int databaseSizeBeforeUpdate = contentStoreInternalRepository.findAll().size();

        // Update the contentStoreInternal using partial update
        ContentStoreInternal partialUpdatedContentStoreInternal = new ContentStoreInternal();
        partialUpdatedContentStoreInternal.setId(contentStoreInternal.getId());

        partialUpdatedContentStoreInternal.version(UPDATED_VERSION).insertDate(UPDATED_INSERT_DATE);

        restContentStoreInternalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContentStoreInternal.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContentStoreInternal))
            )
            .andExpect(status().isOk());

        // Validate the ContentStoreInternal in the database
        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeUpdate);
        ContentStoreInternal testContentStoreInternal = contentStoreInternalList.get(contentStoreInternalList.size() - 1);
        assertThat(testContentStoreInternal.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testContentStoreInternal.getInsertDate()).isEqualTo(UPDATED_INSERT_DATE);
        assertThat(testContentStoreInternal.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testContentStoreInternal.getContent()).isEqualTo(DEFAULT_CONTENT);
    }

    @Test
    @Transactional
    void fullUpdateContentStoreInternalWithPatch() throws Exception {
        // Initialize the database
        contentStoreInternalRepository.saveAndFlush(contentStoreInternal);

        int databaseSizeBeforeUpdate = contentStoreInternalRepository.findAll().size();

        // Update the contentStoreInternal using partial update
        ContentStoreInternal partialUpdatedContentStoreInternal = new ContentStoreInternal();
        partialUpdatedContentStoreInternal.setId(contentStoreInternal.getId());

        partialUpdatedContentStoreInternal
            .version(UPDATED_VERSION)
            .insertDate(UPDATED_INSERT_DATE)
            .fileName(UPDATED_FILE_NAME)
            .content(UPDATED_CONTENT);

        restContentStoreInternalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContentStoreInternal.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedContentStoreInternal))
            )
            .andExpect(status().isOk());

        // Validate the ContentStoreInternal in the database
        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeUpdate);
        ContentStoreInternal testContentStoreInternal = contentStoreInternalList.get(contentStoreInternalList.size() - 1);
        assertThat(testContentStoreInternal.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testContentStoreInternal.getInsertDate()).isEqualTo(UPDATED_INSERT_DATE);
        assertThat(testContentStoreInternal.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testContentStoreInternal.getContent()).isEqualTo(UPDATED_CONTENT);
    }

    @Test
    @Transactional
    void patchNonExistingContentStoreInternal() throws Exception {
        int databaseSizeBeforeUpdate = contentStoreInternalRepository.findAll().size();
        contentStoreInternal.setId(UUID.randomUUID());

        // Create the ContentStoreInternal
        ContentStoreInternalDTO contentStoreInternalDTO = contentStoreInternalMapper.toDto(contentStoreInternal);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContentStoreInternalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contentStoreInternalDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contentStoreInternalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContentStoreInternal in the database
        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContentStoreInternal() throws Exception {
        int databaseSizeBeforeUpdate = contentStoreInternalRepository.findAll().size();
        contentStoreInternal.setId(UUID.randomUUID());

        // Create the ContentStoreInternal
        ContentStoreInternalDTO contentStoreInternalDTO = contentStoreInternalMapper.toDto(contentStoreInternal);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContentStoreInternalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contentStoreInternalDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContentStoreInternal in the database
        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContentStoreInternal() throws Exception {
        int databaseSizeBeforeUpdate = contentStoreInternalRepository.findAll().size();
        contentStoreInternal.setId(UUID.randomUUID());

        // Create the ContentStoreInternal
        ContentStoreInternalDTO contentStoreInternalDTO = contentStoreInternalMapper.toDto(contentStoreInternal);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContentStoreInternalMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(contentStoreInternalDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContentStoreInternal in the database
        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContentStoreInternal() throws Exception {
        // Initialize the database
        contentStoreInternalRepository.saveAndFlush(contentStoreInternal);

        int databaseSizeBeforeDelete = contentStoreInternalRepository.findAll().size();

        // Delete the contentStoreInternal
        restContentStoreInternalMockMvc
            .perform(delete(ENTITY_API_URL_ID, contentStoreInternal.getId().toString()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContentStoreInternal> contentStoreInternalList = contentStoreInternalRepository.findAll();
        assertThat(contentStoreInternalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
