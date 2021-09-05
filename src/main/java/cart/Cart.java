package cart;

import products.BaseProduct;

import java.math.BigDecimal;

public interface Cart {
    void add(BaseProduct product);

    void remove(BaseProduct product);

    BigDecimal getSum();
}
