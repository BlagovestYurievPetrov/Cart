package products;

import java.math.BigDecimal;

import static util.ExceptionMessages.*;

/**
 * Base abstract class to be extended by products of different
 * types who could be added to the shopping cart.
 */
public class BaseProduct {
    private String name;
    private BigDecimal price;
    private int quantity;
    private final int initialQuantity;


    protected BaseProduct(String name, double price, int quantity) {
        this.setName(name);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.initialQuantity = quantity;
    }

    /**
     * Increases the quantity of the product.
     *
     * @param increase Integer value representing the amount that will
     *                 be added to the quantity of the product.
     * @throws IllegalArgumentException When the provided parameter is
     *                                  zero or a negative integer.
     */
    public void increaseQuantity(int increase) {
        if (increase < 1) {
            throw new IllegalArgumentException(QUANTITY_POSITIVE_MESSAGE);
        }
        this.quantity = this.quantity + increase;
    }

    private void setName(String name) {
        if (name.length() < 2) {
            throw new IllegalArgumentException(INVALID_NAME_MESSAGE);
        }
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    private void setPrice(double price) {
        if (price <= 0.0) {
            throw new IllegalArgumentException(PRICE_POSITIVE_MESSAGE);
        }
        this.price = BigDecimal.valueOf(price);
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException(QUANTITY_POSITIVE_MESSAGE);
        }
        this.quantity = quantity;
    }

    public int getInitialQuantity() {
        return this.initialQuantity;
    }

    @Override
    public String toString() {
        return "BaseProduct{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", initialQuantity=" + initialQuantity +
                '}';
    }
}
