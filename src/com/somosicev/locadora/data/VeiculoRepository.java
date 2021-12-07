package com.somosicev.locadora.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.somosicev.locadora.model.Veiculo;

public class VeiculoRepository {

    private Connection conexao;

    public VeiculoRepository(Connection conexao) {
        this.conexao = conexao;
    }

    public boolean add(Veiculo veiculo) {
        boolean resultado = false;
        try {
            PreparedStatement pstm = conexao.prepareStatement("INSERT INTO VEICULO (modelo, placa, renavam, quilometragem, valorDiaria, disponivel) VALUES (?, ?, ?, ?, ?, ?)");
            pstm.setString(1, veiculo.getModelo());
            pstm.setString(2, veiculo.getPlaca());
            pstm.setString(3, veiculo.getRenavam());
            pstm.setLong(4, veiculo.getQuilometragem());
            pstm.setBigDecimal(5, veiculo.getValorDiaria());
            pstm.setInt(6, (veiculo.isDisponivel() ? 1 : 0) );
            if (pstm.executeUpdate() > 0) {
                resultado = true;
            }
            pstm.close();
        }
        catch (SQLException ex) {
            System.out.println("Erro na inclusão do veículo no banco de dados.");
        }
        return resultado;
    }

    public boolean update(Veiculo veiculo) {
        boolean resultado = false;
        try {
            PreparedStatement pstm = conexao.prepareStatement("UPDATE VEICULO SET modelo = ?, placa = ?, renavam = ?, quilometragem = ?, valorDiaria = ?, disponivel = ? WHERE idVeiculo = ?");
            pstm.setString(1, veiculo.getModelo());
            pstm.setString(2, veiculo.getPlaca());
            pstm.setString(3, veiculo.getRenavam());
            pstm.setLong(4, veiculo.getQuilometragem());
            pstm.setBigDecimal(5, veiculo.getValorDiaria());
            pstm.setInt(6, (veiculo.isDisponivel() ? 1 : 0) );
            pstm.setLong(7, veiculo.getId());
            if (pstm.executeUpdate() > 0) {
                resultado = true;
            }
            pstm.close();
        }
        catch (SQLException ex) {
            System.out.println("Erro na atualização do veículo no banco de dados.");
        }
        return resultado;
    }

    public boolean remove(Veiculo veiculo) {
        boolean resultado = false;
        try {
            PreparedStatement pstm = conexao.prepareStatement("DELETE FROM VEICULO WHERE idVeiculo = ?");
            pstm.setLong(1, veiculo.getId());
            if (pstm.executeUpdate() > 0) {
                resultado = true;
            }
            pstm.close();
        }
        catch (SQLException ex) {
            System.out.println("Erro na remoção do veículo no banco de dados.");
        }
        return resultado;
    }

    public List<Veiculo> findAll() {
        List<Veiculo> lista = new ArrayList<>();
        try {
            PreparedStatement pstm = conexao.prepareStatement("SELECT * FROM VEICULO order by idVeiculo");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getLong("idVeiculo"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setRenavam(rs.getString("renavam"));
                veiculo.setQuilometragem(rs.getLong("quilometragem"));
                veiculo.setValorDiaria(rs.getBigDecimal("valorDiaria"));
                veiculo.setDisponivel(rs.getInt("disponivel") == 1);
                lista.add(veiculo);
            }
            rs.close();
            pstm.close();
        }
        catch (SQLException ex) {
            System.out.println("Erro na pesquisa de veículos no banco de dados.");
        }
        return lista;
    }

    public Veiculo findById(Long id) {
        Veiculo veiculo = null;
        try {
            PreparedStatement pstm = conexao.prepareStatement("SELECT * FROM VEICULO WHERE idVeiculo = ?");
            pstm.setLong(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setId(rs.getLong("idVeiculo"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setRenavam(rs.getString("renavam"));
                veiculo.setQuilometragem(rs.getLong("quilometragem"));
                veiculo.setValorDiaria(rs.getBigDecimal("valorDiaria"));
                veiculo.setDisponivel(rs.getInt("disponivel") == 1);
            }
            rs.close();
            pstm.close();
        }
        catch (SQLException ex) {
            System.out.println("Erro na pesquisa do veículos por id.");
        }
        return veiculo;
    }
    
    public List<Veiculo> findByDisponibilidade(boolean disponivel) {
        List<Veiculo> lista = new ArrayList<>();
        try {
            PreparedStatement pstm = conexao.prepareStatement("SELECT * FROM VEICULO WHERE disponivel = ? order by idVeiculo");
            pstm.setInt(1, (disponivel ? 1 : 0));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getLong("idVeiculo"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setRenavam(rs.getString("renavam"));
                veiculo.setQuilometragem(rs.getLong("quilometragem"));
                veiculo.setValorDiaria(rs.getBigDecimal("valorDiaria"));
                veiculo.setDisponivel(rs.getInt("disponivel") == 1);
                lista.add(veiculo);
            }
            rs.close();
            pstm.close();
        }
        catch (SQLException ex) {
            System.out.println("Erro na pesquisa de veículos por disponibilidade.");
        }
        return lista;
    }
}
