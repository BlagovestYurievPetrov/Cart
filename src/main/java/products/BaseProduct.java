package products;

import static util.ExceptionMessages.*;

public abstract class BaseProduct {
    private String name;
    private double price;
    private int quantity;
    private final int initialQuantity;


    protected BaseProduct(String name, double price, int quantity) {
        this.setName(name);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.initialQuantity = quantity;
    }


    public void increaseQuantity(int increase) {
        this.quantity = this.quantity + increase;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        if (name.length() < 2) {
            throw new IllegalArgumentException(INVALID_NAME_MESSAGE);
        }
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    private void setPrice(double price) {
        if (price < 0.0) {
            throw new IllegalArgumentException(PRICE_POSITIVE_MESSAGE);
        }
        this.price = price;
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
