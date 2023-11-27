/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore.ui;
import bookstore.states.ApplicationState;
import bookstore.states.LoginState;
import bookstore.Admin;
import bookstore.AdminFactory;
import bookstore.Customer;

import java.io.File;
import java.io.IOException;

import bookstore.states.ActionsEnum;
/**
 *
 * @author porom
 */
public class BookStoreLauncher {
    static ApplicationState state;
    public static Admin admin; /* Probably bad to have a static admin, 
                            but the project requirements indicate
                            a single admin user, so it's ok */
    public static Customer current_customer;
    public static void main (String [] args){
      /* Create customers.txt and books.txt file if not exists */
      File customersDataFile = new File("customers.txt");
      File bookDataFile = new File("books.txt");
      admin = AdminFactory.buildAdmin();
      
      if (!customersDataFile.exists()){
        try{
          customersDataFile.createNewFile();
        } catch (IOException e){
          System.out.println("Problem Setting up Book Store");
          return;
        }
      }
      if (!bookDataFile.exists()){
        try{
          bookDataFile.createNewFile();
        } catch (IOException e){
          System.out.println("Problem Setting up Book Store");
          return;
        }
      }

      state = new LoginState();
      state.renderState();
    }
    
    public static void processEvent (ActionsEnum action){
        state.unrenderState();
        state = state.transitionState(action);
        state.renderState();
    }
    
    public static void processAdminInstantiation(String username, String password){
        admin = new Admin(username, password);
    }

    public static void processCustomerInstation(String username, String password, int points){
        current_customer = new Customer(username, password, points);
    }
}