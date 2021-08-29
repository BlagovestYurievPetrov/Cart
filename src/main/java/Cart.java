import products.BaseProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<BaseProduct> products;
    private Double totalSum;
    public Cart() {
        this.products = new ArrayList<>();
    }

    public void add(BaseProduct product){
        boolean doesProductExist = this.products.stream().anyMatch(pr -> product.getName().compareTo(pr.getName()));
        this.products.add(product);
        this.totalSum = 0.0;
    }

    public void remove(BaseProduct product){
        this.products.remove(product);
    }

    public Double getSumBeforeDiscount(){
        this.products.forEach(product -> this.totalSum += product.getPrice());
        return this.totalSum;
    }

    public List<BaseProduct> getProducts() {
        return products;
    }
}
