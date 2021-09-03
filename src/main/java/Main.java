import cart.impl.CartImpl;


public class Main {
    public static void main(String[] args) {
        CartImpl cart = new CartImpl();
        System.out.printf("The total value of your cart is: %.2f",cart.getSum());

    }
}
