import products.Shampoo;
import products.Soap;
import products.Towel;

public class Main {
    public static void main(String[] args) {
        Shampoo sh = new Shampoo("hs", 10.00, 1);
        Soap dove = new Soap("dove", 20.00, 1);
        Soap pal = new Soap("pal", 20.00, 1);
        Cart cart = new Cart();
        cart.add(pal);
        cart.add(pal);
        cart.add(pal);
        cart.add(pal);
        cart.add(dove);
        cart.add(sh);
        cart.add(sh);
        System.out.printf("The total value of your cart is: %.2f",cart.getSum());

    }
}
