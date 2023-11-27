package bookstore;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class Admin extends User {
  private ArrayList<Book> books = new ArrayList<Book>();
  private ArrayList<Customer> customers = new ArrayList<Customer>();
  public Admin(String username, String password){
    super(username, password, UserTypeEnum.ADMIN);
    /* Populate books, and customers */
    loadBooks();
    loadCustomers();
  }
  
  public ArrayList<Book> getBooks (){
      return this.books;
  }
  
  public ArrayList<Customer> getCustomers (){
      return this.customers;
  }
  public void addBook(Book book){
    /* Insert book into books database file */
    if(bookExists(book)){
      return;
    }
    File bookDataFile = new File("books.txt");
      try{
        BufferedWriter writer = new BufferedWriter(new FileWriter(bookDataFile, true));
        writer.write(book.title() + "," + book.price() + "\n");
        writer.close();
        this.books.add(book);
        return;
      } catch (IOException e){
          System.out.println("Problem Writing to File");
      }
  }

  public void loadBooks(){
    File bookDataFile = new File("books.txt");
    BufferedReader reader;
    try{
      reader = new BufferedReader(new FileReader(bookDataFile));
      String line = reader.readLine();
      while (line != null){
        String[] data = line.split(",");
        if(data.length != 2){
          reader.close();
          return;
        }
        books.add(new Book(data[0], Float.parseFloat(data[1]), this));
        line = reader.readLine();
      }
      reader.close();
    } catch (Exception e){
        System.out.println("Problem reading File");
    }
  }

  public void loadCustomers(){
    File customersDataFile = new File("customers.txt");
    BufferedReader reader;
    try{
      reader = new BufferedReader(new FileReader(customersDataFile));
      String line = reader.readLine();
      while (line != null){
        String[] data = line.split(",");
        if(data.length != 3){
          reader.close();
          return;
        }
        customers.add(new Customer(data[0], data[1], Integer.parseInt(data[2])));
        line = reader.readLine();
      }
      reader.close();
    } catch (Exception e){
        System.out.println("Problem reading File");
    }
  }

  public boolean bookExists(Book book){
    String booksDataFile = "books.txt";
    BufferedReader reader;
    try{
      reader = new BufferedReader(new FileReader(booksDataFile));
      String line = reader.readLine();
      while (line != null){
        String[] data = line.split(",");
        if (data[0].equals(book.title())){
          reader.close();
          return true;
        }
        line = reader.readLine();
      }
    } catch (IOException e){
      System.out.println("Problem reading File");
    }
    return false;
  }

  public void removeBook(Book book){
    /* Insert book into books database file */
    if(!bookExists(book)){
      return;
    }
    File booksDataFile = new File("books.txt");
    BufferedReader reader;
    try{
      reader = new BufferedReader(new FileReader(booksDataFile));
      String fileBuffer = "";
      String line = reader.readLine();
      while (line != null){
        String[] data = line.split(",");
        if (data[0].equals(book.title())){
          line = reader.readLine();
          continue;
        }
        fileBuffer += line + "\n";
        line = reader.readLine();
      }
      reader.close();

      BufferedWriter writer = new BufferedWriter(new FileWriter(booksDataFile));
      writer.write(fileBuffer);
      writer.close();
    } catch (IOException e){
        System.out.println("Problem Writing to File");
    }
    books.remove(book);
  }

  public boolean registerCustomer(String username, String password){
    /* Insert into username, password into customers.txt */
    /* TODO: CREATE STATIC CLASS TO CONTAIN CONSTANTS */
    if(userExists(username, password)){
      return false;
    } else{
      File customersDataFile = new File("customers.txt");
      try{
        BufferedWriter writer = new BufferedWriter(new FileWriter(customersDataFile, true));
        writer.write(username + "," + password + "," + "0" + "\n");
        writer.close();
        customers.add(new Customer(username, password, 0));
        return true;
      } catch (IOException e){
          System.out.println("Problem Writing to File");
      }
    }
    return false;
  }

  public void removeCustomer(String username){
    for(Customer customer: customers){
      if (customer.getUsername().equals(username)){
        customers.remove(customer);
        break;
      }
    }

    /* Write new customers to file */
    File customersDataFile = new File("customers.txt");
    try{
      BufferedWriter writer = new BufferedWriter(new FileWriter(customersDataFile));
      for(Customer customer: customers){
        writer.write(customer.getUsername() + "," + customer.getPassword() + "," + customer.getPoints() + "\n");
      }
      writer.close();
    } catch (IOException e){
        System.out.println("Problem Writing to File");
    }
  }

  public boolean userExists (String username, String password){
    File customersDataFile = new File("customers.txt");
    BufferedReader reader;
      try{
        reader = new BufferedReader(new FileReader(customersDataFile));
        String line = reader.readLine();
        /* Scan for existing username, password */
        while (line != null){
          String[] data = line.split(",");
          if (data[0].equals(username) && data[1].equals(password)){
            reader.close();
            return true;
          }
          line = reader.readLine();
        }
        reader.close();
      } catch (IOException e){
          System.out.println("Problem reading  File");
      }
      return false;
  }

  public void processOrder (Book book, Customer customer){
    removeBook(book);
  }
}
