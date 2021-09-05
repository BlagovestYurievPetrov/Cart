package products;

import cart.impl.CartImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaseProductTest {
    private Soap soap;
    private CartImpl cart;

    @Before
    public void setUp() {
        this.soap = new Soap("soap", 10.00, 1);
        this.cart = new CartImpl();
    }

    @Test
    public void increaseQuantityByAddingSameObjectAgain() {
        this.cart.add(soap);
        this.cart.add(soap);
        Assert.assertEquals(2, soap.getQuantity());
    }

    @Test
    public void testQuantityIncreaseMethod() {
        soap.increaseQuantity(2);
        Assert.assertEquals(3, soap.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuantityIncreaseMethodWithNegativeNumber() {
        soap.increaseQuantity(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enterInvalidName() {
        new Soap("d", 20.00, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enterInvalidQuantity() {
        new Soap("dove", 20.00, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void enterInvalidPrice() {
        new Soap("dove", -20.00, 1);
    }

}