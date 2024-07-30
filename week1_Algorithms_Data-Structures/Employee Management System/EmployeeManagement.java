import java.util.Scanner;

class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}

public class EmployeeManagement {
    private static Employee[] employees;
    private static int count;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of employees:");
        int size = Integer.parseInt(scanner.nextLine());
        employees = new Employee[size];
        count = 0;

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Traverse Employees");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addEmployee(scanner);
                    break;
                case 2:
                    searchEmployee(scanner);
                    break;
                case 3:
                    traverseEmployees();
                    break;
                case 4:
                    deleteEmployee(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void addEmployee(Scanner scanner) {
        if (count == employees.length) {
            System.out.println("Array is full. Cannot add more employees.");
            return;
        }
        System.out.println("Enter employee ID:");
        int employeeId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter position:");
        String position = scanner.nextLine();
        System.out.println("Enter salary:");
        double salary = Double.parseDouble(scanner.nextLine());

        employees[count] = new Employee(employeeId, name, position, salary);
        count++;
        System.out.println("Employee added successfully.");
    }

    public static void searchEmployee(Scanner scanner) {
        System.out.println("Enter employee ID to search:");
        int employeeId = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                System.out.println(employees[i]);
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    public static void traverseEmployees() {
        if (count == 0) {
            System.out.println("No employees to display.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println(employees[i]);
        }
    }

    public static void deleteEmployee(Scanner scanner) {
        System.out.println("Enter employee ID to delete:");
        int employeeId = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            if (employees[i].getEmployeeId() == employeeId) {
                // Shift elements to the left to fill the gap
                for (int j = i; j < count - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[count - 1] = null;
                count--;
                System.out.println("Employee deleted successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }
}
