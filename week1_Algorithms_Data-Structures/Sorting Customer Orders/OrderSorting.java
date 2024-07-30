import java.util.Scanner;

class Order {
    private String orderId;
    private String customerName;
    private double totalPrice;

    public Order(String orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

public class OrderSorting {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of orders:");
        int n = Integer.parseInt(scanner.nextLine());

        Order[] orders = new Order[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter order ID:");
            String orderId = scanner.nextLine();
            System.out.println("Enter customer name:");
            String customerName = scanner.nextLine();
            System.out.println("Enter total price:");
            double totalPrice = Double.parseDouble(scanner.nextLine());
            orders[i] = new Order(orderId, customerName, totalPrice);
        }

        System.out.println("Choose sorting method:");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Quick Sort");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                bubbleSort(orders);
                System.out.println("Sorted using Bubble Sort:");
                break;
            case 2:
                quickSort(orders, 0, orders.length - 1);
                System.out.println("Sorted using Quick Sort:");
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        for (Order order : orders) {
            System.out.println(order);
        }
    }

    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    // Swap orders[j] and orders[j + 1]
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    public static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = (low - 1); 
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;

        return i + 1;
    }
}
