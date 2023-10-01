package utils;

import entidades.Pessoa;


import java.io.IOException;
import java.util.Scanner;

public class Util {
    private String opcaoAcao;
    private String opcaoPessoa;
    private Scanner scanner = new Scanner(System.in);
    public Util(String opcaoPessoa, String opcaoAcao) {

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
                }
                return false;
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

            }
            return false;
        }
        return true;
    }

    public boolean verificarInstancia(Pessoa pessoa) {

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

    // mensagem de espera para apresentação de dados
    public void clickMe(){

        System.out.println("");
        System.out.println("tecle qualquer tecla para continuar.. ");
        try{
            System.in.read();
              }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String inputDadosText(String msgTitulo,String msgErr,String valorOriginal) {
        boolean inloop = true;
        String sReturn="";
        while (inloop) {
            System.out.println(msgTitulo);
            sReturn = scanner.nextLine();
            if (!campoValido(sReturn, msgTitulo)) {
                if (opcaoAcao.equals("A")) {
                    sReturn =valorOriginal;
                     inloop = false;
                }
            } else {
                inloop = false;
            }
        }
        return sReturn;
    }

    public Integer inputDadosNum(String msgTitulo,String msgErr,Integer valorOriginal) {
        boolean inloop = true;
        Integer sReturn=0;
        while (inloop) {
            System.out.println(msgTitulo);
            String entrada = scanner.nextLine();
            if (!campoValido(entrada, msgTitulo)) {
                if (opcaoAcao.equals("A")) {
                    sReturn =valorOriginal;
                    inloop = false;
                }
            } else {
                try {
                    sReturn =Integer.parseInt(entrada);
                    inloop = false;
                }catch (Exception e){
                    System.out.println("valor invalido, tente novamente");
                }

            }
        }
        return sReturn;
    }

}
