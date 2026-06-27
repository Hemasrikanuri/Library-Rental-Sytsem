import java.util.*;
class Book {
    private int bookId;
    private String title;
    private String author;
    private double price;
    private boolean isAvailable;
    static int totalBooks = 0;       
    final double LATE_FEE = 2.5;      
    Book() {
        this.bookId = 0;
        this.title = "";
        this.author = "";
        this.price = 0.0;
        this.isAvailable = true;
        totalBooks++;
    }
    Book(int id, String title, String author, double price) {
        this.bookId = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isAvailable = true;
        totalBooks++;
    }
    public int getBookId(){
        return bookId;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public double getPrice(){
        return price;
    }
    public boolean isAvailable(){
        return isAvailable;
    }
    public static void displayTotalBooks() {
    System.out.println("Total Books in Library: " + totalBooks);
    }
    public static void decreaseTotalBooks() {
    totalBooks--;
    }
    void displayDetails() {
        System.out.printf("%d\t%s\t%s\t%.2f\t%s\n",
            bookId, title, author, price, isAvailable ? "Available" : "Rented");
    }
    void rentBook() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Book rented successfully!");
        } else {
            System.out.println("Book already rented!");
        }
    }
    void returnBook() {
        if(!isAvailable){
            isAvailable = true;
            System.out.println("Book returned successfully!");
        }
        else{
            System.out.println("Book is already available!");
        }
    }
    public String toString() {
        return title + " by " + author;
    }
}
class Library {
    private ArrayList<Book> books = new ArrayList<>();
    void addBook(Book b) {
        for(Book book : books){
            if(book.getBookId() == b.getBookId()){
                System.out.println("Book ID already exists");
                return;
            }
        }
        books.add(b);
        System.out.println("Book added successfully!");
    }
    void searchBook(String title) {
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                b.displayDetails();
                found = true;
            }
        }
        if (!found)
            System.out.println("Book not found!");
    }
    void searchBookByAuthor(String author) {
        boolean found = false;
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                b.displayDetails();
                found = true;
            }
        }
        if (!found)
            System.out.println("Book not found!");
    }
    void deleteBook(int id){
        for(Book b : books){
            if(b.getBookId()==id){
                books.remove(b);
                Book.decreaseTotalBooks();
                System.out.println("Book deleted successfully!");
                return;
            }
        }
        System.out.println("Book ID not found!");
    }
    void rentBook(int id) {
        for (Book b : books) {
            if (b.getBookId() == id) {
                b.rentBook();
                return;
            }
        }
        System.out.println("Book ID not found!");
    }
    void returnBook(int id) {
        for (Book b : books) {
            if (b.getBookId() == id) {
                b.returnBook();
                return;
            }
        }
        System.out.println("Book ID not found!");
    }
    void displayAllBooks(){
        if(books.isEmpty()){
            System.out.println("No books in library.");
            return;
        }
        System.out.println("ID\tTitle\tAuthor\tPrice\tStatus");
        for(Book b : books){
            b.displayDetails();
        }
    }
    void displayAvailableBooks() {
        System.out.println("\nAvailable Books:");
        System.out.println("ID\tTitle\tAuthor\tPrice\tStatus");
        for (Book b : books) {
            if (b.isAvailable()) b.displayDetails();
        }
    }
    void displayRentedBooks() {
        System.out.println("\nRented Books:");
        System.out.println("ID\tTitle\tAuthor\tPrice\tStatus");
        for (Book b : books) {
            if (!b.isAvailable()) b.displayDetails();
        }
    }
}
public class LibraryRentalSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String libraryName = (args.length > 0) ? args[0] : "City Library";
        Library lib = new Library();
        int choice;
        do {
            System.out.println("\n===== Welcome to " + libraryName + " =====");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book by Title");
            System.out.println("3. Search Book by Author");
            System.out.println("4. Delete Book");
            System.out.println("5. Rent Book");
            System.out.println("6. Return Book");
            System.out.println("7. Show All Books");
            System.out.println("8. Show Available Books");
            System.out.println("9. Show Rented Books");
            System.out.println("10. Show Total Books");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");
            try{
                choice = sc.nextInt();
            }
            catch(InputMismatchException e){
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                choice=0;
            }
            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    lib.addBook(new Book(id, title, author, price));
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("Enter Title to Search: ");
                    lib.searchBook(sc.nextLine());
                    break;
                case 3:
                    sc.nextLine();
                    System.out.print("Enter Author to Search: ");
                    lib.searchBookByAuthor(sc.nextLine());
                    break;
                case 4:
                    System.out.println("Enter Book ID to Delete: ");
                    lib.deleteBook(sc.nextInt());
                    break;   
                case 5:
                    System.out.print("Enter Book ID to Rent: ");
                    try{
                        lib.rentBook(sc.nextInt());
                    }
                    catch (InputMismatchException e){
                        System.out.println("Please enter a valid Book ID.");
                        sc.nextLine();
                    }
                    break;
                case 6:
                    System.out.print("Enter Book ID to Return: ");
                    try{
                        lib.returnBook(sc.nextInt());
                    }
                    catch (InputMismatchException e){
                        System.out.println("Please enter a valid Book ID.");
                        sc.nextLine();
                    }                    
                    break;
                case 7:
                    lib.displayAllBooks();
                    break;
                case 8:
                    lib.displayAvailableBooks();
                    break;
                case 9:
                    lib.displayRentedBooks();
                    break;
                case 10:
                    Book.displayTotalBooks();
                    break;
                case 11:
                    System.out.println("Exiting... Thank you for using " + libraryName + "!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 11);
        sc.close();
    }
}
