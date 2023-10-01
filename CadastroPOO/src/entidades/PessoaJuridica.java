package entidades;

import java.io.Serializable;

import utils.IDControle;

public class PessoaJuridica extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cnpj;

    public PessoaJuridica() {

    }
    public PessoaJuridica(String cnpj) {
        this.cnpj = cnpj;
    }
    public PessoaJuridica(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }
    public PessoaJuridica(Integer id, String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    @Override
    public String exibir() {
        return
                "id : " + getId() + "\n" +
                        "empresa : " + getNome() + "\n" +
                        "cnpj : '" + getCnpj() + "\n";

    }

}
