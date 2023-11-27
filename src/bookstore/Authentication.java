/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Authentication implements IAuth{
    public final String ADMIN_USERNAME = "admin";
    public final String ADMIN_PASSWORD = "admin";
    public final String CUSTOMER_DATA_FILE = "customers.txt";
    @Override
    public UserTypeEnum authenticateUser(String username, String password){
        if(adminLogin(username, password)){
            return UserTypeEnum.ADMIN;
        }

        if(customerLogin(username, password)){
            return UserTypeEnum.CUSTOMER;
        }
        
        return UserTypeEnum.UNAUTHENTICATED;
    }
    
    public boolean adminLogin(String username, String password){
        return username.equals(ADMIN_USERNAME) && 
               password.equals(ADMIN_PASSWORD);
    }
    
    public boolean customerLogin(String username, String password){
      BufferedReader reader;
      String customersDataFile = "customers.txt";
      try{
        reader = new BufferedReader(new FileReader(customersDataFile));
        String line = reader.readLine();
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
          System.out.println("Problem Writing to File");
      }
      return false;
    }
}
