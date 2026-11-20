package foodordering.menu.controller;

import foodordering.menu.dto.MenuItemRequestDTO;
import foodordering.menu.dto.MenuItemResponseDTO;
import foodordering.menu.service.MenuService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private final MenuService menuService;

    // Constructor injection of MenuService
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // Create a new menu item
    @PostMapping
    public ResponseEntity<MenuItemResponseDTO> createMenuItem(
            @Valid @RequestBody MenuItemRequestDTO requestDTO) {

        MenuItemResponseDTO response = menuService.saveMenuItem(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Retrieve all menu items
    @GetMapping
    public ResponseEntity<List<MenuItemResponseDTO>> getAllMenuItems() {

        List<MenuItemResponseDTO> menuItems = menuService.getAllMenuItems();

        return ResponseEntity.ok(menuItems);
    }

    // Retrieve a menu item by its ID
    @GetMapping("/{id}")
    public ResponseEntity<MenuItemResponseDTO> getMenuItemById(
            @PathVariable Long id) {

        MenuItemResponseDTO response = menuService.getMenuItemById(id);

        return ResponseEntity.ok(response);
    }

    // Update an existing menu item
    @PutMapping("/{id}")
    public ResponseEntity<MenuItemResponseDTO> updateMenuItem(
            @PathVariable Long id,
            @Valid @RequestBody MenuItemRequestDTO requestDTO) {

        MenuItemResponseDTO response =
                menuService.updateMenuItem(id, requestDTO);

        return ResponseEntity.ok(response);
    }

    // Delete a menu item by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {

        menuService.deleteMenuItem(id);

        return ResponseEntity.noContent().build();
    }

    // Retrieve all menu items belonging to a particular restaurant
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<MenuItemResponseDTO>> getMenuItemsByRestaurant(
            @PathVariable Long restaurantId) {

        List<MenuItemResponseDTO> menuItems =
                menuService.getMenuItemsByRestaurant(restaurantId);

        return ResponseEntity.ok(menuItems);
    }
}