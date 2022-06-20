package net.archiscape.app.core.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import net.archiscape.app.core.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProjectElementDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectElementDTO.class);
        ProjectElementDTO projectElementDTO1 = new ProjectElementDTO();
        projectElementDTO1.setId(1L);
        ProjectElementDTO projectElementDTO2 = new ProjectElementDTO();
        assertThat(projectElementDTO1).isNotEqualTo(projectElementDTO2);
        projectElementDTO2.setId(projectElementDTO1.getId());
        assertThat(projectElementDTO1).isEqualTo(projectElementDTO2);
        projectElementDTO2.setId(2L);
        assertThat(projectElementDTO1).isNotEqualTo(projectElementDTO2);
        projectElementDTO1.setId(null);
        assertThat(projectElementDTO1).isNotEqualTo(projectElementDTO2);
    }
}
