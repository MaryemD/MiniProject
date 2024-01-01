import java.util.HashMap;
import java.time.LocalDate;
import java.util.Scanner;


public class Admin {
    private int adminID;
    private String adminUserName;
    private static HashMap<Integer, Book> booksMap = new HashMap<>();
    private int bookIdCounter = 1;
    
    public Admin(int adminID, String adminUserName) {
        this.adminID = adminID;
        this.adminUserName = adminUserName;
    }

    // Getters and Setters for adminID and adminUserName
    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    // Getter and Setter for booksMap
    public static HashMap<Integer, Book> getBooksMap() {
        return booksMap;
    }

//    public void setBooksMap(HashMap<Integer, Book> booksMap) {
//        this.booksMap = booksMap;
//    }
    
    public void addBook(String title, String author, String genre, int availabilityStatus) {
        Book newBook = new Book(bookIdCounter, title, author, genre, availabilityStatus);
        booksMap.put(bookIdCounter, newBook);
        bookIdCounter++; // Increment counter for the next book ID
        System.out.println("Book added successfully.");
    }
    
    public void deleteBook(int bookId) {
        if (booksMap.containsKey(bookId)) {
            booksMap.remove(bookId);
            System.out.println("Book with ID " + bookId + " deleted successfully.");
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }
    }
    
    public void updateBook(int bookId, String title, String author, String genre, int availabilityStatus) {
        if (booksMap.containsKey(bookId)) {
            Book bookToUpdate = booksMap.get(bookId);
            bookToUpdate.setTitle(title);
            bookToUpdate.setAuthor(author);
            bookToUpdate.setGenre(genre);
            bookToUpdate.setAvailabilityStatus(availabilityStatus);
            System.out.println("Book with ID " + bookId + " updated successfully.");
        } else {
            System.out.println("Book with ID " + bookId + " not found.");
        }
    }
    
    public Event createEvent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter event name:");
        String eventName = scanner.nextLine();

        System.out.println("Enter event description:");
        String description = scanner.nextLine();

        System.out.println("Enter event date (YYYY-MM-DD):");
        LocalDate eventDate = LocalDate.parse(scanner.nextLine());

        // Create and return the event object
        return new Event(eventName, eventDate, description);
    }
}
