package com.somosicev.locadora.model;

import java.math.BigDecimal;
import java.util.Date;

public class Devolucao {

    private Date dataDevolucao;
    private BigDecimal valorLocacao;
    private Integer quantidadeDiarias;
    
    public Devolucao() {
    }

    public Devolucao(Date dataDevolucao, BigDecimal valorLocacao, Integer quantidadeDiarias) {
        this.dataDevolucao = dataDevolucao;
        this.valorLocacao = valorLocacao;
        this.quantidadeDiarias = quantidadeDiarias;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public BigDecimal getValorLocacao() {
        return valorLocacao;
    }

    public void setValorLocacao(BigDecimal valorLocacao) {
        this.valorLocacao = valorLocacao;
    }

    public Integer getQuantidadeDiarias() {
        return quantidadeDiarias;
    }

    public void setQuantidadeDiarias(Integer quantidadeDiarias) {
        this.quantidadeDiarias = quantidadeDiarias;
    }

    @Override
    public String toString() {
        return "Devolucao [dataDevolucao=" + dataDevolucao + ", quantidadeDiarias=" + quantidadeDiarias
                + ", valorLocacao=" + valorLocacao + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataDevolucao == null) ? 0 : dataDevolucao.hashCode());
        result = prime * result + ((quantidadeDiarias == null) ? 0 : quantidadeDiarias.hashCode());
        result = prime * result + ((valorLocacao == null) ? 0 : valorLocacao.hashCode());
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
        Devolucao other = (Devolucao) obj;
        if (dataDevolucao == null) {
            if (other.dataDevolucao != null)
                return false;
        } else if (!dataDevolucao.equals(other.dataDevolucao))
            return false;
        if (quantidadeDiarias == null) {
            if (other.quantidadeDiarias != null)
                return false;
        } else if (!quantidadeDiarias.equals(other.quantidadeDiarias))
            return false;
        if (valorLocacao == null) {
            if (other.valorLocacao != null)
                return false;
        } else if (!valorLocacao.equals(other.valorLocacao))
            return false;
        return true;
    }
    
}
