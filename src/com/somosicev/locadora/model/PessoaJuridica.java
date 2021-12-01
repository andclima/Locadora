package com.somosicev.locadora.model;

public class PessoaJuridica extends Cliente {

    private String cnpj;
    private String inscricaoEstadual;
    private String nomeResponsavel;
    private String cnhResponsavel;

    public PessoaJuridica() {
        
    }
    
    @Override
    public String toString() {
        return "PessoaJuridica [cnhResponsavel=" + cnhResponsavel + ", cnpj=" + cnpj + ", inscricaoEstadual="
                + inscricaoEstadual + ", nomeResponsavel=" + nomeResponsavel + "]";
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getCnhResponsavel() {
        return cnhResponsavel;
    }

    public void setCnhResponsavel(String cnhResponsavel) {
        this.cnhResponsavel = cnhResponsavel;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((cnhResponsavel == null) ? 0 : cnhResponsavel.hashCode());
        result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
        result = prime * result + ((inscricaoEstadual == null) ? 0 : inscricaoEstadual.hashCode());
        result = prime * result + ((nomeResponsavel == null) ? 0 : nomeResponsavel.hashCode());
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
        PessoaJuridica other = (PessoaJuridica) obj;
        if (cnhResponsavel == null) {
            if (other.cnhResponsavel != null)
                return false;
        } else if (!cnhResponsavel.equals(other.cnhResponsavel))
            return false;
        if (cnpj == null) {
            if (other.cnpj != null)
                return false;
        } else if (!cnpj.equals(other.cnpj))
            return false;
        if (inscricaoEstadual == null) {
            if (other.inscricaoEstadual != null)
                return false;
        } else if (!inscricaoEstadual.equals(other.inscricaoEstadual))
            return false;
        if (nomeResponsavel == null) {
            if (other.nomeResponsavel != null)
                return false;
        } else if (!nomeResponsavel.equals(other.nomeResponsavel))
            return false;
        return true;
    }

}
