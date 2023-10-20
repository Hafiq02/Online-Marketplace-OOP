import java.util.ArrayList;
import java.util.List;

public class Seller extends Person{
    private String name;
    private List<Product> productsForSale;

    public Seller(String name) {
        this.name = name;
        this.productsForSale = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProductsForSale() {
        return productsForSale;
    }

    public void addProductForSale(Product product) {
        productsForSale.add(product);
    }

    public Product removeProductForSale(int index) {
        return productsForSale.remove(index);
    }
}
