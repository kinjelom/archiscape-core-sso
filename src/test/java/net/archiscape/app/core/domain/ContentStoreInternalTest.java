package net.archiscape.app.core.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import net.archiscape.app.core.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContentStoreInternalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContentStoreInternal.class);
        ContentStoreInternal contentStoreInternal1 = new ContentStoreInternal();
        contentStoreInternal1.setId(UUID.randomUUID());
        ContentStoreInternal contentStoreInternal2 = new ContentStoreInternal();
        contentStoreInternal2.setId(contentStoreInternal1.getId());
        assertThat(contentStoreInternal1).isEqualTo(contentStoreInternal2);
        contentStoreInternal2.setId(UUID.randomUUID());
        assertThat(contentStoreInternal1).isNotEqualTo(contentStoreInternal2);
        contentStoreInternal1.setId(null);
        assertThat(contentStoreInternal1).isNotEqualTo(contentStoreInternal2);
    }
}
