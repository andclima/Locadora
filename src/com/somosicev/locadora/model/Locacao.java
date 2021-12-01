package com.somosicev.locadora.model;

import java.math.BigDecimal;
import java.util.Date;

public class Locacao {
    
    private Long id;
    private Date dataLocacao;
    private Veiculo veiculo;
    private Cliente cliente;
    private BigDecimal valorDiaria;
    private Integer quantidadeDiarias;
    private SituacaoLocacao situacao;
    private Devolucao devolucao;

    public Locacao() {

    }

    public Locacao(Long id, Date dataLocacao, Veiculo veiculo, Cliente cliente, BigDecimal valorDiaria,
            Integer quantidadeDiarias, SituacaoLocacao situacao) {
        this.id = id;
        this.dataLocacao = dataLocacao;
        this.veiculo = veiculo;
        this.cliente = cliente;
        this.valorDiaria = valorDiaria;
        this.quantidadeDiarias = quantidadeDiarias;
        this.situacao = situacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Integer getQuantidadeDiarias() {
        return quantidadeDiarias;
    }

    public void setQuantidadeDiarias(Integer quantidadeDiarias) {
        this.quantidadeDiarias = quantidadeDiarias;
    }

    public SituacaoLocacao getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoLocacao situacao) {
        this.situacao = situacao;
    }

    public Devolucao getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(Devolucao devolucao) {
        this.devolucao = devolucao;
    }

    @Override
    public String toString() {
        return "Locacao [cliente=" + cliente + ", id=" + id + ", veiculo=" + veiculo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
        result = prime * result + ((dataLocacao == null) ? 0 : dataLocacao.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((quantidadeDiarias == null) ? 0 : quantidadeDiarias.hashCode());
        result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
        result = prime * result + ((valorDiaria == null) ? 0 : valorDiaria.hashCode());
        result = prime * result + ((veiculo == null) ? 0 : veiculo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Locacao other = (Locacao) obj;
        if (cliente == null) {
            if (other.cliente != null)
                return false;
        } else if (!cliente.equals(other.cliente))
            return false;
        if (dataLocacao == null) {
            if (other.dataLocacao != null)
                return false;
        } else if (!dataLocacao.equals(other.dataLocacao))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (quantidadeDiarias == null) {
            if (other.quantidadeDiarias != null)
                return false;
        } else if (!quantidadeDiarias.equals(other.quantidadeDiarias))
            return false;
        if (situacao != other.situacao)
            return false;
        if (valorDiaria == null) {
            if (other.valorDiaria != null)
                return false;
        } else if (!valorDiaria.equals(other.valorDiaria))
            return false;
        if (veiculo == null) {
            if (other.veiculo != null)
                return false;
        } else if (!veiculo.equals(other.veiculo))
            return false;
        return true;
    }
}
