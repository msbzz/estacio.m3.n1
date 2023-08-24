package services;

import java.util.InputMismatchException;
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
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;

    private static PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
    private static PessoaJuridicaRepo repo2 = new PessoaJuridicaRepo();
    private Scanner scanner = new Scanner(System.in);
    private String opcaoPessoa;
    private String opcaoAcao;

    private String prefixo="";
    static Integer idBusca = 0;

    private Validacoes vld;

    private Dao dao;

    public Acoes(String opcaoPessoa, String opcaoAcao) {
        this.opcaoPessoa = opcaoPessoa;
        this.opcaoAcao = opcaoAcao;
        this.vld = new Validacoes(opcaoPessoa, opcaoAcao);
        this.dao = new Dao(opcaoPessoa,repo1,repo2);
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
                persistirDados();
                break;
            case "G":
                //recuperarDados(Get)
                System.out.println("recuperar dados " + tpPessoa);
                recuperarDados();
                break;
            case "E":
                //sair(exit)
                System.out.println("sair !");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Escolha inválida. Tente novamente.");
                vld.clickMe();
        }
    }

    private void recuperarDados() {
            dao.recuperar(prefixo);
    }

    private void persistirDados() {
          dao.persistir(prefixo);

    }

    private void inserirDados() {
        if (opcaoPessoa.equals("f")) {
            dao.inserirDados(pessoaFisica);
        } else {
            dao.inserirDados(pessoaJuridica);
        }
    }

    private void preencheDadosPessoa() {
        /*
        if (opcaoAcao.equals("A")) {
            if (opcaoPessoa.equals("f")) {
                pessoaFisica.exibir();
            } else {
                pessoaJuridica.exibir();
            }
        }

         */

        String nome = "";
        String cpf = "";
        String cnpj = "";
        Integer idade = 0;

        boolean inloop = true;
        // nome
        if (opcaoAcao.equals("A")) {
            scanner.nextLine();
        }
        while (inloop) {
            System.out.println("Digite o nome");
            nome = scanner.nextLine();
            if (!vld.campoValido(nome, "nome precisa ser preenchido")) {
                if (opcaoAcao.equals("A")) {
                    nome = (opcaoPessoa.equals("f")) ? pessoaFisica.getNome() : pessoaJuridica.getNome();
                    inloop = false;
                }
            } else {
                inloop = false;
            }
        }

        System.out.println("preencher nome "+nome);
        inloop = true;

        if (opcaoPessoa.equals("f")) {
            // idade
            while (inloop) {
                System.out.println("Digite o idade");
                //idade = scanner.nextInt();
                String entrada = scanner.nextLine();

                if (!vld.campoIdadeValida(entrada, "insira a idade entre 18 e 99 anos")) {
                    if (opcaoAcao.equals("A")) {
                        idade = pessoaFisica.getIdade();
                        inloop = false;
                    }
                } else {
                    idade = Integer.parseInt(entrada);
                    inloop = false;
                }

                System.out.println("preencher idade "+idade);

            }

            inloop = true;

            // cpf
            while (inloop) {
                System.out.println("Digite o cpf");
                cpf = scanner.nextLine();
                if (!vld.campoValido(cpf, "o cpf precisa ser definido")) {
                    if (opcaoAcao.equals("A")) {
                        cpf = pessoaFisica.getCpf();
                        inloop = false;
                    }
                } else {
                    inloop = false;
                }
            }

            System.out.println("preencher cpf "+cpf);
            vld.clickMe();
            if (opcaoAcao.equals("A")) {
                pessoaFisica.setCpf(cpf);
                pessoaFisica.setNome(nome);
                pessoaFisica.setIdade(idade);
            }else{
                atualizarInstancia(new PessoaFisica(nome, idade, cpf));
            }

        } else {

            inloop = true;

            // cnpj
            while (inloop) {
                System.out.println("Digite o cnpj");
                cnpj = scanner.nextLine();
                if (!vld.campoValido(cnpj, "o cnpj precisa ser definido")) {
                    if (opcaoAcao.equals("A")) {
                        cnpj = pessoaJuridica.getCnpj();
                        inloop = false;
                    }
                } else {
                    inloop = false;
                }
            }
            if (opcaoAcao.equals("A")) {
                pessoaJuridica.setCnpj(cnpj);
                pessoaJuridica.setNome(nome);
            }else {
                atualizarInstancia(new PessoaJuridica(nome, cnpj));
            }
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
                vld.clickMe();
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
            pessoaValida = vld.validarDadosPessoa(pessoaFisica);
        } else {
            pessoaJuridica = (PessoaJuridica) dao.obterPessoa(id);
            pessoaValida = vld.validarDadosPessoa(pessoaJuridica);
        }

        if (pessoaValida) {
            return true;
        } else {
            return false;
        }


    }

    private void alterarDadosPessoa() {
        if (opcaoPessoa.equals("f")) {
            dao.alterar(pessoaFisica);
        } else {
            dao.alterar(pessoaJuridica);
        }
    }


}
