import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Library {
    private List<Admin> admins;
    private List<User> users;
    private Map<Integer, Book> books;
    private List<Booking> bookings;
    private List<Event> events;
    private int bookingIdCounter;
    private int userIdCounter;
    private int nextBookId;
    private HashMap<String, String> adminCredentials;

    public Library() {
    	this.bookingIdCounter = 1;
        this.admins = new ArrayList<>();
        this.users = new ArrayList<>();
        this.books = Admin.getBooksMap();
        bookings = new ArrayList<>();
        this.events = new ArrayList<>();
        this.userIdCounter = 1;
        adminCredentials = new HashMap<>();
        adminCredentials.put("admin_username", "admin_password");
        nextBookId = 1;
    }
    
    public void deleteEvent(Event event) {
        events.remove(event);
    }

    
    public Event getEventByName(String eventName) {
        for (Event event : events) {
            if (event.getEventName().equals(eventName)) {
                return event;
            }
        }
        return null; // Return null if the event with the specified name is not found
    }
    
    public List<Event> getEvents() {
        return events; // Return the list of events
    }
    
    public void deleteBook(Book book) {
        // Check if the book exists in the library
        if (books.containsValue(book)) {
            // Remove the book from the HashMap
            books.remove(book.getBookID());
            System.out.println("Book '" + book.getTitle() + "' deleted successfully.");
        } else {
            System.out.println("Book '" + book.getTitle() + "' not found in the library.");
        }
    }

    // Methods to manage admins
    public void addAdmin(Admin admin) {
        admins.add(admin);
    }
    
    public int getNextBookId() {
        return nextBookId++;
    }
    
    public Admin authenticateAdmin(String username) {
        for (Admin admin : admins) {
            if (admin.getAdminUserName().equals(username)) {
                return admin;
            }
        }
        return null; // Return null if admin username is not found
    }


    // Methods to manage users
    public void addUser(User user) {
        users.add(user);
    }

    // Methods to manage books
    public void addBook(Book book) {
        books.put(book.getBookID(), book);
    }

    // Methods to manage bookings
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }
    
    public void addEvent(Event event) {
        events.add(event);
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }
    
    public Map<Integer, Book> getBooks() {
        return books;
    }
    
    public void borrowBook(User user, Book book) {
        // Check book availability and other conditions here
    	LocalDate currentDate = LocalDate.now(); // Get current system date
        LocalDate endDate = currentDate.plusDays(15);
        
        // Assuming the booking constructor includes appropriate parameters:
        Booking newBooking = new Booking(bookingIdCounter++, user, book, currentDate, endDate); // Initialize booking details
        addBooking(newBooking); // Add the new booking to the library's bookings
        book.setAvailabilityStatus(0); // Update book availability
    }
    
    public List<User> getUsers() {
        return this.users;
    }
    
    public boolean signUpForEvent(User user, Event event) {
        if (events.contains(event)) {
            if (!event.getSignups().contains(user)) {
                event.getSignups().add(user);
                return true;
            } else {
                System.out.println("You are already signed up for this event.");
                return false;
            }
        } else {
            System.out.println("Event not found.");
            return false;
        }
    }
    
    public User authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Return the user if authentication succeeds
            }
        }
        return null; // Return null if authentication fails
    }
    
    public User getUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null; // Return null if user is not found
    }
    
    public Book getBookById(int bookId) {
        return books.get(bookId); // Assuming 'books' is a Map<Integer, Book>
    }
    
    public int generateUserId() {
        return userIdCounter++;
    }

    public void registerUser(String username, String password, String membershipType) {
        int userId = generateUserId();
        User newUser = new User(userId, username, password, membershipType);
        //newUser.setPassword(password);
        users.add(newUser);
        System.out.println("User account created successfully.");
    }
    // Additional methods for managing entities as needed
}
