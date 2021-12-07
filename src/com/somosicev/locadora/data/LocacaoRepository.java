package com.somosicev.locadora.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.somosicev.locadora.model.Devolucao;
import com.somosicev.locadora.model.Locacao;

public class LocacaoRepository {

    private Connection conexao;

    public LocacaoRepository(Connection conexao) {
        this.conexao = conexao;
    }

    public Locacao add(Locacao locacao) {
        Locacao resultado = null;
        try {
            conexao.setAutoCommit(false);

            Long idNovo = -1l;
            PreparedStatement pstm = conexao.prepareStatement("INSERT INTO LOCACAO (idCliente, idVeiculo, dataLocacao, valorDiaria, quantidadeDiarias) VALUES (?, ?, ?, ?, ?) RETURNING idLocacao");
            pstm.setLong(1, locacao.getCliente().getId());
            pstm.setLong(2, locacao.getVeiculo().getId());
            pstm.setDate(3, new java.sql.Date(locacao.getDataLocacao().getTime()));
            pstm.setBigDecimal(4, locacao.getValorDiaria());
            pstm.setInt(5, locacao.getQuantidadeDiarias());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                idNovo = rs.getLong(1);
            }
            rs.close();
            pstm.close();

            if (locacao.getDevolucao() != null) {
                pstm = conexao.prepareStatement("INSERT INTO DEVOLUCAO (idLocacao, datadevolucao, valorlocacao, quantidadediarias) VALUES (?, ?, ?, ?)");
                pstm.setLong(1, idNovo);
                pstm.setDate(2, new java.sql.Date(locacao.getDevolucao().getDataDevolucao().getTime()));
                pstm.setBigDecimal(3, locacao.getDevolucao().getValorLocacao());
                pstm.setInt(4, locacao.getDevolucao().getQuantidadeDiarias());
                pstm.executeUpdate();
                pstm.close();
            }
            conexao.commit();
            resultado = findById(idNovo);
        }
        catch (SQLException ex) {
            System.out.println("Erro na inclusão da locação no banco de dados.");
            ex.printStackTrace();
            try {
                conexao.rollback();
            } catch (SQLException exRollback) {
                //
            }
        }
        return resultado;
    }
    
    public boolean update(Locacao locacao) {
        boolean resultado = false;
        try {
            conexao.setAutoCommit(false);
            PreparedStatement pstm = conexao.prepareStatement("UPDATE Locacao SET idCliente = ?, idVeiculo = ?, dataLocacao = ?, valorDiaria = ?, quantidadeDiarias = ? WHERE idLocacao = ?");
            pstm.setLong(1, locacao.getCliente().getId());
            pstm.setLong(2, locacao.getVeiculo().getId());
            pstm.setDate(3, new java.sql.Date(locacao.getDataLocacao().getTime()));
            pstm.setBigDecimal(4, locacao.getValorDiaria());
            pstm.setInt(5, locacao.getQuantidadeDiarias());
            pstm.setLong(6, locacao.getId());
            pstm.executeUpdate();
            pstm.close();

            boolean possuiaDevolucao = false;
            pstm = conexao.prepareStatement("SELECT idLocacao FROM Devolucao WHERE idLocacao = ?");
            pstm.setLong(1, locacao.getId());
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                possuiaDevolucao = true;
            }
            rs.close();
            pstm.close();

            if (locacao.getDevolucao() != null) {
                if (!possuiaDevolucao) {
                    pstm = conexao.prepareStatement("INSERT INTO Devolucao (idLocacao, datadevolucao, valorlocacao, quantidadediarias) VALUES (?, ?, ?, ?)");
                    pstm.setLong(1, locacao.getId());
                    if (locacao.getDevolucao().getDataDevolucao() != null) {
                        pstm.setDate(2, new java.sql.Date(locacao.getDevolucao().getDataDevolucao().getTime()));
                    } else {
                        pstm.setDate(2, null);
                    }
                    pstm.setBigDecimal(3, locacao.getDevolucao().getValorLocacao());
                    pstm.setInt(4, locacao.getDevolucao().getQuantidadeDiarias());
                    pstm.executeUpdate();
                    pstm.close();
                } else {
                    pstm = conexao.prepareStatement("UPDATE Devolucao SET datadevolucao = ?, valorlocacao = ?, quantidadediarias = ? WHERE idLocacao = ?");
                    if (locacao.getDevolucao().getDataDevolucao() != null) {
                        pstm.setDate(1, new java.sql.Date(locacao.getDevolucao().getDataDevolucao().getTime()));
                    } else {
                        pstm.setDate(1, null);
                    }
                    pstm.setBigDecimal(2, locacao.getDevolucao().getValorLocacao());
                    pstm.setInt(3, locacao.getDevolucao().getQuantidadeDiarias());
                    pstm.setLong(4, locacao.getId());
                    pstm.executeUpdate();
                    pstm.close();                    
                }
            } else {
                if (possuiaDevolucao) {
                    pstm = conexao.prepareStatement("DELETE FROM Devolucao WHERE idLocacao = ?");
                    pstm.setLong(1, locacao.getId());
                    pstm.executeUpdate();
                    pstm.close();                    
                }
            }
            conexao.commit();
            resultado = true;
        }
        catch (SQLException ex) {
            System.out.println("Erro na atualização da locação no banco de dados.");
            ex.printStackTrace();
            try {
                conexao.rollback();
            } catch (SQLException exRollback) {
                //
            }
        }
        return resultado;
    }

    public boolean remove(Locacao locacao) {
        boolean resultado = false;
        try {
            conexao.setAutoCommit(false);

            PreparedStatement pstm = conexao.prepareStatement("DELETE FROM Devolucao WHERE idLocacao = ?");
            pstm.setLong(1, locacao.getId());
            pstm.executeUpdate();
            pstm.close();                    

            pstm = conexao.prepareStatement("DELETE FROM Locacao WHERE idLocacao = ?");
            pstm.setLong(1, locacao.getId());
            pstm.executeUpdate();
            pstm.close();

            conexao.commit();
            resultado = true;
        }
        catch (SQLException ex) {
            System.out.println("Erro na remoção da locação no banco de dados.");
            ex.printStackTrace();
            try {
                conexao.rollback();
            } catch (SQLException exRollback) {
                //
            }
        }
        return resultado;
    }

    public List<Locacao> findAll() {
        List<Locacao> lista = new ArrayList<>();
        try {
            PreparedStatement pstm = conexao.prepareStatement("SELECT idLocacao FROM Locacao l ORDER BY l.dataLocacao");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                lista.add(findById(rs.getLong("idLocacao")));
            }
            rs.close();
            pstm.close();
        }
        catch (SQLException ex) {
            System.out.println("Erro na pesquisa de locações no banco de dados.");
        }
        return lista;
    }

    public Locacao findById(Long id) {
        VeiculoRepository repoVeiculo = new VeiculoRepository(conexao);
        ClienteRepository repoCliente = new ClienteRepository(conexao);
        Locacao locacao = null;
        try {
            PreparedStatement pstm = conexao.prepareStatement("SELECT l.*, d.idLocacao iddevolucao, datadevolucao, valorlocacao, quantidadediarias quantidadediariasdevolucao " +
                                            "  FROM Locacao l LEFT JOIN Devolucao d ON l.idLocacao = d.idLocacao " +
                                            " WHERE idLocacao = ?");
            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                locacao = new Locacao();
                locacao.setId(rs.getLong("idLocacao"));
                locacao.setCliente(repoCliente.findById(rs.getLong("idCliente")));
                locacao.setVeiculo(repoVeiculo.findById(rs.getLong("idVeiculo")));
                locacao.setDataLocacao(rs.getDate("datalocacao"));
                locacao.setQuantidadeDiarias(rs.getInt("quantidadediarias"));
                locacao.setValorDiaria(rs.getBigDecimal("valordiaria"));

                if (rs.getString("iddevolucao") != null) {
                    Devolucao devolucao = new Devolucao();
                    devolucao.setDataDevolucao(rs.getDate("datadevolucao"));
                    devolucao.setQuantidadeDiarias(rs.getInt("quantidadediariasdevolucao"));
                    devolucao.setValorLocacao(rs.getBigDecimal("valorlocacao"));
                    locacao.setDevolucao(devolucao);
                }
            }
            rs.close();
            pstm.close();
        }
        catch (SQLException ex) {
            System.out.println("Erro na pesquisa de locacao por identificador.");
        }
        return locacao;
    }
}
