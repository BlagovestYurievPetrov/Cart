import products.BaseProduct;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cart {
    private final List<BaseProduct> products;
    private Double totalSum;

    public Cart() {
        this.products = new ArrayList<>();
        this.totalSum = 0.0;
    }


    public void add(BaseProduct product) {
        boolean doesProductExist = this.products.contains(product);
        if (doesProductExist) {
            int index = this.products.indexOf(product);
            BaseProduct selectedProduct = this.products.get(index);
            selectedProduct.setQuantity(selectedProduct.getQuantity() + selectedProduct.getInitialQuantity());
            return;
        }
        this.products.add(product);
    }


    public void remove(BaseProduct product) {
        boolean doesProductExist = this.products.contains(product);
        if (!doesProductExist) {
            throw new IllegalArgumentException(String.format("You cannot remove %s, since it is not contained in the cart.", product.getName()));
        }
        this.products.remove(product);
    }

    public Double getSum() {
        //Collect all classes in the cart
        List<String> cartClasses = getAllClassesInShoppingCart();

        this.products.forEach(product -> {
            int numberOfItemsWithTheSameClass = getNumberOfItemsWithTheSameClass(cartClasses, product);
            if (numberOfItemsWithTheSameClass > 1 || product.getQuantity() > 1) {
                this.totalSum += product.getPrice() * 0.9 * product.getQuantity();
            } else {
                this.totalSum += product.getPrice() * product.getQuantity();
            }
        });
        return this.totalSum > 100.00 ? this.totalSum * 0.9 : this.totalSum;
    }

    private int getNumberOfItemsWithTheSameClass(List<String> cartClasses, BaseProduct product) {
        // Return the amount of times a certain class is repeated in the shopping cart
        int counter = 0;
        for (String cartClass : cartClasses) {
            if(cartClass.equals(product.getClass().getSimpleName())){
                counter++;
            }
        }
        return counter;
    }

    private List<String> getAllClassesInShoppingCart() {
        //This method returns a List of all class names in the shopping cart
        List<String> cartClasses = new ArrayList<>();
        this.products.forEach(pr-> {
            cartClasses.add(pr.getClass().getSimpleName());
        });
        return cartClasses;
    }

    public List<BaseProduct> getProducts() {
        return products;
    }
}
