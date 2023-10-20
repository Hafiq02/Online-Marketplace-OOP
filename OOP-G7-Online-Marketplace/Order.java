import java.util.Date;
import java.util.List;

public class Order {
    private Customer customer;
    private List<Product> purchasedProducts;
    private Date orderDate;

    public Order(Customer customer, List<Product> purchasedProducts) {
        this.customer = customer;
        this.purchasedProducts = purchasedProducts;
        this.orderDate = new Date();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
