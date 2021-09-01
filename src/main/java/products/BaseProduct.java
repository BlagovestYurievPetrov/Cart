package products;

public abstract class BaseProduct {
    private String name;
    private Double price;
    private Integer quantity;
    private final Integer initialQuantity;


    protected BaseProduct(String name, Double price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.initialQuantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getInitialQuantity() {
        return initialQuantity;
    }
}
