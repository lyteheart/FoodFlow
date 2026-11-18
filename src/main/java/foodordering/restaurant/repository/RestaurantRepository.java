package foodordering.restaurant.repository;

import foodordering.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}
