package foodordering.menu.repository;

import foodordering.menu.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuItem,Long> {
}
