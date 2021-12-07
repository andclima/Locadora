package com.somosicev.locadora.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            // Inicia uma transação!
            conexao.setAutoCommit(false);

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
            conexao.commit();
            resultado = true;
        }
        catch (SQLException ex) {
            System.out.println("Erro na inclusão do cliente no banco de dados.");
            ex.printStackTrace();
            try {
                conexao.rollback();
            } catch (SQLException exRollback) {
                //
            }
        }
        return resultado;
    }
    
    public boolean update(Cliente cliente) {
        boolean resultado = false;
        try {
            // Inicia uma transação!
            conexao.setAutoCommit(false);
            
            PreparedStatement pstm = conexao.prepareStatement("UPDATE CLIENTE SET nome = ?, email = ?, telefone = ? WHERE idCliente = ?");
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getEmail());
            pstm.setString(3, cliente.getTelefone());
            pstm.setLong(4, cliente.getId());
            pstm.executeUpdate();
            pstm.close();

            if (cliente instanceof PessoaFisica) {
                PessoaFisica novaPessoaFisica = (PessoaFisica) cliente;
                pstm = conexao.prepareStatement("UPDATE PESSOAFISICA SET cpf = ?, identidade = ?, orgao = ?, cnh = ? WHERE idCliente = ?");
                pstm.setString(1, novaPessoaFisica.getCpf());
                pstm.setString(2, novaPessoaFisica.getIdentidade());
                pstm.setString(3, novaPessoaFisica.getOrgao());
                pstm.setString(4, novaPessoaFisica.getCnh());
                pstm.setLong(5, novaPessoaFisica.getId());
                pstm.executeUpdate();
                pstm.close();
            }
            else if (cliente instanceof PessoaJuridica) {
                PessoaJuridica novaPessoaJuridica = (PessoaJuridica) cliente;
                pstm = conexao.prepareStatement("UPDATE PESSOAJURIDICA SET cnpj = ?, inscricaoEstadual = ?, nomeResponsavel = ?, cnhResponsavel = ? WHERE idCliente = ?");
                pstm.setString(1, novaPessoaJuridica.getCnpj());
                pstm.setString(2, novaPessoaJuridica.getInscricaoEstadual());
                pstm.setString(3, novaPessoaJuridica.getNomeResponsavel());
                pstm.setString(4, novaPessoaJuridica.getCnhResponsavel());
                pstm.setLong(5, novaPessoaJuridica.getId());
                pstm.executeUpdate();
                pstm.close();
            } 
            conexao.commit();
            resultado = true;
        }
        catch (SQLException ex) {
            System.out.println("Erro na atualização do cliente no banco de dados.");
            ex.printStackTrace();
            try {
                conexao.rollback();
            } catch (SQLException exRollback) {
                //
            }
        }
        return resultado;
    }

    public boolean remove(Cliente cliente) {
        boolean resultado = false;
        try {
            // Inicia uma transação!
            PreparedStatement pstm;
            conexao.setAutoCommit(false);
            if (cliente instanceof PessoaFisica) {
                pstm = conexao.prepareStatement("DELETE FROM PESSOAFISICA WHERE idCliente = ?");
                pstm.setLong(1, cliente.getId());
                pstm.executeUpdate();
                pstm.close();
            }
            else if (cliente instanceof PessoaJuridica) {
                pstm = conexao.prepareStatement("DELETE FROM PESSOAJURIDICA WHERE idCliente = ?");
                pstm.setLong(1, cliente.getId());
                pstm.executeUpdate();
                pstm.close();
            } 

            pstm = conexao.prepareStatement("DELETE FROM CLIENTE WHERE idCliente = ?");
            pstm.setLong(1, cliente.getId());
            pstm.executeUpdate();
            pstm.close();

            conexao.commit();
            resultado = true;
        }
        catch (SQLException ex) {
            System.out.println("Erro na remoção do cliente no banco de dados.");
            ex.printStackTrace();
            try {
                conexao.rollback();
            } catch (SQLException exRollback) {
                //
            }
        }
        return resultado;
    }

    public List<Cliente> findAll() {
        List<Cliente> lista = new ArrayList<>();
        try {
            PreparedStatement pstm = conexao.prepareStatement("SELECT c.*, cpf, identidade, orgao, cnh, cnpj, inscricaoestadual, nomeresponsavel, cnhresponsavel " +
                                            " FROM CLIENTE c left join PESSOAFISICA pf ON c.idCliente = pf.idCliente " +
                                            " left join PESSOAJURIDICA pj ON c.idCliente = pj.idCliente "+
                                            " ORDER BY c.idCliente");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                if (rs.getInt("tipo") == 1) {
                    PessoaFisica novaPessoaFisica = new PessoaFisica();
                    novaPessoaFisica.setNome(rs.getString("nome"));
                    novaPessoaFisica.setCnh(rs.getString("cnh"));
                    novaPessoaFisica.setCpf(rs.getString("cpf"));
                    novaPessoaFisica.setEmail(rs.getString("email"));
                    novaPessoaFisica.setIdentidade(rs.getString("identidade"));
                    novaPessoaFisica.setOrgao(rs.getString("orgao"));
                    novaPessoaFisica.setTelefone(rs.getString("telefone"));
                    lista.add(novaPessoaFisica);
                } else {
                    PessoaJuridica novaPessoaJuridica = new PessoaJuridica();
                    novaPessoaJuridica.setNome(rs.getString("nome"));
                    novaPessoaJuridica.setEmail(rs.getString("email"));
                    novaPessoaJuridica.setTelefone(rs.getString("telefone"));
                    novaPessoaJuridica.setCnpj(rs.getString("cnpj"));
                    novaPessoaJuridica.setInscricaoEstadual(rs.getString("inscricaoestadual"));
                    novaPessoaJuridica.setNomeResponsavel(rs.getString("nomeresponsavel"));
                    novaPessoaJuridica.setCnhResponsavel(rs.getString("cnhresponsavel"));
                    lista.add(novaPessoaJuridica);
                }
            }
            rs.close();
            pstm.close();
        }
        catch (SQLException ex) {
            System.out.println("Erro na pesquisa de clientes no banco de dados.");
        }
        return lista;
    }

    public Cliente findById(Long id) {
        Cliente cliente = null;
        try {
            PreparedStatement pstm = conexao.prepareStatement("SELECT c.*, cpf, identidade, orgao, cnh, cnpj, inscricaoestadual, nomeresponsavel, cnhresponsavel " +
                                            " FROM CLIENTE c left join PESSOAFISICA pf ON c.idCliente = pf.idCliente " +
                                            " left join PESSOAJURIDICA pj ON c.idCliente = pj.idCliente " +
                                            " WHERE c.idCliente = ?");
            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                if (rs.getInt("tipo") == 1) {
                    PessoaFisica novaPessoaFisica = new PessoaFisica();
                    novaPessoaFisica.setNome(rs.getString("nome"));
                    novaPessoaFisica.setCnh(rs.getString("cnh"));
                    novaPessoaFisica.setCpf(rs.getString("cpf"));
                    novaPessoaFisica.setEmail(rs.getString("email"));
                    novaPessoaFisica.setIdentidade(rs.getString("identidade"));
                    novaPessoaFisica.setOrgao(rs.getString("orgao"));
                    novaPessoaFisica.setTelefone(rs.getString("telefone"));
                    cliente = novaPessoaFisica;
                } else {
                    PessoaJuridica novaPessoaJuridica = new PessoaJuridica();
                    novaPessoaJuridica.setNome(rs.getString("nome"));
                    novaPessoaJuridica.setEmail(rs.getString("email"));
                    novaPessoaJuridica.setTelefone(rs.getString("telefone"));
                    novaPessoaJuridica.setCnpj(rs.getString("cnpj"));
                    novaPessoaJuridica.setInscricaoEstadual(rs.getString("inscricaoestadual"));
                    novaPessoaJuridica.setNomeResponsavel(rs.getString("nomeresponsavel"));
                    novaPessoaJuridica.setCnhResponsavel(rs.getString("cnhresponsavel"));
                    cliente = novaPessoaJuridica;
                }
            }
            rs.close();
            pstm.close();
        }
        catch (SQLException ex) {
            System.out.println("Erro na pesquisa de clientes por identificador.");
        }
        return cliente;    
    }

}
