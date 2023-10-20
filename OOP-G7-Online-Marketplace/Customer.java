import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private List<Order> orderHistory;
    private List<Product> shoppingCart;

    public Customer(String name) {
        super(name);
        this.orderHistory = new ArrayList<>();
        this.shoppingCart = new ArrayList<>();
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(List<Product> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addToCart(Product product) {
        shoppingCart.add(product);
        System.out.println(getName() + " added " + product.getName() + " to the shopping cart.");
    }

    public void removeFromCart(Product product) {
        shoppingCart.remove(product);
        System.out.println(getName() + " removed " + product.getName() + " from the shopping cart.");
    }

    public void placeOrder() {
        if (shoppingCart.isEmpty()) {
            System.out.println(getName() + ", your shopping cart is empty. Please add some products before placing an order.");
            return;
        }

        Order order = new Order(this, shoppingCart);
        orderHistory.add(order);
        shoppingCart.clear();
        System.out.println(getName() + ", your order has been placed successfully. Thank you for shopping with us!");
    }
}
