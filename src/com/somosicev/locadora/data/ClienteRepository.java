package com.somosicev.locadora.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.somosicev.locadora.model.Cliente;
import com.somosicev.locadora.model.PessoaFisica;
import com.somosicev.locadora.model.PessoaJuridica;

public class ClienteRepository {

    private Connection conexao;

    public ClienteRepository(Connection conexao) {
        this.conexao = conexao;
    }

    public boolean add(Cliente cliente) {
        boolean resultado = false;
        try {
            Long idNovo = -1l;
            PreparedStatement pstm = conexao.prepareStatement("INSERT INTO CLIENTE (nome, email, telefone, tipo) VALUES (?, ?, ?, ?) RETURNING idCliente");
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getEmail());
            pstm.setString(3, cliente.getTelefone());
            if (cliente instanceof PessoaFisica) {
                pstm.setInt(4, 1);
            } else {
                pstm.setInt(4, 2);
            }
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                idNovo = rs.getLong(1);
            }
            rs.close();
            pstm.close();

            if (cliente instanceof PessoaFisica) {
                PessoaFisica novaPessoaFisica = (PessoaFisica) cliente;
                pstm = conexao.prepareStatement("INSERT INTO PESSOAFISICA (idCliente, cpf, identidade, orgao, cnh) VALUES (?, ?, ?, ?, ?)");
                pstm.setLong(1, idNovo);
                pstm.setString(2, novaPessoaFisica.getCpf());
                pstm.setString(3, novaPessoaFisica.getIdentidade());
                pstm.setString(4, novaPessoaFisica.getOrgao());
                pstm.setString(5, novaPessoaFisica.getCnh());
                pstm.executeUpdate();
                pstm.close();
            }
            else if (cliente instanceof PessoaJuridica) {
                PessoaJuridica novaPessoaJuridica = (PessoaJuridica) cliente;
                pstm = conexao.prepareStatement("INSERT INTO PESSOAJURIDICA (idCliente, cnpj, inscricaoEstadual, nomeResponsavel, cnhResponsavel) VALUES (?, ?, ?, ?, ?)");
                pstm.setLong(1, idNovo);
                pstm.setString(2, novaPessoaJuridica.getCnpj());
                pstm.setString(3, novaPessoaJuridica.getInscricaoEstadual());
                pstm.setString(4, novaPessoaJuridica.getNomeResponsavel());
                pstm.setString(5, novaPessoaJuridica.getCnhResponsavel());
                pstm.executeUpdate();
                pstm.close();
            } 
        }
        catch (SQLException ex) {
            System.out.println("Erro na inclusão do cliente no banco de dados.");
            ex.printStackTrace();
        }
        return resultado;
    }
    
    public boolean update(Cliente cliente) {
        return false;
    }

    public boolean remove(Cliente cliente) {
        return false;
    }

    public List<Cliente> findAll() {
        return null;
    }

    public Cliente findById(Long id) {
        return null;
    }

}
