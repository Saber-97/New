/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author Admin
 */
public class loging {
    
    private int id;
    private double balance;
    private String name;
    private String password;
    private String email;
    

    
    
    public int getId(){
    
        return id;
    }
    public double getPrice(){
    
        return balance;
    }
    public String getName(){
    
        return name;
    }
    public String getDescription(){
    
        return password;
    }
    public void setOwn(int id, String name, String email, String pass, double balance){
    
        this.id =id ;
        this.name = name;
        this.email = email;
        this.password = pass;
        this.balance = balance;
    }
    public int setOwn(int id){
    
        this.id =id ;
        return id;
    }
    
    public loging(){
    
        
        
    }
    public loging(int id, String Name,String email,String pass, double balance){
        this.id =id ;
        this.name =Name ;
        this.email =email ;
        this.password = pass  ;
        this.balance = balance  ;
        
    
    }
    public loging(int id){
        this.id = id;
        
        
    
    }
    
    
    
}
