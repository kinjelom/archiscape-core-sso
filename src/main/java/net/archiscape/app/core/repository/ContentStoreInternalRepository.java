package net.archiscape.app.core.repository;

import java.util.UUID;
import net.archiscape.app.core.domain.ContentStoreInternal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the ContentStoreInternal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContentStoreInternalRepository extends JpaRepository<ContentStoreInternal, UUID> {}
