package foodordering.discount.service;

import java.math.BigDecimal;

public interface DiscountService {

    // Calculates and returns the discount amount based on the order total.
    BigDecimal calculateDiscount(BigDecimal orderAmount);

}