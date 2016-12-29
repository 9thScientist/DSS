/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Interface.SplitExpenseUI;
import Moradores.Apartamento;
import data.ApartamentoDAO;
import data.Connect;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author andre
 */
public class Main {
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException{
        
            Connection con= Connect.connect();
            ApartamentoDAO a = new ApartamentoDAO();
            
            Apartamento apa = new Apartamento(2,10);
            
            a.put(apa);

            //SplitExpenseUI frame = new SplitExpenseUI();
            //frame.pack();
            //frame.setVisible(true);
            
            Connect.close(con);
        }
    
    
}
