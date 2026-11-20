
package foodordering.config;

import foodordering.restaurant.entity.Restaurant;
import foodordering.restaurant.repository.RestaurantRepository;
import foodordering.menu.entity.MenuItem;
import foodordering.menu.repository.MenuRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public DataInitializer(
            RestaurantRepository restaurantRepository,
            MenuRepository menuRepository) {

        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    @PostConstruct
    public void initializeData() {

        // Prevent duplicate initialization.
        if (restaurantRepository.count() > 0) {
            return;
        }

        // BANGALORE

        createRestaurantWithMenu("Royal Spice Kitchen",
                "Indiranagar, Bangalore",
                new String[][]{
                        {"Paneer Butter Masala", "249", "25"},
                        {"Chicken Biryani", "299", "20"},
                        {"Butter Naan", "45", "50"},
                        {"Dal Makhani", "199", "30"},
                        {"Veg Pulao", "179", "25"}
                });

        createRestaurantWithMenu("Burger District",
                "Koramangala, Bangalore",
                new String[][]{
                        {"Classic Burger", "149", "30"},
                        {"Double Cheese Burger", "229", "20"},
                        {"Peri Peri Fries", "119", "35"},
                        {"Veggie Burger", "129", "25"},
                        {"Chocolate Shake", "139", "20"}
                });

        createRestaurantWithMenu("Dosa Junction",
                "Jayanagar, Bangalore",
                new String[][]{
                        {"Masala Dosa", "99", "40"},
                        {"Mysore Masala Dosa", "129", "35"},
                        {"Idli Sambar", "69", "45"},
                        {"Pongal", "89", "30"},
                        {"Filter Coffee", "40", "50"}
                });

        createRestaurantWithMenu("Napoli Pizza House",
                "Whitefield, Bangalore",
                new String[][]{
                        {"Margherita Pizza", "249", "20"},
                        {"Farmhouse Pizza", "349", "15"},
                        {"Pepperoni Pizza", "399", "12"},
                        {"Garlic Bread", "129", "30"},
                        {"Pasta Alfredo", "229", "20"}
                });

        // MUMBAI

        createRestaurantWithMenu("Bombay Tadka",
                "Andheri West, Mumbai",
                new String[][]{
                        {"Butter Chicken", "299", "20"},
                        {"Paneer Tikka", "229", "25"},
                        {"Chicken Tikka Roll", "179", "30"},
                        {"Jeera Rice", "129", "35"},
                        {"Butter Roti", "35", "50"}
                });

        createRestaurantWithMenu("Wok Republic",
                "Bandra West, Mumbai",
                new String[][]{
                        {"Veg Hakka Noodles", "169", "25"},
                        {"Chicken Fried Rice", "219", "20"},
                        {"Chilli Paneer", "199", "20"},
                        {"Veg Manchurian", "179", "25"},
                        {"Spring Rolls", "129", "30"}
                });

        createRestaurantWithMenu("The Hungry Bun",
                "Powai, Mumbai",
                new String[][]{
                        {"Classic Chicken Burger", "189", "25"},
                        {"Crispy Veg Burger", "139", "30"},
                        {"Loaded Nachos", "179", "20"},
                        {"Cheese Fries", "149", "25"},
                        {"Oreo Shake", "159", "20"}
                });

        // DELHI

        createRestaurantWithMenu("Delhi Darbar Kitchen",
                "Connaught Place, New Delhi",
                new String[][]{
                        {"Chicken Korma", "299", "20"},
                        {"Mutton Rogan Josh", "379", "15"},
                        {"Paneer Lababdar", "229", "25"},
                        {"Garlic Naan", "59", "40"},
                        {"Chicken Biryani", "279", "20"}
                });

        createRestaurantWithMenu("Chaat Street",
                "Karol Bagh, New Delhi",
                new String[][]{
                        {"Aloo Tikki Chaat", "89", "35"},
                        {"Pani Puri", "69", "40"},
                        {"Papdi Chaat", "99", "30"},
                        {"Dahi Bhalla", "109", "25"},
                        {"Chole Bhature", "139", "25"}
                });

        createRestaurantWithMenu("Sushi Sakura",
                "Saket, New Delhi",
                new String[][]{
                        {"Veg Sushi Roll", "249", "15"},
                        {"California Roll", "349", "12"},
                        {"Chicken Teriyaki Bowl", "299", "15"},
                        {"Miso Soup", "99", "30"},
                        {"Tempura Vegetables", "229", "15"}
                });

        // HYDERABAD

        createRestaurantWithMenu("Nizami Biryani House",
                "Banjara Hills, Hyderabad",
                new String[][]{
                        {"Hyderabadi Chicken Biryani", "279", "30"},
                        {"Mutton Biryani", "349", "20"},
                        {"Veg Dum Biryani", "199", "25"},
                        {"Chicken 65", "229", "20"},
                        {"Double Ka Meetha", "99", "25"}
                });

        createRestaurantWithMenu("Andhra Spice Hub",
                "Madhapur, Hyderabad",
                new String[][]{
                        {"Andhra Chicken Curry", "269", "20"},
                        {"Gongura Mutton", "329", "15"},
                        {"Pesarattu", "99", "30"},
                        {"Curd Rice", "89", "35"},
                        {"Andhra Meals", "179", "25"}
                });

        createRestaurantWithMenu("Creamy Scoop Desserts",
                "Gachibowli, Hyderabad",
                new String[][]{
                        {"Chocolate Brownie", "129", "25"},
                        {"Red Velvet Cake", "179", "15"},
                        {"Belgian Waffle", "199", "20"},
                        {"Vanilla Ice Cream", "89", "30"},
                        {"Chocolate Sundae", "149", "20"}
                });

        // CHENNAI

        createRestaurantWithMenu("Madras Meals",
                "T. Nagar, Chennai",
                new String[][]{
                        {"South Indian Meals", "149", "30"},
                        {"Lemon Rice", "99", "35"},
                        {"Curd Rice", "89", "35"},
                        {"Sambar Rice", "109", "30"},
                        {"Rasam Rice", "99", "30"}
                });

        createRestaurantWithMenu("Coastal Curry House",
                "Adyar, Chennai",
                new String[][]{
                        {"Fish Curry", "299", "15"},
                        {"Prawn Masala", "329", "15"},
                        {"Fish Fry", "249", "20"},
                        {"Egg Curry", "149", "25"},
                        {"Steamed Rice", "79", "40"}
                });

        // PUNE

        createRestaurantWithMenu("Pune Misal Corner",
                "Kothrud, Pune",
                new String[][]{
                        {"Kolhapuri Misal Pav", "109", "35"},
                        {"Vada Pav", "35", "50"},
                        {"Sabudana Khichdi", "99", "30"},
                        {"Poha", "59", "40"},
                        {"Pav Bhaji", "129", "30"}
                });

        createRestaurantWithMenu("Little Italy Kitchen",
                "Viman Nagar, Pune",
                new String[][]{
                        {"Penne Arrabbiata", "229", "20"},
                        {"Spaghetti Aglio Olio", "249", "15"},
                        {"Mushroom Risotto", "299", "15"},
                        {"Margherita Pizza", "259", "20"},
                        {"Tiramisu", "179", "15"}
                });

        // KOLKATA

        createRestaurantWithMenu("Kolkata Kathi Rolls",
                "Park Street, Kolkata",
                new String[][]{
                        {"Egg Kathi Roll", "99", "35"},
                        {"Chicken Kathi Roll", "139", "30"},
                        {"Paneer Kathi Roll", "119", "30"},
                        {"Double Egg Roll", "129", "25"},
                        {"Chicken Chowmein", "169", "25"}
                });

        createRestaurantWithMenu("Bengal Sweet House",
                "Salt Lake, Kolkata",
                new String[][]{
                        {"Rasgulla", "30", "50"},
                        {"Mishti Doi", "69", "35"},
                        {"Sandesh", "49", "40"},
                        {"Gulab Jamun", "59", "35"},
                        {"Cham Cham", "55", "30"}
                });

        createRestaurantWithMenu("Royal Mughlai Kitchen",
                "New Town, Kolkata",
                new String[][]{
                        {"Chicken Chaap", "279", "20"},
                        {"Kolkata Chicken Biryani", "269", "25"},
                        {"Mutton Kosha", "329", "15"},
                        {"Egg Devil", "99", "30"},
                        {"Roomali Roti", "35", "40"}
                });
    }

    private void createRestaurantWithMenu(
            String name,
            String address,
            String[][] menuItems) {

        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setAddress(address);

        Restaurant savedRestaurant =
                restaurantRepository.save(restaurant);

        for (String[] item : menuItems) {

            MenuItem menuItem = new MenuItem();

            menuItem.setName(item[0]);
            menuItem.setPrice(new BigDecimal(item[1]));
            menuItem.setStockQuantity(Integer.parseInt(item[2]));
            menuItem.setRestaurant(savedRestaurant);

            menuRepository.save(menuItem);
        }
    }
}
