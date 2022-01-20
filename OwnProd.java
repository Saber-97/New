/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Admin
 */
public class OwnProd {
    private int id;
    private int sales;
    private int count;
    private double price;
    private double balance;
    private String name;
    private String description;
    
    private int UserId;
    private String category;
    private int TempCount;
    private int FromUser;
    
    
    
    private int curUser;
    private String curUsername;
    private String curEmail;
    private String curPass;
    private double curBalance;
    public int getSales(){
    
        return sales;
    }
    public int getFromUser(){
    
        return FromUser;
    }
    public int getCount(){
    
        return count;
    }
    public double getPrice(){
    
        return price;
    }
    public String getName(){
    
        return name;
    }
    public String getDescription(){
    
        return description;
    }
    
    public void setName(String Name){
    this.name = Name;
    
    }
     public void setId(int id){
    this.id = id;
    
    }
     public void setFromUser(int id){
    this.FromUser = id;
    
    }
    public void setPrice(double price){
    this.price = price;
    
    }
    public void setCount(int count){
    this.count = count;
    
    }
    public void setDesc(String desc){
    this.description = desc;
    
    }
    
     public void SetUser(int user){
    this.UserId = user;
    
    }
      public void SetSales(int sales){
    this.sales = sales;
    
    }
         public void SetCat(String categ){
        this.category = categ;
    
    }
    
    public OwnProd(){
    
        
        
    }
    boolean isNumeric(String string) {
        int intValue;



        if(string == null || string.equals("")) {

            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {

        }
        return false;
}
    public OwnProd(int id,int usId,String Name,double price, int count,String desc,String category){
        this.name = Name;
        this.description = desc;
        this.id = id;
        this.price = price;
        this.count = count;
        this.UserId = usId;
        this.category = category;
        
    
    }
     public OwnProd(int id,int usId,String Name,double price, int count,String desc,String category,int sales){
        this.name = Name;
        this.description = desc;
        this.id = id;
        this.price = price;
        this.count = count;
        this.UserId = usId;
        this.category = category;
        this.sales = sales;
        
    
    }
     
      public OwnProd(int id,int usId,String Name,double price, int count,String desc,String category,int sales,int countTemp){
        this.name = Name;
        this.description = desc;
        this.id = id;
        this.price = price;
        this.count = count;
        this.UserId = usId;
        this.category = category;
        this.sales = sales;
        this.TempCount = countTemp;
    
    }
    public OwnProd(int prId,int usId,int id, String Name,double price, int count,String desc,int sales){
        this.name = Name;
        this.description = desc;
        this.id = id;
        this.price = price;
        this.count = count;
        this.UserId = usId;
        this.sales = sales;
    
    }
    
     public OwnProd(String name,int id, int count,double price,int fromUser , int usId ){
        this.name = name;
        this.FromUser = fromUser;
        this.id = id;
        this.price = price;
        this.count = count;
        this.UserId = usId;
    
    }
     
     public OwnProd(String name){
        this.name = name;
    
    }
      public OwnProd(int prId){
        this.id = prId;
    
    }
    
    
      public int getId(){
    
        return id;
    }
       public String getCat(){
    
        return category;
    }
      public int getUsId(){
    
        return UserId;
    }
      public int getTemp(){
    
        return TempCount;
    }
    
    
    public boolean execOwnQuery(String OwnQuery , OwnProd Own){
        
        
        try {
        String query = " SELECT `UserId` FROM  `curuser` WHERE 1";
        PreparedStatement st;
            st = DbConnection.getConnection().prepareStatement(query);
            ResultSet rs = st.executeQuery(); 
            if(rs.next()){
                curUser = rs.getInt("UserId"); 
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String query = " SELECT `balance` FROM `users` WHERE `UserId` = ?";
             PreparedStatement st;
            st = DbConnection.getConnection().prepareStatement(query);
            st.setInt(1, Own.getId());
            ResultSet rs = st.executeQuery(); 
            if(rs.next()){

                balance =  rs.getInt("balance");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            String query = " SELECT `UserId`, `Username`, `Email`, `Password`, `balance` FROM `users` WHERE `UserId` = ?";
             PreparedStatement st;
            st = DbConnection.getConnection().prepareStatement(query);
            st.setInt(1, curUser);
            ResultSet rs = st.executeQuery(); 
            if(rs.next()){
                curUser = rs.getInt("UserId"); 
                curBalance =  rs.getInt("balance");
                curEmail = rs.getString("Email");
                curPass= rs.getString("Password");
                curUsername = rs.getString("Username");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        PreparedStatement ps;
        if(OwnQuery.equals("add")){
        
            try {
                //SELECT * FROM `users` WHERE 1
                
                ps = DbConnection.getConnection().
                       
                        prepareStatement("INSERT INTO `products`( `UserId`, `ProductName`, `ProductPrice`,"
                                + " `ProductCount`, `productSales`, `Description`,`Category`) VALUES (?,?,?,?,?,?,?)");
                ps.setInt(1, curUser); 
                ps.setString(2, Own.getName()); 
                ps.setDouble(3, Own.getPrice());
                ps.setInt(4, Own.getCount()); 
                ps.setInt(5, Own.getSales());
                ps.setString(6, Own.getDescription()); 
                ps.setString(7, Own.getCat());
                return ps.executeUpdate() > 0;
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            
            
        }
        if(OwnQuery.equals("buy")){
        
            try {
                //SELECT * FROM `users` WHERE 1
                
                ps = DbConnection.getConnection().
                       
                        prepareStatement("UPDATE `products` SET `ProductCount`= ? ,`productSales`= ? WHERE `ProductId` = ?");
                
                ps.setInt(1, Own.getCount() - Own.getTemp()); 
                ps.setInt(2, Own.getSales() + Own.getTemp());
                ps.setInt(3, Own.getUsId());
                
                PreparedStatement ps2 = DbConnection.getConnection().
                       
                        prepareStatement("DELETE FROM `car` WHERE  `ProductId` = ?");
                ps2.setInt(1, Own.getUsId());
                ps2.execute();
                
                PreparedStatement ps3 = DbConnection.getConnection().
                       
                        prepareStatement("UPDATE `users` SET `balance`=? WHERE `UserId` = ?");
                ps3.setDouble(1, curBalance - Own.getPrice());
                ps3.setInt(2, curUser);
                ps3.execute();
                
                PreparedStatement ps4 = DbConnection.getConnection().
                       
                        prepareStatement("INSERT INTO `history`(`UserId`, `FromUser`, `ProductId`, `count`, `TotalPrice`) VALUES (?,?,?,?,?)");
                
                ps4.setInt(3, Own.getUsId());
                ps4.setInt(1, curUser);
                ps4.setInt(2, Own.getId());
                ps4.setInt(4, Own.getTemp());
                ps4.setDouble(5, Own.getPrice());
                ps4.execute();
                
                PreparedStatement ps5 = DbConnection.getConnection().
                       
                        prepareStatement("UPDATE `users` SET `balance`=? WHERE `UserId` = ?");
                ps5.setDouble(1, balance + Own.getPrice());
                ps5.setInt(2, Own.getId());
                ps5.execute();
                
                PreparedStatement ps6 = DbConnection.getConnection().
                       
                        prepareStatement("INSERT INTO `notifications`( `ProductId`, `UserId`, `Status`) VALUES (?,?,?)");
                ps6.setDouble(2, Own.getId());
                ps6.setInt(1, Own.getUsId());
                ps6.setInt(3, 1);
                ps6.execute();
                
                return ps.executeUpdate() > 0;
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            
            
            
        }
         if(OwnQuery.equals("addFavourite")){
        
            try {
                //SELECT * FROM `users` WHERE 1
                
                ps = DbConnection.getConnection().
                       
                        prepareStatement("INSERT INTO `favourite`( `UserId`, `ProductId`) VALUES (?,?)");
                
                ps.setInt(1, curUser); 
                ps.setInt(2, Own.getUsId());
                return ps.executeUpdate() > 0;
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
         if(OwnQuery.equals("deleteFav")){
        
            try {
                //SELECT * FROM `users` WHERE 1
                
                ps = DbConnection.getConnection().
                       
                        prepareStatement("DELETE FROM `favourite` WHERE `ProductId` = ?");
                
                ps.setInt(1, Own.getUsId());
                return ps.executeUpdate() > 0;
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
          if(OwnQuery.equals("edit")){
        
            try {
                
                ps = DbConnection.getConnection().
                       
                        prepareStatement("UPDATE `products` SET `ProductName`=?,`ProductPrice`=? ,"
                                + "`ProductCount`=? ,`Description`=? WHERE `ProductId` = ? ");
                
                ps.setString(1, Own.getName()); 
                ps.setDouble(2, Own.getPrice());
                ps.setInt(3, Own.getCount()); 
                ps.setString(4, Own.getDescription()); 
                ps.setInt(5, Own.getUsId());
                 
                return ps.executeUpdate() > 0;
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
          if(OwnQuery.equals("delete")){
        
            try {
                
                ps = DbConnection.getConnection().
                       
                        prepareStatement("DELETE FROM `products` WHERE `ProductId` = ? ");
                
                ps.setInt(1, Own.getUsId());
                 
                return ps.executeUpdate() > 0;
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
          if(OwnQuery.equals("deleteCart")){
        
            try {
                
                ps = DbConnection.getConnection().
                       
                        prepareStatement("DELETE FROM `car` WHERE `ProductId` = ? ");
                
                ps.setInt(1, Own.getUsId());
                 
                return ps.executeUpdate() > 0;
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
          
           if(OwnQuery.equals("addCart")){
       
            try {
                
                ps = DbConnection.getConnection().
                       
                        prepareStatement("INSERT INTO `car`(`CurUser`, `UserId`,"
                                + " `ProductId`, `ProductName`, `price`, `stock`, `Description`,`sales`) VALUES (?,?,?,?,?,?,?,?)");
                ps.setInt(1, curUser); 
             
                        
                
                ps.setInt(2, Own.getId()); 
                ps.setInt(3, Own.getUsId()); 
                ps.setString(4, Own.getName()); 
                ps.setDouble(5, Own.getPrice());
                ps.setInt(6, Own.getCount()); 
                ps.setString(7, Own.getDescription()); 
                ps.setInt(8, Own.getSales());
                
                return ps.executeUpdate() > 0;
                
            } catch (SQLException ex) {
                Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
           else{
                 JOptionPane.showMessageDialog(null, "Invalid operation","Logic error",2);
                  return false;
           }
       
        
    }
    
    public HashMap<String,Integer>getTypesMap(){
        String query = " SELECT `UserId` FROM  `curuser` WHERE 1";
            PreparedStatement st2;
            try {
                st2 = DbConnection.getConnection().prepareStatement(query);
                ResultSet rs = st2.executeQuery(); 
                if(rs.next()){
                    curUser = rs.getInt("UserId");         
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        HashMap<String,Integer> map = new HashMap<>();
        PreparedStatement st;
        ResultSet rs;
        
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT * FROM `products` WHERE `UserId`= ? ");
            st.setInt(1, curUser); 
            rs = st.executeQuery(); 
            
            OwnProd own;
            
            while (rs.next()){
                own = new OwnProd(rs.getInt(1),rs.getInt(2),rs.getString(3),
                        rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                map .put(own.getName() , own.getId());
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;

}
    
    public HashMap<String,Integer>getTypesMap6(){
        String query = " SELECT `UserId` FROM  `curuser` WHERE 1";
            PreparedStatement st2;
            try {
                st2 = DbConnection.getConnection().prepareStatement(query);
                ResultSet rs = st2.executeQuery(); 
                if(rs.next()){
                    curUser = rs.getInt("UserId");         
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        HashMap<String,Integer> map = new HashMap<>();
        PreparedStatement st;
        ResultSet rs;
        // SELECT `ProductId` FROM `products` ORDER BY `ProductId` DESC LIMIT 1
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT * FROM `products` ORDER BY `productSales` DESC LIMIT 3");
            rs = st.executeQuery(); 
            
            OwnProd own;
            
            while (rs.next()){
                own = new OwnProd(rs.getInt(1),rs.getInt(2),rs.getString(3),
                        rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                map .put(own.getName() , own.getId());
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;

}
    
      public HashMap<String,Integer>getTypesMapHist(){
        String query = " SELECT `UserId` FROM  `curuser` WHERE 1";
            PreparedStatement st2;
            try {
                st2 = DbConnection.getConnection().prepareStatement(query);
                ResultSet rs = st2.executeQuery(); 
                if(rs.next()){
                    curUser = rs.getInt("UserId");         
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        HashMap<String,Integer> map = new HashMap<>();
        PreparedStatement st;
        ResultSet rs;
        
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT p.ProductName , c1.ProductId , c1.count ,"
                    + " c1.TotalPrice , c1.FromUser , c1.UserId\n" +
"FROM products AS p  \n" +
"LEFT JOIN history AS c1  \n" +
"ON p.ProductId=c1.ProductId WHERE c1.UserId = ? ");
            st.setInt(1, curUser); 
            rs = st.executeQuery(); 
            
            OwnProd own;
            
            while (rs.next()){
                own = new OwnProd(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getDouble(4),rs.getInt(5),
                        rs.getInt(6));
                
                map .put(own.getName() , own.getId());
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;

}
    public HashMap<String,Integer>getTypesMap2(){
        String query = " SELECT `UserId` FROM  `curuser` WHERE 1";
            PreparedStatement st2;
            try {
                st2 = DbConnection.getConnection().prepareStatement(query);
                ResultSet rs = st2.executeQuery(); 
                if(rs.next()){
                    curUser = rs.getInt("UserId");         
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        HashMap<String,Integer> map = new HashMap<>();
        PreparedStatement st;
        ResultSet rs;
        
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT * FROM `products` WHERE `UserId`!= ? ");
            st.setInt(1, curUser); 
            rs = st.executeQuery(); 
            
            OwnProd own;
            
            while (rs.next()){
                own = new OwnProd(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                map .put(own.getName() , own.getId());
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;

}
    
    public HashMap<String,Integer>getTypesMapFav(){
        String query = " SELECT `UserId` FROM  `curuser` WHERE 1";
            PreparedStatement st2;
            try {
                st2 = DbConnection.getConnection().prepareStatement(query);
                ResultSet rs = st2.executeQuery(); 
                if(rs.next()){
                    curUser = rs.getInt("UserId");         
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        HashMap<String,Integer> map = new HashMap<>();
        PreparedStatement st;
        ResultSet rs;
        
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT p.ProductId,p.UserId,p.ProductName,"
                    + "p.ProductPrice,p.ProductCount,p.productSales,p.Description,p.Category "
                    + "FROM products AS p  LEFT JOIN favourite AS c1 ON p.ProductId=c1.ProductId WHERE c1.UserId =? ");
            st.setInt(1, curUser); 
            rs = st.executeQuery(); 
            
            OwnProd own;
            
            
            while (rs.next()){
                
            
                own = new OwnProd(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                map .put(own.getName() , own.getId());
                
            
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;

}
    
    public HashMap<String,Integer>getTypesMapHist2(){
        String query = " SELECT `UserId` FROM  `curuser` WHERE 1";
            PreparedStatement st2;
            try {
                st2 = DbConnection.getConnection().prepareStatement(query);
                ResultSet rs = st2.executeQuery(); 
                if(rs.next()){
                    curUser = rs.getInt("UserId");         
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        HashMap<String,Integer> map = new HashMap<>();
        PreparedStatement st;
        ResultSet rs;
        
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT p.ProductName , c1.ProductId , c1.count ,"
                    + " c1.TotalPrice , c1.FromUser , c1.UserId\n" +
"FROM products AS p  \n" +
"LEFT JOIN history AS c1  \n" +
"ON p.ProductId=c1.ProductId WHERE c1.FromUser = ? ");
            st.setInt(1, curUser); 
            rs = st.executeQuery(); 
            
            OwnProd own;
            
            while (rs.next()){
                own = new OwnProd(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getDouble(4),rs.getInt(5),
                        rs.getInt(6));
                
                map .put(own.getName() , own.getId());
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;

}
    
    
    public HashMap<String,Integer>getTypesMap3(String searchTerm){
        //$sql = "SELECT * FROM personal_info WHERE FirstName LIKE '%$searchq%' OR LastName LIKE '%$searchq%' ORDER BY id DESC";

        String query = " SELECT `UserId` FROM  `curuser` WHERE 1";
            PreparedStatement st2;
            try {
                st2 = DbConnection.getConnection().prepareStatement(query);
                ResultSet rs = st2.executeQuery(); 
                if(rs.next()){
                    curUser = rs.getInt("UserId");         
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        HashMap<String,Integer> map = new HashMap<>();
        PreparedStatement st;
        ResultSet rs;
        
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT * FROM `products` WHERE"
                    + " `ProductName` LIKE ? AND `UserId` != ? ORDER BY `ProductId` DESC; ");
            st.setString(1, searchTerm);
            st.setInt(2, curUser);
            rs = st.executeQuery(); 
            
            OwnProd own;
            
            while (rs.next()){
                own = new OwnProd(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                map .put(own.getName() , own.getId());
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;

}
   
    
    public HashMap<String,Integer>getTypesMap4(String CatTerm){

        String query = " SELECT `UserId` FROM  `curuser` WHERE 1";
            PreparedStatement st2;
            try {
                st2 = DbConnection.getConnection().prepareStatement(query);
                ResultSet rs = st2.executeQuery(); 
                if(rs.next()){
                    curUser = rs.getInt("UserId");         
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        HashMap<String,Integer> map = new HashMap<>();
        PreparedStatement st;
        ResultSet rs;
        
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT * FROM `products` WHERE `Category` = ? AND `UserId` != ?   ");
            st.setString(1, CatTerm);
            st.setInt(2, curUser);
            rs = st.executeQuery(); 
            
            OwnProd own;
            
            while (rs.next()){
                own = new OwnProd(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                map .put(own.getName() , own.getId());
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;

}
    
    public HashMap<String,Integer>getTypesMap5(String CatTerm,String search){

        String query = " SELECT `UserId` FROM  `curuser` WHERE 1";
            PreparedStatement st2;
            try {
                st2 = DbConnection.getConnection().prepareStatement(query);
                ResultSet rs = st2.executeQuery(); 
                if(rs.next()){
                    curUser = rs.getInt("UserId");         
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        HashMap<String,Integer> map = new HashMap<>();
        PreparedStatement st;
        ResultSet rs;
        
        try {
            
            st = DbConnection.getConnection().prepareStatement("SELECT * FROM `products` WHERE `Category` = ? AND `ProductName` LIKE ? AND `UserId` != ?  ");
            st.setString(1, CatTerm);
            st.setString(2, search);
            st.setInt(3, curUser);
            rs = st.executeQuery(); 
            OwnProd own;
            
            while (rs.next()){
                
                own = new OwnProd(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getDouble(4),rs.getInt(5),rs.getString(6),rs.getString(7));
                map .put(own.getName() , own.getId());
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;

}
    
    public OwnProd getOwnProdById(int id){
    
        PreparedStatement st;
        ResultSet rs;
        OwnProd own = new OwnProd();
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT * FROM `products` WHERE `ProductId` = ?");
            st.setInt(1,id);
            rs = st.executeQuery();
            
            
            if(rs.next()){
                own.SetUser(rs.getInt(2));
                own.setId(id);
                own.setName(rs.getString(3));
                own.setPrice(rs.getDouble(4));
                own.setCount(rs.getInt(5));
                own.SetSales(rs.getInt(6));
                own.setDesc(rs.getString(7));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return own;
    }
    
    public OwnProd getProdById2(int id){
    
        PreparedStatement st;
        ResultSet rs;
        OwnProd own = new OwnProd();
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT * FROM `products` WHERE `ProductId` = ? ORDER BY `productSales` DESC LIMIT 3");
            st.setInt(1,id);
            rs = st.executeQuery();
            
            
            if(rs.next()){
                own.SetUser(rs.getInt(2));
                own.setId(id);
                own.setName(rs.getString(3));
                own.setPrice(rs.getDouble(4));
                own.setCount(rs.getInt(5));
                own.SetSales(rs.getInt(6));
                own.setDesc(rs.getString(7));
                own.SetCat(rs.getString(8));
   
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return own;
    }
       public OwnProd getProdById(int id){
    
        PreparedStatement st;
        ResultSet rs;
        OwnProd own = new OwnProd();
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT * FROM `car` WHERE `ProductId` = ?");
            st.setInt(1,id);
            rs = st.executeQuery();
            
            
            if(rs.next()){
                rs.getInt(1);
                rs.getInt(2);
                own.SetUser(rs.getInt(3));
                own.setId(id);
                own.setName(rs.getString(5));
                own.setPrice(rs.getDouble(6));
                own.setCount(rs.getInt(7));
                own.setDesc(rs.getString(8));
                own.SetSales(rs.getInt(9));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return own;
    }

       
        public OwnProd getOwnProdByIdFav(int id){
    
        PreparedStatement st;
        ResultSet rs;
        OwnProd own = new OwnProd();
        try {
            
            st = DbConnection.getConnection().prepareStatement("SELECT p.ProductId,p.UserId,p.ProductName,p.ProductPrice,p.ProductCount,p.productSales,"
                    + "p.Description,p.Category FROM products AS p  LEFT JOIN favourite AS c1 ON "
                    + "p.ProductId=c1.ProductId WHERE c1.UserId =? AND c1.ProductId = ?");
            st.setInt(1,curUser);
            st.setInt(2,id);
            rs = st.executeQuery();
            
            
            if(rs.next()){
               System.out.println("here");
                own.SetUser(rs.getInt(2));
                own.setId(id);
                own.setName(rs.getString(3));
                own.setPrice(rs.getDouble(4));
                own.setCount(rs.getInt(5));
                own.SetSales(rs.getInt(6));
                own.setDesc(rs.getString(7));
                
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return own;
    }
       
        public OwnProd getOwnProdByIdHist1(int id){
            
        PreparedStatement st;
        ResultSet rs;
        OwnProd own = new OwnProd();
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT * FROM `history` WHERE `UserId` = ? AND `ProductId` = ?" );
            st.setInt(1,curUser);
            st.setInt(2,id);
            rs = st.executeQuery();

            
            if(rs.next()){
                own.SetUser(rs.getInt(2));
                
                own.setFromUser(rs.getInt(3));
                own.setId(rs.getInt(4));
                own.setCount(rs.getInt(5));
                
                own.setPrice(rs.getDouble(6));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return own;
    }
         public OwnProd getOwnProdByIdHist2(int id){
            
        PreparedStatement st;
        ResultSet rs;
        OwnProd own = new OwnProd();
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT * FROM `history` WHERE `FromUser` = ?");
            st.setInt(1,curUser);
            rs = st.executeQuery();

            
            if(rs.next()){
                own.SetUser(rs.getInt(2));
                
                own.setFromUser(rs.getInt(3));
                own.setId(rs.getInt(4));
                own.setCount(rs.getInt(5));
                
                own.setPrice(rs.getDouble(6));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return own;
    }
    
     public HashMap<String,Integer>getTypesMapCart(){
        String query = " SELECT `UserId` FROM  `curuser` WHERE 1";
            PreparedStatement st2;
            try {
                st2 = DbConnection.getConnection().prepareStatement(query);
                ResultSet rs = st2.executeQuery(); 
                if(rs.next()){
                    curUser = rs.getInt("UserId");         
                }
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        HashMap<String,Integer> map = new HashMap<>();
        PreparedStatement st;
        ResultSet rs;
        
        try {
            st = DbConnection.getConnection().prepareStatement("SELECT * FROM `car` WHERE `CurUser` = ?");
            st.setInt(1, curUser); 
            rs = st.executeQuery(); 
            
            OwnProd own;
            
            while (rs.next()){
                
                own = new OwnProd(rs.getInt(2),rs.getInt(3),rs.getInt(4),
                        rs.getString(5),rs.getDouble(6),rs.getInt(7),rs.getString(8),rs.getInt(9));
                map .put(own.getName() , own.getId());
                   
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(OwnProd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return map;

}
     
     
}
