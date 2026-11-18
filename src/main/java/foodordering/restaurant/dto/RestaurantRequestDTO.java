
package foodordering.restaurant.dto;

import jakarta.validation.constraints.NotBlank;

public class RestaurantRequestDTO {

    @NotBlank(message = "Restaurant name is required")
    private String name;

    @NotBlank(message = "Restaurant address is required")
    private String address;

    public RestaurantRequestDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
