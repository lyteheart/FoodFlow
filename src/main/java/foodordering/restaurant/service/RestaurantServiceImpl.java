package foodordering.restaurant.service;


import foodordering.exception.InvalidInputException;
import foodordering.exception.NotFoundException;
import foodordering.menu.repository.MenuRepository;
import foodordering.restaurant.dto.RestaurantRequestDTO;
import foodordering.restaurant.dto.RestaurantResponseDTO;
import foodordering.restaurant.entity.Restaurant;
import foodordering.restaurant.repository.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    // Constructor injection to provide access to the RestaurantRepository.
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public RestaurantResponseDTO saveRestaurant(RestaurantRequestDTO requestDTO) {
        // Convert request DTO into Restaurant entity.
        Restaurant restaurant = new Restaurant();
        restaurant.setName(requestDTO.getName());
        restaurant.setAddress(requestDTO.getAddress());

        // Save the restaurant in the database.
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // Convert saved entity into response DTO.
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO();
        responseDTO.setId(savedRestaurant.getId());
        responseDTO.setName(savedRestaurant.getName());
        responseDTO.setAddress(savedRestaurant.getAddress());

        return responseDTO;
    }

    @Override
    public RestaurantResponseDTO getRestaurantById(Long id) {

        // Find the restaurant by ID or throw an exception if it doesn't exist.
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Restaurant not found with ID: " + id)
                );

        // Convert Restaurant entity into response DTO.
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO();
        responseDTO.setId(restaurant.getId());
        responseDTO.setName(restaurant.getName());
        responseDTO.setAddress(restaurant.getAddress());

        return responseDTO;
    }

    @Override
    public Page<RestaurantResponseDTO> getAllRestaurants(Pageable pageable) {

        // Retrieve all restaurants from the database.
        Page<Restaurant> restaurants = restaurantRepository.findAll(pageable);

        // Convert each Restaurant entity into a RestaurantResponseDTO.
        return restaurants
                .map(restaurant -> {
                    RestaurantResponseDTO responseDTO = new RestaurantResponseDTO();
                    responseDTO.setId(restaurant.getId());
                    responseDTO.setName(restaurant.getName());
                    responseDTO.setAddress(restaurant.getAddress());
                    return responseDTO;
                });
    }

    @Override
    public RestaurantResponseDTO updateRestaurant(Long id, RestaurantRequestDTO requestDTO) {

        // Find the existing restaurant or throw an exception.
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Restaurant not found with ID: " + id)
                );

        // Update restaurant details.
        restaurant.setName(requestDTO.getName());
        restaurant.setAddress(requestDTO.getAddress());

        // Save the updated restaurant.
        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);

        // Convert updated entity into response DTO.
        RestaurantResponseDTO responseDTO = new RestaurantResponseDTO();
        responseDTO.setId(updatedRestaurant.getId());
        responseDTO.setName(updatedRestaurant.getName());
        responseDTO.setAddress(updatedRestaurant.getAddress());

        return responseDTO;
    }

    @Override
    public void deleteRestaurant(Long id) {

        // Find the restaurant or throw an exception if it doesn't exist.
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Restaurant not found with ID: " + id)
                );
        if (menuRepository.existsByRestaurantId(id)) {
            throw new InvalidInputException(
                    "Cannot delete a restaurant that still has menu items."
            );
        }

        // Delete the restaurant from the database.
        restaurantRepository.delete(restaurant);
    }

}
