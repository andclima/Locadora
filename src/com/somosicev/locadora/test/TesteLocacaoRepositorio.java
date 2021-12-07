package com.somosicev.locadora.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import com.somosicev.locadora.data.ClienteRepository;
import com.somosicev.locadora.data.Conexao;
import com.somosicev.locadora.data.LocacaoRepository;
import com.somosicev.locadora.data.VeiculoRepository;
import com.somosicev.locadora.model.Cliente;
import com.somosicev.locadora.model.Devolucao;
import com.somosicev.locadora.model.Locacao;
import com.somosicev.locadora.model.SituacaoLocacao;
import com.somosicev.locadora.model.Veiculo;

public class TesteLocacaoRepositorio {

    public static void main(String[] args) {

        Connection conn = Conexao.getInstance();
        
        try {
            LocacaoRepository repo = new LocacaoRepository(conn);
            ClienteRepository repoCliente = new ClienteRepository(conn);
            VeiculoRepository repoVeiculo = new VeiculoRepository(conn);

            Cliente cliente = repoCliente.findById(8l);
            Veiculo veiculo = repoVeiculo.findById(3l);
            Locacao locacao = new Locacao(null, Calendar.getInstance().getTime(), veiculo, cliente, veiculo.getValorDiaria(), 10, SituacaoLocacao.ATIVA);
            Locacao gravada = repo.add(locacao);
            System.out.println(gravada);

            locacao = gravada;
            Devolucao devolucao = new Devolucao(Calendar.getInstance().getTime(), new BigDecimal("1200.68"), 15);
            locacao.setDevolucao(devolucao);
            repo.update(locacao);
            gravada = repo.findById(locacao.getId());
            System.out.println(gravada);

            locacao = gravada;
            locacao.setQuantidadeDiarias(20);
            locacao.setDevolucao(null);
            repo.update(locacao);
            gravada = repo.findById(locacao.getId());
            System.out.println(gravada);
            repo.remove(gravada);

            conn.close();
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
