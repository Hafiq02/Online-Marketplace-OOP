import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

public class OnlineMarketplace {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("||*|*|*|*|*|*|*|*|*|*|*|*|*||");
        System.out.println("||**      MAIN MENU      **||");
        System.out.println("||*|*|*|*|*|*|*|*|*|*|*|*|*||");
        System.out.println("|---------------------------|");
        System.out.println("                           ");
        System.out.print("    Seller's name: ");
        String tmpName = scanner.nextLine();

        Seller seller = new Seller(tmpName);
        Product product1 = new Product("iPhone", 999.99, "Latest smartphone from Apple");
        Product product2 = new Product("Laptop", 1499.99, "High-performance laptop");
        Product product3 = new Product("Headphones", 99.99, "Wireless headphones with noise cancellation");
        Product product4 = new Product("Smart Watch", 199.99, "Fitness tracker and smartwatch");
        Product product5 = new Product("Camera", 599.99, "Professional DSLR camera");

        seller.addProductForSale(product1);
        seller.addProductForSale(product2);
        seller.addProductForSale(product3);
        seller.addProductForSale(product4);
        seller.addProductForSale(product5);

        boolean continueManaging = true;
        while (continueManaging) {
            System.out.println("\nList of Products:");
            int i = 0;
            for (Product product : seller.getProductsForSale()) {
                System.out.println("[" + i + "]");
                System.out.println("Name: " + product.getName());
                System.out.println("Price: $" + product.getPrice());
                System.out.println("Description: " + product.getDescription());
                System.out.println("----------------------");
                i++;
            }

            System.out.println("\nSelect an option:\n1. Add product\n2. Remove product\n3. Continue to shopping");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.print("Enter the name of the product: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the price of the product: ");
                    double price = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter the description of the product: ");
                    String description = scanner.nextLine();

                    Product newProduct = new Product(name, price, description);
                    seller.addProductForSale(newProduct);
                    System.out.println("Product added for sale: " + newProduct.getName());
                    break;
                case "2":
                    boolean validIndex = false;
                    int index = 0;
                    while (!validIndex) {
                        System.out.print("Enter the index of the product to remove (or 'cancel' to go back): ");
                        String input = scanner.nextLine();
                        if (input.equalsIgnoreCase("cancel")) {
                            break;
                        }
                        try {
                            index = Integer.parseInt(input);
                            if (index >= 0 && index < seller.getProductsForSale().size()) {
                                validIndex = true;
                            } else {
                                System.out.println("Invalid index. Product does not exist.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid index.");
                        }
                    }
                    if (validIndex) {
                        Product removedProduct = seller.removeProductForSale(index);
                        System.out.println("Product removed: " + removedProduct.getName());
                        System.out.println("\nList of Products:");
                        i = 0;
                        for (Product product : seller.getProductsForSale()) {
                            System.out.println("[" + i + "]");
                            System.out.println("Name: " + product.getName());
                            System.out.println("Price: $" + product.getPrice());
                            System.out.println("Description: " + product.getDescription());
                            System.out.println("----------------------");
                            i++;
                        }
                    }
                    break;
                case "3":
                    System.out.println("\nList of Products:");
                    i = 0;
                    for (Product product : seller.getProductsForSale()) {
                        System.out.println("[" + i + "]");
                        System.out.println("Name: " + product.getName());
                        System.out.println("Price: $" + product.getPrice());
                        System.out.println("Description: " + product.getDescription());
                        System.out.println("----------------------");
                        i++;
                    }
                    continueManaging = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        System.out.print("\nBuyer's name: ");
        tmpName = scanner.nextLine();
        Customer customer = new Customer(tmpName);

        double totalAmount = 0.0;
        boolean continueShopping = true;
        while (continueShopping) {
            System.out.println("\nEnter the index of the product you want to buy (or 'end' to finish shopping):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("end")) {
                continueShopping = false;
            } else {
                try {
                    int selectedIndex = Integer.parseInt(input);
                    if (selectedIndex >= 0 && selectedIndex < seller.getProductsForSale().size()) {
                        Product selectedProduct = seller.getProductsForSale().get(selectedIndex);
                        customer.addToCart(selectedProduct);
                        System.out.println("Product added to the shopping cart: " + selectedProduct.getName());
                        totalAmount += selectedProduct.getPrice();
                    } else {
                        System.out.println("Invalid selection. No product added to the shopping cart.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. No product added to the shopping cart.");
                }
            }
        }

        System.out.println("\nShopping cart for " + customer.getName() + ":");
        for (Product product : customer.getShoppingCart()) {
            System.out.println(product.getName() + " : $" + product.getPrice());
        }
        System.out.println("\nTotal amount to be paid: $" + totalAmount);

        System.out.print("\nDo you want to proceed with the order? (Y/N): ");
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("Y")) {
            customer.placeOrder();

            System.out.println("\nOrder history for " + customer.getName() + ":");
            for (Order order : customer.getOrderHistory()) {
                System.out.println("Order Date: " + order.getOrderDate());
                for (Product product : order.getPurchasedProducts()) {
                    System.out.println(product.getName() + " - $" + product.getPrice());
                }
                System.out.println("-----------------------------");
            }
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            String formattedTotalAmount = decimalFormat.format(totalAmount);
            System.out.println("Total amount paid: $" + totalAmount);
        } else {
            System.out.println("\nOrder canceled. Exiting the program.");
        }
    }
}
