/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author pc
 */
public class Test {
    public static void save(Site s){
        //information d'acces a la base de donnees
        String user="root";
        String password="";
        String url ="jdbc:mysql://localhost/db";
        Connection cn=null;
        Statement st =null;
        try{
            //Etape1:chargement du driver 
            Class.forName("com.mysql.jdbc.Driver");
            //Etape2:recupération de la connexion
            cn=DriverManager.getConnection(url,user,password);
            //Etape3:création d'un statement
            st=cn.createStatement();
            String req = "insert into site values(null,'" + s.getNom() + "')";
            //Etape4:
            st.executeUpdate(req);
        }catch(SQLException e){
            System.out.println("Erreur SQL");    
        }catch(ClassNotFoundException ex){
            System.out.println("Impossible de charger le drive");
        }finally{
            try{
                //Etape5:
                st.close();
                cn.close();
            }catch(SQLException ex){
                System.out.println("Impossible de liberer les ressources");
            }
        }
        
    }
    
    public static void load(){
        //informtion d'acces a la base de donnees
        String user ="root";
        String password="";
        String url ="jdbc:mysql://localhost/db";
        Connection cn= null;
        Statement st = null;
        ResultSet rs = null;
        try{
            //Etape1:chargement du driver 
            Class.forName("com.mysql.jdbc.Driver");
            //Etape2:recupération de la connexion
            cn=DriverManager.getConnection(url,user,password);
            //Etape3:création d'un statement
            st=cn.createStatement();
            String req = "select * from site";
            //Etape4
            rs=st.executeQuery(req);
            //Etape5
            while (rs.next()){
                System.out.println(rs.getInt(1)+ " "+rs.getString(2));
            }
        
    }catch(SQLException e) {
        System.out.println(e.getMessage());
        
    }catch(ClassNotFoundException ex){
        System.out.println("Impossible de charger le driver");
    }finally{
            try{
                //Etape6
                st.close();
                cn.close();
            }catch(SQLException ex){
              System.out.println("Impossible de liberer les ressources");   
            }
        }
    
}
     public static void main (String[] args){
        //insertion des donnees
        save(new Site("SAFI"));
        save(new Site("MARRAKECH"));
        save(new Site("ELJADIDA"));
        load();
    }
}
