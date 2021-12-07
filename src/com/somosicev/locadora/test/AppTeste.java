package com.somosicev.locadora.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.somosicev.locadora.data.Conexao;
import com.somosicev.locadora.data.VeiculoRepository;
import com.somosicev.locadora.model.Cliente;
import com.somosicev.locadora.model.Veiculo;

public class AppTeste {

    public static void main(String[] args) {
       testeVeiculoRepositorio();
    }

    public static void testeVeiculoRepositorio() {

        Connection conn = Conexao.getInstance();

        try {
            VeiculoRepository repo = new VeiculoRepository(conn);

            System.out.println("Veículos cadastrados:");
            System.out.println("-------------------------------------");
            for (Veiculo registro:repo.findAll()) {
                System.out.println(registro);
            }
            System.out.println("");

            // Veiculo novo = new Veiculo(null, "Mobile", "HBO2134", "123456", 100L, new BigDecimal("95.50"), false);
            // repo.add(novo);

            Veiculo veiculoEncontrado = repo.findById(1l);
            if (veiculoEncontrado != null) {
                veiculoEncontrado.setRenavam("999888777");
                repo.update(veiculoEncontrado);
                System.out.println("Veículo atualizado com sucesso!");
            }

            veiculoEncontrado = repo.findById(2l);
            if (veiculoEncontrado != null) {
                if (repo.remove(veiculoEncontrado)) {
                    System.out.println("Veículo removido com sucesso!");
                } else {
                    System.out.println("Veículo não removido!");
                }
            } else {
                System.out.println("Veículo não encontrado!");
            }
            
            System.out.println("Veículos cadastrados:");
            System.out.println("-------------------------------------");
            for (Veiculo registro:repo.findAll()) {
                System.out.println(registro);
            }
            System.out.println("");

            System.out.println("Veículos Disponíveis");
            System.out.println("-------------------------------------");
            for (Veiculo registro:repo.findByDisponibilidade(true)) {
                System.out.println(registro);
            }
            System.out.println("");

            conn.close();
    
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public static void testeLista() {
        List<Cliente> lista = new ArrayList<>();
        lista.add(new Cliente(1l, "Fulano", "fulano@gmail.com", "(86) 1234-5678"));
        lista.add(new Cliente(2l, "Beltrano", "beltrano@gmail.com", "(86) 4321-5678"));
        lista.add(new Cliente(3l, "Sicrano", "sicrano@gmail.com", "(86) 1234-8765"));

        Cliente novo = new Cliente(4l, "Beltrano", "beltrano@gmail.com", "(86) 4321-5678");
        if (lista.contains(novo)) {
            System.out.println("Este cliente já existe na lista!");
        } else {
            lista.add(novo);
            System.out.println("Cliente adicionado com sucesso!");
        }

        System.out.println("Lista de Clientes: ");
        for (Cliente registro:lista) {
            System.out.println(registro + " -> hashcode: " + registro.hashCode());
        }
    }

    public static void testeConexao() {
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
