import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

public class TaskManagement {
    private Task head;

    public static void main(String[] args) {
        TaskManagement taskList = new TaskManagement();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. Add Task");
            System.out.println("2. Search Task");
            System.out.println("3. Traverse Tasks");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    taskList.addTask(scanner);
                    break;
                case 2:
                    taskList.searchTask(scanner);
                    break;
                case 3:
                    taskList.traverseTasks();
                    break;
                case 4:
                    taskList.deleteTask(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    public void addTask(Scanner scanner) {
        System.out.println("Enter task ID:");
        int taskId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter task name:");
        String taskName = scanner.nextLine();
        System.out.println("Enter task status:");
        String status = scanner.nextLine();

        Task newTask = new Task(taskId, taskName, status);
        if (head == null) {
            head = newTask;
        } else {
            Task current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTask;
        }
        System.out.println("Task added successfully.");
    }

    public void searchTask(Scanner scanner) {
        System.out.println("Enter task ID to search:");
        int taskId = Integer.parseInt(scanner.nextLine());
        Task current = head;
        while (current != null) {
            if (current.taskId == taskId) {
                System.out.println(current);
                return;
            }
            current = current.next;
        }
        System.out.println("Task not found.");
    }

    public void traverseTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        Task current = head;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }

    public void deleteTask(Scanner scanner) {
        System.out.println("Enter task ID to delete:");
        int taskId = Integer.parseInt(scanner.nextLine());
        if (head == null) {
            System.out.println("Task not found.");
            return;
        }
        if (head.taskId == taskId) {
            head = head.next;
            System.out.println("Task deleted successfully.");
            return;
        }
        Task current = head;
        while (current.next != null) {
            if (current.next.taskId == taskId) {
                current.next = current.next.next;
                System.out.println("Task deleted successfully.");
                return;
            }
            current = current.next;
        }
        System.out.println("Task not found.");
    }
}
