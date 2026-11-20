package foodordering.restaurant.controller;

import foodordering.restaurant.dto.RestaurantRequestDTO;
import foodordering.restaurant.dto.RestaurantResponseDTO;
import foodordering.restaurant.service.RestaurantService;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    // Constructor injection of RestaurantService
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    // Create a new restaurant
    @PostMapping
    public ResponseEntity<RestaurantResponseDTO> createRestaurant(
            @Valid @RequestBody RestaurantRequestDTO requestDTO) {

        RestaurantResponseDTO response =
                restaurantService.saveRestaurant(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Retrieve all restaurants
    @GetMapping
    public ResponseEntity<Page<RestaurantResponseDTO>>
    getAllRestaurants(@PageableDefault(size = 10,sort = "name",direction = Sort.Direction.ASC) Pageable pageable) {

        Page<RestaurantResponseDTO> restaurants =
                restaurantService.getAllRestaurants(pageable);

        return ResponseEntity.ok(restaurants);
    }

    // Retrieve a restaurant by its ID
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> getRestaurantById(
            @PathVariable Long id) {

        RestaurantResponseDTO response =
                restaurantService.getRestaurantById(id);

        return ResponseEntity.ok(response);
    }

    // Update an existing restaurant
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponseDTO> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantRequestDTO requestDTO) {

        RestaurantResponseDTO response =
                restaurantService.updateRestaurant(id, requestDTO);

        return ResponseEntity.ok(response);
    }

    // Delete a restaurant by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {

        restaurantService.deleteRestaurant(id);

        return ResponseEntity.noContent().build();
    }
}