package foodordering.discount.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class PercentageDiscountService implements DiscountService {

    // 🔴 Why use static final?
    // Suppose Spring creates multiple instances of this service.
    // We don't need a separate discount percentage for every object. All instances use the same 10% value.
    private static final BigDecimal DISCOUNT_PERCENTAGE = new BigDecimal("10");

    // Calculates a 10% discount on the order amount.
    @Override
    public BigDecimal calculateDiscount(BigDecimal orderAmount) {

        return orderAmount.multiply(DISCOUNT_PERCENTAGE)
                .divide(new BigDecimal("100"));
    }
}