import cart.impl.CartImpl;
import products.Soap;


public class Main {
    public static void main(String[] args) {
        CartImpl cart = new CartImpl();
        Soap soap = new Soap("dove", 20.00, 2);
        System.out.printf("The total value of your cart is: %.2f",cart.getSum());
        System.out.println(soap);
    }
}
