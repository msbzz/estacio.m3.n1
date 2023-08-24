package utils;

import entidades.Pessoa;


import java.io.IOException;

public class Validacoes {
    private String opcaoAcao;
    private String opcaoPessoa;
    public Validacoes(String opcaoPessoa,String opcaoAcao) {

        this.opcaoPessoa=opcaoPessoa;
        this.opcaoAcao=opcaoAcao;
    }

    // validação idade
    public  boolean campoIdadeValida(String valor, String msg) {
        try {
            Integer idade = Integer.parseInt(valor);
            if ((idade < 18) || (idade > 99)) {
                if (!opcaoAcao.equals("A")) {
                    System.out.println(msg);
                    clickMe();
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, insira um número inteiro válido para a idade.");
            return false;
        }



        return true;
    }

    // validação nome
    public  boolean campoValido(String valor, String msg) {

        if (valor.isEmpty()) {

            if (!opcaoAcao.equals("A")){
                System.out.println(msg);
                clickMe();
                return false;
            }

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
            clickMe();
            return true;
        }

    }

    //enviar mensagem e aguardar teclar algo
    public void clickMe(){
        System.out.println("tecle qualquer tecla para continuar.. ");
        try{
            System.in.read();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
