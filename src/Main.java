import java.util.Scanner;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Admin admin = new Admin(1, "AdminName");
        library.addAdmin(admin);
        //Event newEvent = admin.createEvent();
        //library.addEvent(newEvent);
        System.out.println("Event created and added to the library.");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Create an account");
            System.out.println("4. Exit");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter admin username:");
                    String adminUsername = scanner.nextLine();

                    Admin loggedInAdmin = library.authenticateAdmin(adminUsername);

                    if (loggedInAdmin != null) {
                        System.out.println("Admin login successful. Welcome, " + loggedInAdmin.getAdminUserName() + "!");

                        boolean adminMenu = true;
                        while (adminMenu) {
                            System.out.println("\nAdmin Menu:");
                            System.out.println("1. Add Book");
                            System.out.println("2. Update Book");
                            System.out.println("3. Delete Book");
                            System.out.println("4. Create Event");
                            System.out.println("5. Delete Event");
                            System.out.println("6. Exit Admin Menu");

                            int adminOption = scanner.nextInt();
                            scanner.nextLine();

                            switch (adminOption) {
	                            case 1:
	                                // Logic to add books
	                                System.out.println("Enter book details:");
	                                System.out.println("Enter title:");
	                                String title = scanner.nextLine();
	                                System.out.println("Enter author:");
	                                String author = scanner.nextLine();
	                                System.out.println("Enter genre:");
	                                String genre = scanner.nextLine();
	                                System.out.println("Enter availability status (0 or 1):");
	                                int availabilityStatus = scanner.nextInt();
	
	                                // Create a new book object and add it to the library
	                                Book newBook = new Book(library.getNextBookId(), title, author, genre, availabilityStatus);
	                                library.addBook(newBook);
	                                break;
	                                
                                case 2:
                                	System.out.println("Enter book ID to update:");
                                    int bookIdToUpdate = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline character

                                    Book bookToUpdate = library.getBookById(bookIdToUpdate);
                                    if (bookToUpdate != null) {
                                        System.out.println("Enter updated title:");
                                        bookToUpdate.setTitle(scanner.nextLine());
                                        System.out.println("Enter updated author:");
                                        bookToUpdate.setAuthor(scanner.nextLine());
                                        System.out.println("Enter updated genre:");
                                        bookToUpdate.setGenre(scanner.nextLine());
                                        System.out.println("Enter updated availability status (0 or 1):");
                                        bookToUpdate.setAvailabilityStatus(scanner.nextInt());
                                    } else {
                                        System.out.println("Book not found.");
                                    }
                                    break;
                                case 3:
                                	System.out.println("Enter book ID to delete:");
                                    int bookIdToDelete = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline character

                                    Book bookToDelete = library.getBookById(bookIdToDelete);
                                    if (bookToDelete != null) {
                                        library.deleteBook(bookToDelete);
                                        System.out.println("Book deleted successfully.");
                                    } else {
                                        System.out.println("Book not found.");
                                    }
                                    break;

                                case 4:
                                	System.out.println("Enter event details:");
                                    System.out.println("Enter event name:");
                                    String eventName = scanner.nextLine();
                                    System.out.println("Enter event description:");
                                    String eventDescription = scanner.nextLine();
                                    System.out.println("Enter event date (YYYY-MM-DD): ");
                                    String inputDate = scanner.nextLine();
                                    LocalDate eventDate = LocalDate.parse(inputDate);

                                    // Create a new event object and add it to the library
                                    Event newEvent = new Event(eventName, eventDate, eventDescription);
                                    library.addEvent(newEvent);
                                    System.out.println("Event created successfully.");
                                    break;
                                    
                                case 5:
                                	System.out.println("Enter event name to delete:");
                                    String eventNameToDelete = scanner.nextLine();

                                    Event eventToDelete = library.getEventByName(eventNameToDelete);
                                    if (eventToDelete != null) {
                                        library.deleteEvent(eventToDelete);
                                        System.out.println("Event deleted successfully.");
                                    } else {
                                        System.out.println("Event not found.");
                                    }
                                    break;
                                case 6:
                                    adminMenu = false;
                                    break;
                                default:
                                    System.out.println("Invalid admin option.");
                            }
                        }
                    } else {
                        System.out.println("Admin login failed. Invalid credentials.");
                    }
                    break;
                case 2:
                    System.out.println("Enter username:");
                    String username = scanner.nextLine();
                    System.out.println("Enter password:");
                    String password = scanner.nextLine();

                    // Authenticate the user
                    User loggedInUser = library.authenticateUser(username, password);

                    if (loggedInUser != null) {
                    	while (true) {
                          System.out.println("\nSelect an option:");
                          System.out.println("1. Display available books");
                          System.out.println("2. Borrow a book");
                          System.out.println("3. Sign up for an event");
                          System.out.println("4. Create an account");
                          System.out.println("5. Exit");
              
                          int userOption = scanner.nextInt();
                          scanner.nextLine(); // Consume the newline character
              
                          switch (userOption) {
                              case 1:
                                  // Displaying available books
                                  System.out.println("\nAvailable Books:");
                                  for (Book book : library.getBooks().values()) {
                                      if (book.getAvailabilityStatus() == 1) {
                                          System.out.println(book.getTitle());
                                      }
                                  }
                                  break;
                              case 2:
                                  
                                  if (loggedInUser != null) {
                                      //System.out.println("Login successful. Welcome, " + loggedInUser.getUsername() + "!");
              
                                      System.out.println("Enter book ID:");
                                      int bookId = scanner.nextInt();
              
                                      Book selectedBook = library.getBookById(bookId);
              
                                      if (selectedBook != null) {
                                          loggedInUser.borrowBook(selectedBook, library);
                                      } else {
                                          System.out.println("Invalid book ID.");
                                      }
                                  } else {
                                      System.out.println("Invalid username or password.");
                                  }
                                  break;
                              case 3:
              
                                  // Authenticate the user for event signup
                                  User loggedInUserForEvent = library.authenticateUser(username, password);
              
                                  if (loggedInUserForEvent != null) {
                                      //System.out.println("Login successful. Welcome, " + loggedInUserForEvent.getUsername() + "!");
              
                                      System.out.println("Enter user ID:");
                                      int userIDForEvent = scanner.nextInt();
                                      User userForEvent = library.getUserById(userIDForEvent);
              
                                      if (userForEvent != null) {
                                          // Display available events
                                          System.out.println("Available Events:");
                                          for (Event event : library.getEvents()) {
                                              System.out.println(event.getEventName());
                                          }

                                          System.out.println("Enter the event name to sign up for:");
                                          scanner.nextLine(); // Consume the newline character
                                          String selectedEventName = scanner.nextLine();

                                          Event selectedEvent = library.getEventByName(selectedEventName);
                                          if (selectedEvent != null) {
                                              library.signUpForEvent(userForEvent, selectedEvent);
                                              System.out.println("Signed up successfully for the event: " + selectedEvent.getEventName());
                                          } else {
                                              System.out.println("Invalid event name.");
                                          }
                                      } else {
                                          System.out.println("Invalid user ID.");
                                      }
                                  } else {
                                      System.out.println("Invalid username or password.");
                                  }
                                  break;
                              case 4:
                                  // Create an account
                                  System.out.println("Enter username:");
                                  String newUsername = scanner.nextLine();
                                  System.out.println("Enter password:");
                                  String newPassword = scanner.nextLine();
                                  System.out.println("Enter membership type:");
                                  String newMembershipType = scanner.nextLine();
              
                                  library.registerUser(newUsername, newPassword, newMembershipType);
                                  break;
                              case 5:
                                  System.out.println("Exiting...");
                                  scanner.close();
                                  System.exit(0);
                                  break;
                              default:
                                  System.out.println("Invalid option. Please select again.");
                          }
                      }
                  }
                     else {
                        System.out.println("Invalid username or password.");
                    }
                    break;

                case 3:
                    System.out.println("Enter username:");
                    String newUsername = scanner.nextLine();
                    System.out.println("Enter password:");
                    String newPassword = scanner.nextLine();
                    System.out.println("Enter membership type:");
                    String newMembershipType = scanner.nextLine();

                    // Register the new user
                    library.registerUser(newUsername, newPassword, newMembershipType);
                    break;

                    
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please select again.");
        }}
        }
    }



