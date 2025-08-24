import java.util.ArrayList;
import java.util.List;

class Product {
    private int productId;
    private String productName;
    private int quantity;
    private double price;

    // Constructors, getters, setters

    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}

class InventoryManager {
    private List<Product> inventory;

    public InventoryManager() {
        // Initialize inventory list
        inventory = new ArrayList<>();
    }

    public void addProduct(Product product) {
        // Add product to inventory
        inventory.add(product);
    }

    public void updateProductQuantity(int productId, int newQuantity) {
        // Update quantity of an existing product
//        var product = inventory.stream().filter(p -> p.getProductId() == productId).collect(Collectors.toList()).get(0);
//        product.setQuantity(newQuantity);

        var product = inventory.stream()
                .filter(p -> p.getProductId() == productId)
                .findFirst();
        product.ifPresent(p -> p.setQuantity(newQuantity));
    }

    public void removeProduct(int productId) {
        // Remove product from inventory
        inventory.removeIf(p -> p.getProductId() == productId);
    }

    public void displayAllProducts() {
        // Display list of all products in inventory
        for (var p : inventory) {
            System.out.println(p.toString());
        }
    }
}

public class InventoryApplication {
    public static void main(String[] args) {
        System.out.println("InventoryApplication run");
        // Demonstrate inventory management system
        // Create instances of Product and InventoryManager
        // Perform operations such as addProduct, updateProductQuantity, removeProduct, displayAllProducts
        InventoryManager m = new InventoryManager();
        Product p1 = new Product(1, "Radio", 3, 100);
        Product p2 = new Product(2, "TV", 5, 400);
        Product p3 = new Product(3, "Smartphone", 10, 300);
        Product p4 = new Product(4, "Macbook", 2, 200);
        m.addProduct(p1);
        m.addProduct(p2);
        m.addProduct(p3);
        m.addProduct(p4);
        System.out.println("-----Initial list ---");
        m.displayAllProducts();
        m.updateProductQuantity(3, 50); // smartphone 50
        System.out.println("----- After quantity change ----");
        m.displayAllProducts();
        m.removeProduct(2); // TV
        System.out.println("------ After removal -------");
        m.displayAllProducts();
    }
}
