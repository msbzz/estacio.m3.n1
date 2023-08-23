package services;

import java.util.Scanner;

import dao.Dao;
import entidades.Pessoa;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;
import model.PessoaFisicaRepo;
import model.PessoaJuridicaRepo;
import utils.Validacoes;


;

public class Acoes {
    PessoaFisica pessoaFisica;
    PessoaJuridica pessoaJuridica;

    PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
    PessoaJuridicaRepo repo2 = new PessoaJuridicaRepo();
    Scanner scanner = new Scanner(System.in);
    private String opcaoPessoa;
    private String opcaoAcao;

    static Integer idBusca = 0;

    private Validacoes vld;

    private Dao dao;

    public Acoes(String opcaoPessoa, String opcaoAcao) {
        this.opcaoPessoa = opcaoPessoa;
        this.opcaoAcao = opcaoAcao;
        this.vld = new Validacoes(opcaoPessoa);
        this.dao = new Dao(opcaoPessoa);
    }

    public void execuntandoAcoes() {
        String tpPessoa = (opcaoPessoa == "f") ? "pessoa fisica" : "pessoa juridica";
        switch (opcaoAcao) {
            case "I":
                preencheDadosPessoa();
                inserirDados();
                break;
            case "A":
                obterId();
                alterarDados();

                break;
            case "R":
                obterId();
                //excluirPessoa();
                System.out.println("excluindo dados " + tpPessoa);
                break;
            case "B":
                System.out.println("buscar dados by id " + tpPessoa);
                obterId();
                obtemDadosById(idBusca);
                break;
            case "S":
                //exibirTodos
                System.out.println("exibir todos dados " + tpPessoa);
                dao.obterTodos();
                break;
            case "P":
                //persistirDados
                System.out.println("persisitir dados " + tpPessoa);
                break;
            case "G":
                //recuperarDados(Get)
                System.out.println("recuperar dados " + tpPessoa);
                break;
            case "E":
                //sair(exit)
                System.out.println("sair !");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Escolha inválida. Tente novamente.");
        }
    }

    private void inserirDados() {
        if (opcaoPessoa.equals("f")) {
            dao.inserirDados(pessoaFisica);
        } else {
            dao.inserirDados(pessoaJuridica);
        }
    }

    private void preencheDadosPessoa() {

        System.out.println("Digite o nome");
        String nome = scanner.next();

        if (!vld.campoValido(nome, "nome precisa ser preenchido")) {
            if (opcaoAcao.equals("A")) {
                nome = (opcaoPessoa.equals("f")) ? pessoaFisica.getNome() : pessoaJuridica.getNome();
            } else {
                return;
            }
        }


        if (opcaoPessoa.equals("f")) {
            System.out.println("Digite o idade");
            Integer idade = scanner.nextInt();
            if (!vld.campoValido(nome, "a idade precisa estar entre 18 e 100 anos")) {
                if (opcaoAcao.equals("A")) {
                    nome = (opcaoPessoa.equals("f")) ? pessoaFisica.getNome() : pessoaJuridica.getNome();
                } else {
                    return;
                }
            }
            System.out.println("Digite o cpf");
            String cpf = scanner.next();
            if (!vld.campoValido(cpf, "a o cpf precisa ser definido")) {
                if (opcaoAcao.equals("A")) {
                    cpf = pessoaFisica.getCpf();
                } else {
                    return;
                }
            }
            atualizarInstancia(new PessoaFisica(nome, idade, cpf));
        } else {
            System.out.println("Digite o cnpj");
            String cnpj = scanner.next();
            if (!vld.campoValido(cnpj, "a o cnpj precisa ser definido")) {
                if (opcaoAcao.equals("A")) {
                    cnpj = pessoaJuridica.getCnpj();
                } else {
                    return;
                }
            }
            atualizarInstancia(new PessoaJuridica(nome, cnpj));

        }

    }

    private void atualizarInstancia(Pessoa cls) {
        if (opcaoPessoa == "f") {
            pessoaFisica = (PessoaFisica) cls;

        } else {
            pessoaJuridica = (PessoaJuridica) cls;

        }
    }

    private void obterId() {
        //somente valido para alteraçao/exclusão/busca
        while (true) {
            try {
                System.out.println("Digite o id");
                idBusca = scanner.nextInt();
                return;
            } catch (RuntimeException e) {
                System.out.println("Id inválido,valor deve ser numerico. Tente novamente");
                scanner.nextLine();  // Limpa o buffer de entrada
            }
        }
    }


    private void alterarDados() {

        if (obtemDadosById(idBusca)) {
            preencheDadosPessoa();
            alterarDadosPessoa();
        }

    }


    private boolean obtemDadosById(Integer id) {

        boolean pessoaValida;

        if (opcaoPessoa == "f") {
            pessoaFisica = (PessoaFisica) dao.obterPessoa(id);
            pessoaValida=vld.validarDadosPessoa(pessoaFisica);
        } else {
            pessoaJuridica = (PessoaJuridica) dao.obterPessoa(id);
            pessoaValida=vld.validarDadosPessoa(pessoaJuridica);
        }

        if (pessoaValida){
            return true;
        }else{
            return false;
        }


    }

    private void alterarDadosPessoa() {
        if (opcaoPessoa == "f") {
            dao.alterar(pessoaFisica);
        } else {
            dao.alterar(pessoaJuridica);
        }
    }


}
