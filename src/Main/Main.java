/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import java.sql.*;
import data.Connect;

/**
 *
 * @author andre
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
        public static void main(String[] args) throws Exception {
            
            
            //exemplo de utilização de base de dados
            //ligar a base de dados com o user dss e a pass dsspw
            // na porta 3306 em localhost
            
            //liga o programa a base de dados
            Connect dao = new Connect();
            Connection c = dao.connect();
            
            //fechar conecção a base de dados
            dao.close(c);
        }
    
}
