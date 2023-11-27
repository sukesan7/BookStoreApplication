package bookstore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;

public class Customer extends User {
  private int points;
  ArrayList<Book> cart = new ArrayList<Book>();
  ArrayList<Order> orders = new ArrayList<Order>();
  public Customer(String username, String password, int points){
    super(username, password, UserTypeEnum.CUSTOMER);
    this.points = points;
  }

  public int getPoints(){
    return this.points;
  }

  public Status getStatus(){
    if (this.points < 1000){
      return Status.SILVER;
    }
    return Status.GOLD;
  }

  /* Return the amount of cost after redeemed, and buying a book
   *  If return 0, then the whole book has been paid for by points
   *  If return > 0, then the book has been paid for by points, 
   *  and the customer needs to pay the remaining amount
   *  Will alter Customer's points
   */
  public float redeemPointsAndGetCost(){
    float cost_cad = 0;
    for(Book book: this.cart){
      book.owner().processOrder (book, this);
      cost_cad += book.price();
    }
    float points_to_cad = Math.min(cost_cad, this.points / 100);
    float left_over = cost_cad - points_to_cad;
    if(left_over == 0){
      this.points -= cost_cad * 100;
    } else{
      this.points = (int) Math.floor(left_over * 100);
    }
    updateCustomerData();
    return left_over;
  }

  public ArrayList<Book> getCart(){
    return this.cart;
  }

  public void updateCustomerData(){
    /* Update the customer data on file */
    File customerDataFile = new File("customers.txt");
    String fileBuffer = "";
    try{
      BufferedReader reader = new BufferedReader(new java.io.FileReader(customerDataFile));
      String line = reader.readLine();
      while (line != null){
        String[] data = line.split(",");
        if(data.length != 3){
          reader.close();
          return;
        }
        if(data[0].equals(this.getUsername())){
          fileBuffer += this.getUsername() + "," + this.getPassword() + "," + this.points + "\n";
        } else{
          fileBuffer += line + "\n";
        }
        line = reader.readLine();
      }
      BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(customerDataFile));
      writer.write(fileBuffer);
      writer.close();
      reader.close();
    } catch(Exception e){
      System.out.println("Problem reading File");
    }
  }
  /* 
   * Return the number of points earned
   * after purchasing a book with only cash (no points)
   */
  public int pointsEarnedAfterPurchase(){
    double cost_cad = 0;
    for(Book book: this.cart){
      book.owner().processOrder (book, this);
      cost_cad += book.price();
    }
    int pointsGained = (int) Math.floor(cost_cad * 10);
    points += pointsGained;
    updateCustomerData();
    return pointsGained;
  }

  public void clearCart(){
    this.cart.clear();
  }

  public void addOrder(Order order){
    this.orders.add(order);
  }

  public Order getMostRecentOrder(){
    return this.orders.get(this.orders.size() - 1);
  }
}
