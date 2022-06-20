package net.archiscape.app.core.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProjectElementMapperTest {

    private ProjectElementMapper projectElementMapper;

    @BeforeEach
    public void setUp() {
        projectElementMapper = new ProjectElementMapperImpl();
    }
}
