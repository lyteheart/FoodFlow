package foodordering.menu.repository;

import foodordering.menu.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<MenuItem, Long> {
    /*
        SELECT *
        FROM menu_item
        WHERE id = 10
        FOR UPDATE;
    */


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT m FROM MenuItem m WHERE m.id = :id")
    Optional<MenuItem> findByIdForUpdate(@Param("id") Long id);

    // Retrieve all menu items belonging to a particular restaurant
    List<MenuItem> findByRestaurantId(Long restaurantId);

    boolean existsByRestaurantId(Long restaurantId);


}
