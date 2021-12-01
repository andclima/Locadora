package com.somosicev.locadora.model;

import java.math.BigDecimal;

public class Veiculo {

    private Long id;
    private String modelo;
    private String placa;
    private String renavam;
    private Long quilometragem;
    private BigDecimal valorDiaria;
    private boolean disponivel;
    
    public Veiculo() {
    }

    public Veiculo(Long id, String modelo, String placa, String renavam, Long quilometragem, BigDecimal valorDiaria,
            boolean disponivel) {
        this.id = id;
        this.modelo = modelo;
        this.placa = placa;
        this.renavam = renavam;
        this.quilometragem = quilometragem;
        this.valorDiaria = valorDiaria;
        this.disponivel = disponivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRenavam() {
        return renavam;
    }
    
    public void setRenavam(String renavam) {
        this.renavam = renavam;
    }
    public Long getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(Long quilometragem) {
        this.quilometragem = quilometragem;
    }

    public BigDecimal getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(BigDecimal valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Veiculo [id=" + id + ", modelo=" + modelo + ", placa=" + placa + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (disponivel ? 1231 : 1237);
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
        result = prime * result + ((placa == null) ? 0 : placa.hashCode());
        result = prime * result + ((quilometragem == null) ? 0 : quilometragem.hashCode());
        result = prime * result + ((renavam == null) ? 0 : renavam.hashCode());
        result = prime * result + ((valorDiaria == null) ? 0 : valorDiaria.hashCode());
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
        Veiculo other = (Veiculo) obj;
        if (disponivel != other.disponivel)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (modelo == null) {
            if (other.modelo != null)
                return false;
        } else if (!modelo.equals(other.modelo))
            return false;
        if (placa == null) {
            if (other.placa != null)
                return false;
        } else if (!placa.equals(other.placa))
            return false;
        if (quilometragem == null) {
            if (other.quilometragem != null)
                return false;
        } else if (!quilometragem.equals(other.quilometragem))
            return false;
        if (renavam == null) {
            if (other.renavam != null)
                return false;
        } else if (!renavam.equals(other.renavam))
            return false;
        if (valorDiaria == null) {
            if (other.valorDiaria != null)
                return false;
        } else if (!valorDiaria.equals(other.valorDiaria))
            return false;
        return true;
    }
    
}
