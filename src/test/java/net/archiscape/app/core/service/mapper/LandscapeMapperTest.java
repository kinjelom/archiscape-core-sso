package net.archiscape.app.core.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LandscapeMapperTest {

    private LandscapeMapper landscapeMapper;

    @BeforeEach
    public void setUp() {
        landscapeMapper = new LandscapeMapperImpl();
    }
}
