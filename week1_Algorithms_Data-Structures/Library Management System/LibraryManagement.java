import java.util.Arrays;
import java.util.Scanner;

class Book {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

public class LibraryManagement {

    private Book[] books;

    public LibraryManagement(Book[] books) {
        this.books = books;
    }

    public static void main(String[] args) {
        
        Book[] books = {
            new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book(2, "1984", "George Orwell"),
            new Book(3, "To Kill a Mockingbird", "Harper Lee"),
            new Book(4, "Pride and Prejudice", "Jane Austen"),
            new Book(5, "The Catcher in the Rye", "J.D. Salinger")
        };

        
        Arrays.sort(books, (a, b) -> a.title.compareTo(b.title));

        LibraryManagement library = new LibraryManagement(books);
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            System.out.println("Choose an operation:");
            System.out.println("1. Search Book by Title (Linear Search)");
            System.out.println("2. Search Book by Title (Binary Search)");
            System.out.println("3. Exit");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    library.searchBookLinear(scanner);
                    break;
                case 2:
                    library.searchBookBinary(scanner);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    public void searchBookLinear(Scanner scanner) {
        System.out.println("Enter book title to search:");
        String title = scanner.nextLine();
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                System.out.println(book);
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void searchBookBinary(Scanner scanner) {
        System.out.println("Enter book title to search:");
        String title = scanner.nextLine();
        int index = binarySearch(title);
        if (index != -1) {
            System.out.println(books[index]);
        } else {
            System.out.println("Book not found.");
        }
    }

    private int binarySearch(String title) {
        int left = 0;
        int right = books.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = title.compareToIgnoreCase(books[mid].title);
            if (result == 0) {
                return mid;
            }
            if (result < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
