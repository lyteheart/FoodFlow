
package foodordering.menu.service;

import foodordering.menu.dto.MenuItemRequestDTO;
import foodordering.menu.dto.MenuItemResponseDTO;
import foodordering.menu.entity.MenuItem;
import foodordering.menu.repository.MenuRepository;
import foodordering.restaurant.entity.Restaurant;
import foodordering.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuServiceImpl(
            MenuRepository menuRepository,
            RestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // Creates a menu item for an existing restaurant.
    @Override
    public MenuItemResponseDTO saveMenuItem(MenuItemRequestDTO requestDTO) {

        Restaurant restaurant = restaurantRepository.findById(requestDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException(
                        "Restaurant not found with ID: " + requestDTO.getRestaurantId()));

        MenuItem menuItem = new MenuItem();
        menuItem.setName(requestDTO.getName());
        menuItem.setPrice(requestDTO.getPrice());
        menuItem.setStockQuantity(requestDTO.getStockQuantity());
        menuItem.setRestaurant(restaurant);

        MenuItem savedMenuItem = menuRepository.save(menuItem);

        return mapToResponseDTO(savedMenuItem);
    }

    // Retrieves a menu item using its ID.
    @Override
    public MenuItemResponseDTO getMenuItemById(Long id) {

        MenuItem menuItem = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Menu item not found with ID: " + id));

        return mapToResponseDTO(menuItem);
    }

    // Retrieves all menu items.
    @Override
    public List<MenuItemResponseDTO> getAllMenuItems() {

        return menuRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    // Updates an existing menu item's details.
    @Override
    public MenuItemResponseDTO updateMenuItem(Long id, MenuItemRequestDTO requestDTO) {

        MenuItem menuItem = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Menu item not found with ID: " + id));

        Restaurant restaurant = restaurantRepository.findById(requestDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException(
                        "Restaurant not found with ID: " + requestDTO.getRestaurantId()));

        menuItem.setName(requestDTO.getName());
        menuItem.setPrice(requestDTO.getPrice());
        menuItem.setStockQuantity(requestDTO.getStockQuantity());
        menuItem.setRestaurant(restaurant);

        MenuItem updatedMenuItem = menuRepository.save(menuItem);

        return mapToResponseDTO(updatedMenuItem);
    }

    // Deletes a menu item using its ID.
    @Override
    public void deleteMenuItem(Long id) {

        MenuItem menuItem = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Menu item not found with ID: " + id));

        menuRepository.delete(menuItem);
    }

    // Converts MenuItem entity into MenuItemResponseDTO.
    private MenuItemResponseDTO mapToResponseDTO(MenuItem menuItem) {

        MenuItemResponseDTO responseDTO = new MenuItemResponseDTO();

        responseDTO.setId(menuItem.getId());
        responseDTO.setName(menuItem.getName());
        responseDTO.setPrice(menuItem.getPrice());
        responseDTO.setStockQuantity(menuItem.getStockQuantity());
        responseDTO.setRestaurantId(menuItem.getRestaurant().getId());
        responseDTO.setRestaurantName(menuItem.getRestaurant().getName());

        return responseDTO;
    }
}
