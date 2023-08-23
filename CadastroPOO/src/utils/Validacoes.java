package utils;

import entidades.Pessoa;
import entidades.PessoaFisica;

public class Validacoes {

    private String opcaoPessoa;
    public Validacoes(String opcaoPessoa) {
        this.opcaoPessoa=opcaoPessoa;
    }

    public  boolean campoValido(Integer valor, String msg) {

        if ((valor < 18) || (valor > 100)) {
            System.out.println(msg);
            return false;
        }

        return true;
    }

    public  boolean campoValido(String valor, String msg) {

        if (valor.isEmpty()) {
            System.out.println(msg);
            return false;
        }

        return true;
    }

    public boolean validarDadosPessoa(Pessoa pessoa) {

        String msg;
        if(opcaoPessoa.equals("f")){
            msg="Pessoa Fisica";
        }else{
            msg="Pessoa Juridica";
        }
        if (pessoa == null) {
            System.out.println("Id da "+ msg +" não foi encontrado, tente novamente");
            return false;
        } else {
            System.out.println("=====================================");
            System.out.println(pessoa.exibir());
            System.out.println("tecle qualquer tecla para continuar.. ");
            return true;
        }

    }
}
