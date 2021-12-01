package com.somosicev.locadora.model;

public class PessoaFisica extends Cliente {
    
    private String cpf;
    private String identidade;
    private String orgao;
    private String cnh;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdentidade() {
        return identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

    public String getOrgao() {
        return orgao;
    }

    public void setOrgao(String orgao) {
        this.orgao = orgao;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    @Override
    public String toString() {
        return "PessoaFisica [cnh=" + cnh + ", cpf=" + cpf + ", identidade=" + identidade + ", orgao=" + orgao + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cnh == null) ? 0 : cnh.hashCode());
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((identidade == null) ? 0 : identidade.hashCode());
        result = prime * result + ((orgao == null) ? 0 : orgao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PessoaFisica other = (PessoaFisica) obj;
        if (cnh == null) {
            if (other.cnh != null)
                return false;
        } else if (!cnh.equals(other.cnh))
            return false;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (identidade == null) {
            if (other.identidade != null)
                return false;
        } else if (!identidade.equals(other.identidade))
            return false;
        if (orgao == null) {
            if (other.orgao != null)
                return false;
        } else if (!orgao.equals(other.orgao))
            return false;
        return true;
    }

}
