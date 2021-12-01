package com.somosicev.locadora.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.somosicev.locadora.data.Conexao;

public class AppTeste {

    public static void main(String[] args) {
        Connection conexao = Conexao.getInstance();
        if (conexao != null) {
            try {
                PreparedStatement stm = conexao.prepareStatement("select count(1) from cliente");
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    System.out.println("Existem " + rs.getInt(1) + " clientes cadastrados!");
                }
                rs.close();
                stm.close();
                conexao.close();
            } catch (SQLException ex) {
                System.out.println("Erro na consulta dos dados");
                System.out.println(ex.getMessage());
            }
        }
    }

}
