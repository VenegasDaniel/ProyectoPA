
package Logica;

import java.io.File;
import java.util.*;
import Dominio.*;
import java.io.FileNotFoundException;
import FramesVC.*;
import dataBaseStuff.*;
/**
 *
 * @author Cefeg
 */
public class App {
    
    public static DBconnections objetoConection = new DBconnections();
    public static ArrayList<String> l = null;
    public static Usuario currentUser = new Usuario("", "", "", "", 0, "", "", "", "",""); 
    

    public static void main(String[] args) throws FileNotFoundException {
        
        objetoConection.connect();
        Login login = new Login();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        
    }   
}
