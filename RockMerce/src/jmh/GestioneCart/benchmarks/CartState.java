package GestioneCart.benchmarks;

import LogicTier.Entità.Cart;
import LogicTier.Entità.Customer;
import LogicTier.Entità.Guitar;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Scope;

// versione Thread-scoped usata dal benchmark
@State(Scope.Thread)
public class CartState {
    public CartServiceAdapter cartService;
    public Customer customer;
    public Guitar guitarToAdd;
    public static final int CART_SIZE = 1000;

    @Setup(Level.Trial)
    public void setup() {
        this.cartService = new CartServiceAdapter();

        Cart customerCart = new Cart();
        customerCart.setId(42);
        customer = new Customer();
        customer.setCart(customerCart);

        guitarToAdd = new Guitar();
        guitarToAdd.setId(0);
        guitarToAdd.setPrice(150.0);
        guitarToAdd.setDisponibility(1);
    }
}