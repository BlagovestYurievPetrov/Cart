package cart;

import cart.impl.CartImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import products.*;

import java.math.BigDecimal;


public class CartTest {
    private CartImpl cart;
    private Soap teo;
    private Soap dove;
    private Soap max;
    private Soap palmolive;
    private Shampoo hs;
    private Towel rex;
    private Conditioner washAndGo;

    @Before
    public void setUp() {
        this.cart = new CartImpl();
        this.teo = new Soap("teo", 10.00, 1);
        this.dove = new Soap("dove", 20.00, 2);
        this.max = new Soap("max", 10.00, 1);
        this.palmolive = new Soap("palmolive", 20.00, 2);
        this.hs = new Shampoo("headAndShoulders", 120.00, 1);
        this.rex = new Towel("rex", 20.00, 1);
        this.washAndGo = new Conditioner("washAndGo", 20.00, 2);
    }

    @Test
    public void addingOneItemToTheCart() {
        this.cart.add(teo);
        Assert.assertEquals(1, this.cart.getProducts().size());
    }

    @Test
    public void addingExistingProductToCartIncreasesQuantity() {
        this.cart.add(teo);
        this.cart.add(teo);
        Assert.assertEquals(2, teo.getQuantity());
    }

    @Test
    public void addingExistingProductToCartDoesNotAffectSize() {
        this.cart.add(teo);
        this.cart.add(teo);
        Assert.assertEquals(1, this.cart.getProducts().size());
    }

    @Test
    public void removingExistingProduct() {
        this.cart.add(dove);
        this.cart.remove(dove);
        Assert.assertEquals(0, this.cart.getProducts().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void attemptToRemoveProductNotInTheCart() {
        this.cart.remove(teo);
    }

    @Test
    public void discountForSameProductWithBiggerQuantity() {
        this.cart.add(dove);
        Assert.assertEquals(0, BigDecimal.valueOf(36.00).compareTo(this.cart.getSum()));
    }

    @Test
    public void discountForContainingTwoProductsOfTheSameType() {
        this.cart.add(teo);
        this.cart.add(max);
        Assert.assertEquals(0, BigDecimal.valueOf(18.00).compareTo(this.cart.getSum()));
    }

    @Test
    public void discountForTwoProductsOfSameTypeAndBigQuantities() {
        this.cart.add(dove);
        this.cart.add(palmolive);
        Assert.assertEquals(0, BigDecimal.valueOf(72.00).compareTo(this.cart.getSum()));
    }

    @Test
    public void sumForTwoProductsOfDifferentTypesWithoutDiscount() {
        this.cart.add(teo);
        this.cart.add(rex);
        Assert.assertEquals(0, BigDecimal.valueOf(30.00).compareTo(this.cart.getSum()));
    }

    @Test
    public void discountAppliedOnlyToProductWithGreaterQuantity() {
        this.cart.add(dove);
        this.cart.add(rex);
        Assert.assertEquals(0, BigDecimal.valueOf(56.00).compareTo(this.cart.getSum()));
    }

    @Test
    public void discountAppliedByExceedingDiscountThresholdForTotalSum() {
        this.cart.add(hs);
        Assert.assertEquals(0, BigDecimal.valueOf(108.00).compareTo(this.cart.getSum()));
    }

    @Test
    public void discountAppliedForProductsOfSameTypeAndDiscountThresholdForTotalSum() {
        this.cart.add(hs);
        this.cart.add(dove);
        this.cart.add(max);
        this.cart.add(washAndGo);
        Assert.assertEquals(0, BigDecimal.valueOf(180.90).compareTo(this.cart.getSum()));
    }
}