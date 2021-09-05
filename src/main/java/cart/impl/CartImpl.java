package cart.impl;

import cart.Cart;
import products.BaseProduct;

import static util.ExceptionMessages.CANNOT_REMOVE_PRODUCT;
import static util.Variables.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class CartImpl implements Cart {
    private final List<BaseProduct> products;
    private BigDecimal totalSum;

    public CartImpl() {
        this.products = new ArrayList<>();
        this.totalSum = BigDecimal.ZERO;
    }


    public void add(BaseProduct product) {
        //If adding an existing product. The quantity will be increased by the initial amount of the product.
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
            throw new IllegalArgumentException(CANNOT_REMOVE_PRODUCT);
        }
        this.products.remove(product);
    }

    public BigDecimal getSum() {
        //Collect all classes in the cart
        List<String> cartClasses = getAllClassesInShoppingCart();

        this.products.forEach(product -> {
            int numberOfItemsWithTheSameClass = getNumberOfItemsWithTheSameClass(cartClasses, product);
            if (numberOfItemsWithTheSameClass > 1 || product.getQuantity() > 1) {
                BigDecimal result = product.getPrice().multiply(BigDecimal.valueOf(((1 - DISCOUNT_FOR_PRODUCTS_OF_SAME_TYPE_PERCENTAGE) * product.getQuantity())));
                this.totalSum = this.totalSum.add(result);
            } else {
                BigDecimal multiply = product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity()));
                this.totalSum = this.totalSum.add(multiply);
            }
        });

        int comparison = this.totalSum.compareTo(CART_THRESHOLD_FOR_DISCOUNT);
        return comparison > 0 ?
        this.totalSum.multiply(BigDecimal.valueOf(1 - DISCOUNT_FOR_EXCEEDING_THRESHOLD_PERCENTAGE)) : this.totalSum;
    }

    private int getNumberOfItemsWithTheSameClass(List<String> cartClasses, BaseProduct product) {
        // Return the amount of times a certain class is repeated in the shopping cart
        int counter = 0;
        for (String cartClass : cartClasses) {
            if (cartClass.equals(product.getClass().getSimpleName())) {
                counter++;
            }
        }
        return counter;
    }

    private List<String> getAllClassesInShoppingCart() {
        //This method returns a List of all class names in the shopping cart
        List<String> cartClasses = new ArrayList<>();
        this.products.forEach(pr -> cartClasses.add(pr.getClass().getSimpleName()));
        return cartClasses;
    }

    public List<BaseProduct> getProducts() {
        return new ArrayList<>(products);
    }
}
