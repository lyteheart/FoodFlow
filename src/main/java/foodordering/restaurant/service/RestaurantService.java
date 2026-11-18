package foodordering.restaurant.service;


import foodordering.restaurant.dto.RestaurantRequestDTO;
import foodordering.restaurant.dto.RestaurantResponseDTO;

import java.util.List;

public interface RestaurantService {

    // Creates a new restaurant and returns its saved details.
    RestaurantResponseDTO saveRestaurant(RestaurantRequestDTO requestDTO);

    // Retrieves a single restaurant using its ID.
    RestaurantResponseDTO getRestaurantById(Long id);

    // Retrieves all restaurants.
    List<RestaurantResponseDTO> getAllRestaurants();

    // Updates an existing restaurant's details using its ID.
    RestaurantResponseDTO updateRestaurant(Long id, RestaurantRequestDTO requestDTO);

    // Deletes a restaurant using its ID.
    void deleteRestaurant(Long id);

}
