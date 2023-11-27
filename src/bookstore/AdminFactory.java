/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;

public class AdminFactory {
    private static Admin admin;
    public static Admin buildAdmin(){
        if(admin == null)
            admin = new Admin("admin", "admin");
        return admin;
    }
}
