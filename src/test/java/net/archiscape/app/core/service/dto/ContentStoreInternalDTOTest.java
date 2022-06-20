package net.archiscape.app.core.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import net.archiscape.app.core.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContentStoreInternalDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContentStoreInternalDTO.class);
        ContentStoreInternalDTO contentStoreInternalDTO1 = new ContentStoreInternalDTO();
        contentStoreInternalDTO1.setId(UUID.randomUUID());
        ContentStoreInternalDTO contentStoreInternalDTO2 = new ContentStoreInternalDTO();
        assertThat(contentStoreInternalDTO1).isNotEqualTo(contentStoreInternalDTO2);
        contentStoreInternalDTO2.setId(contentStoreInternalDTO1.getId());
        assertThat(contentStoreInternalDTO1).isEqualTo(contentStoreInternalDTO2);
        contentStoreInternalDTO2.setId(UUID.randomUUID());
        assertThat(contentStoreInternalDTO1).isNotEqualTo(contentStoreInternalDTO2);
        contentStoreInternalDTO1.setId(null);
        assertThat(contentStoreInternalDTO1).isNotEqualTo(contentStoreInternalDTO2);
    }
}
