import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InventoryManagementSystem {

    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose an option:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. Display Inventory");
            System.out.println("5. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Enter product ID:");
                    String addId = scanner.nextLine();
                    System.out.println("Enter product name:");
                    String addName = scanner.nextLine();
                    System.out.println("Enter product quantity:");
                    int addQuantity = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter product price:");
                    double addPrice = Double.parseDouble(scanner.nextLine());
                    Product newProduct = new Product(addId, addName, addQuantity, addPrice);
                    manager.addProduct(newProduct);
                    System.out.println("Product added successfully.");
                    break;
                case 2:
                    System.out.println("Enter product ID to update:");
                    String updateId = scanner.nextLine();
                    System.out.println("Enter new product name:");
                    String updateName = scanner.nextLine();
                    System.out.println("Enter new product quantity:");
                    int updateQuantity = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter new product price:");
                    double updatePrice = Double.parseDouble(scanner.nextLine());
                    Product updatedProduct = new Product(updateId, updateName, updateQuantity, updatePrice);
                    manager.updateProduct(updatedProduct);
                    break;
                case 3:
                    System.out.println("Enter product ID to delete:");
                    String deleteId = scanner.nextLine();
                    manager.deleteProduct(deleteId);
                    System.out.println("Product deleted successfully.");
                    break;
                case 4:
                    System.out.println("Current Inventory:");
                    manager.displayInventory();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}

class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}

class InventoryManager {
    HashMap<String, Product> inventory=new HashMap<>();

    

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    public void updateProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            inventory.put(product.getProductId(), product);
            System.out.println("Product updated successfully.");
        } else {
            System.out.println("Product not found in the inventory.");
        }
    }

    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found in the inventory.");
        }
    }

    public Product getProduct(String productId) {
        return inventory.get(productId);
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product product : inventory.values()) {
                System.out.println(product);
            }
        }
    }
}
