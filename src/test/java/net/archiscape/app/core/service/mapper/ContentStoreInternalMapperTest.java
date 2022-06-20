package net.archiscape.app.core.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ContentStoreInternalMapperTest {

    private ContentStoreInternalMapper contentStoreInternalMapper;

    @BeforeEach
    public void setUp() {
        contentStoreInternalMapper = new ContentStoreInternalMapperImpl();
    }
}
