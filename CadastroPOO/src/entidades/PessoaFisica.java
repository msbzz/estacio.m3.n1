package entidades;

import java.io.Serializable;

public class PessoaFisica extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

     private Integer idade;
    private String cpf;
    public PessoaFisica() {
     }
    public PessoaFisica(Integer idade, String cpf) {
        this.idade = idade;
        this.cpf = cpf;
    }

    public PessoaFisica( String nome, Integer idade, String cpf) {
        super(  nome);
        this.idade = idade;
        this.cpf = cpf;

    }
    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String exibir() {
        return
                "id : " + getId() + "\n" +
                "nome : " + getNome()  + "\n" +
                "idade : " + idade  + "\n" +
                "cpf : '" + cpf  + "\n" ;
    }

}
