package com.somosicev.locadora.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private final static String DATABASE_URL = "jdbc:postgresql://castor.db.elephantsql.com/rcpvgoxy";
    private final static String DATABASE_USER = "rcpvgoxy";
    private final static String DATABASE_PASSWORD = "DQDGuDpsCdH77KEHN5SgwGbgsgtuLXUA";

    private static Connection conexao;

    public static Connection getInstance() {
        if (conexao == null) {
            try {
                conexao = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            }
            catch (SQLException ex) {
                System.out.println("Erro na conexão do banco de dados!");
            }
        }
        return conexao;
    }
    
}
