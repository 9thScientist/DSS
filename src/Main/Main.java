/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import java.sql.*;
import data.ConnectDAO;
import data.ApartamentoDAO;

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
            ConnectDAO dao = new ConnectDAO();
            
            //criar os objetos com os quais queremos comunicar
            ApartamentoDAO da = new ApartamentoDAO();    
            //operações sobre os objetos
            da.writeDB(2, 10);
            da.readDB();
            da.deleteById(2);
            da.readDB();
                
            //fechar conecção a base de dados
            dao.close();
        }
    
}
