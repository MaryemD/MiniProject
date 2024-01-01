import java.util.ArrayList;
import java.util.List;


public class User {
    private int userId;
    private String username;
    private String membershipType;
    private List<Book> borrowedBooks = new ArrayList<>();
    private String password;

    public User(int userId, String username,  String password, String membershipType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.membershipType = membershipType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    // Getters and Setters for all attributes
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }
    
    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
    
    public boolean authenticate(String enteredPassword) {
        return enteredPassword.equals(password);
    }

    public void borrowBook(Book book, Library library) {
        if (getMembershipType().equalsIgnoreCase("premium") || (getMembershipType().equalsIgnoreCase("regular") && borrowedBooks.size() < 2)) {
            if (book.getAvailabilityStatus() == 1) {
                library.borrowBook(this, book);
                System.out.println("Book '" + book.getTitle() + "' has been borrowed by " + getUsername());
            } else {
                System.out.println("Book '" + book.getTitle() + "' is not available for borrowing.");
            }
        } else {
            System.out.println("You have reached the borrowing limit.");
        }
    }
}
