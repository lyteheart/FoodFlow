package foodordering.menu.service;

import foodordering.menu.dto.MenuItemRequestDTO;
import foodordering.menu.dto.MenuItemResponseDTO;

import java.util.List;

public interface MenuService {

    // Creates a menu item for an existing restaurant.
    MenuItemResponseDTO saveMenuItem(MenuItemRequestDTO requestDTO);

    // Retrieves a menu item using its ID.
    MenuItemResponseDTO getMenuItemById(Long id);

    // Retrieves all menu items.
    List<MenuItemResponseDTO> getAllMenuItems();

    // Updates an existing menu item's details.
    MenuItemResponseDTO updateMenuItem(Long id, MenuItemRequestDTO requestDTO);

    // Deletes a menu item using its ID.
    void deleteMenuItem(Long id);

    List<MenuItemResponseDTO> getMenuItemsByRestaurant(Long restaurantId);
}