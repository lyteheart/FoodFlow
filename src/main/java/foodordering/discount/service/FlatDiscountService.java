package foodordering.discount.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class FlatDiscountService implements DiscountService {

    private static final BigDecimal FLAT_DISCOUNT = new BigDecimal("100");

    // Applies a fixed ₹100 discount to the order amount.
    @Override
    public BigDecimal calculateDiscount(BigDecimal orderAmount) {

        return orderAmount.min(FLAT_DISCOUNT);
    }
}