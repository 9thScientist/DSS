/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Interface.SplitExpenseUI;
import data.Connect;
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        
        Connection con = Connect.connect();
        
            SplitExpenseUI frame = new SplitExpenseUI();
            frame.pack();
            frame.setVisible(true);
            
        Connect.close(con);
        }
    
    
}
