package net.archiscape.app.core.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import net.archiscape.app.core.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LandscapeElementDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LandscapeElementDTO.class);
        LandscapeElementDTO landscapeElementDTO1 = new LandscapeElementDTO();
        landscapeElementDTO1.setId("id1");
        LandscapeElementDTO landscapeElementDTO2 = new LandscapeElementDTO();
        assertThat(landscapeElementDTO1).isNotEqualTo(landscapeElementDTO2);
        landscapeElementDTO2.setId(landscapeElementDTO1.getId());
        assertThat(landscapeElementDTO1).isEqualTo(landscapeElementDTO2);
        landscapeElementDTO2.setId("id2");
        assertThat(landscapeElementDTO1).isNotEqualTo(landscapeElementDTO2);
        landscapeElementDTO1.setId(null);
        assertThat(landscapeElementDTO1).isNotEqualTo(landscapeElementDTO2);
    }
}
