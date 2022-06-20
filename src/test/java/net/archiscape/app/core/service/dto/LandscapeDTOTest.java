package net.archiscape.app.core.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import net.archiscape.app.core.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LandscapeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LandscapeDTO.class);
        LandscapeDTO landscapeDTO1 = new LandscapeDTO();
        landscapeDTO1.setId("id1");
        LandscapeDTO landscapeDTO2 = new LandscapeDTO();
        assertThat(landscapeDTO1).isNotEqualTo(landscapeDTO2);
        landscapeDTO2.setId(landscapeDTO1.getId());
        assertThat(landscapeDTO1).isEqualTo(landscapeDTO2);
        landscapeDTO2.setId("id2");
        assertThat(landscapeDTO1).isNotEqualTo(landscapeDTO2);
        landscapeDTO1.setId(null);
        assertThat(landscapeDTO1).isNotEqualTo(landscapeDTO2);
    }
}
