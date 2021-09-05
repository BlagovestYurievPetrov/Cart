package cart.impl;

import cart.Cart;
import products.BaseProduct;

import static util.ExceptionMessages.CANNOT_REMOVE_PRODUCT;
import static util.Variables.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores cart data and performs add, remove
 * and calculation of cart sum functionalities.
 */
public final class CartImpl implements Cart {
    private final List<BaseProduct> products;
    private BigDecimal totalSum;

    public CartImpl() {
        this.products = new ArrayList<>();
        this.totalSum = BigDecimal.ZERO;
    }

    /**
     * Adds products to the cart. In case a product is already contained
     * in the cart it increases it's quantity by the initial quantity of
     * the instance.
     *
     * @param product One of the classes extending BaseProduct.
     */
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

    /**
     * Removes a product from the cart.
     *
     * @param product One of the classes extending BaseProduct.
     * @throws IllegalArgumentException - If the product received
     *                                  as a parameter is not in the cart.
     */
    public void remove(BaseProduct product) {
        boolean doesProductExist = this.products.contains(product);
        if (!doesProductExist) {
            throw new IllegalArgumentException(CANNOT_REMOVE_PRODUCT);
        }
        this.products.remove(product);
    }

    /**
     * Calculates the total sum of the cart. Applies discount in case there
     * are products of the same type or multiple copies of the same product.
     * Also applies additional discount if a certain threshold is exceeded.
     *
     * @return BigDecimal value representing the total sum of the cart
     * after all discounts have been taken in consideration.
     */
    public BigDecimal getSum() {
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

    /**
     * Calculates the amount of times a certain class (product type) is
     * repeated in the shopping cart. This can be used to calculate whether
     * the price discount applies to the said product or not.
     *
     * @param cartClasses a List of all class names in the shopping cart.
     * @param product     One of the classes extending BaseProduct.
     * @return Integer value of the amount of times a product type is repeated
     * in the shopping cart.
     */
    private int getNumberOfItemsWithTheSameClass(List<String> cartClasses, BaseProduct product) {
        int counter = 0;
        for (String cartClass : cartClasses) {
            if (cartClass.equals(product.getClass().getSimpleName())) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Fills a List with all class names (types of products) and returns it.
     *
     * @return a List of all class names in the shopping cart.
     */
    private List<String> getAllClassesInShoppingCart() {
        List<String> cartClasses = new ArrayList<>();
        this.products.forEach(pr -> cartClasses.add(pr.getClass().getSimpleName()));
        return cartClasses;
    }


    public List<BaseProduct> getProducts() {
        return new ArrayList<>(products);
    }
}
