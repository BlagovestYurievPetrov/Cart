package cart;

import products.BaseProduct;

public interface Cart {
    void add(BaseProduct product);

    void remove(BaseProduct product);

    Double getSum();
}
