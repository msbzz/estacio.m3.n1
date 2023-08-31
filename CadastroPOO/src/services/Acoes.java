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
        String tpPessoa = (opcaoPessoa == "f") ? "Pessoa fisica" : "Pessoa juridica";
        switch (opcaoAcao) {
            case "I":
                preencheDadosPessoa();
                inserirDados();
                break;
            case "A":
                System.out.println("");
                obterId();
                alterarDados();
                break;
            case "R":
                excluir();
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
                todos();
                break;
            case "P":
                //persistirDados
                System.out.println("persisitir dados " + tpPessoa);
                System.out.println("");
                persistirDados();
                break;
            case "G":
                //recuperarDados(Get)
                System.out.println("recuperar dados " + tpPessoa);
                System.out.println("");
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

    private void excluir() {
        obterId();
        obtemDadosById(idBusca);

        boolean inloop=true;

        while(inloop){
            System.out.println("Confirma a exclusão do item apresentdo(S/N) ?");
            String resp = scanner.nextLine();

            if(resp.equals("N")||resp.equals("n")){
                return;
            }else if(resp.equals("s")||resp.equals("S")){
                inloop=false;
            }
        }
        dao.exluir(idBusca);
     }

    private void todos() {
        dao.obterTodos();
        vld.clickMe();
    }

    private void recuperarDados() {
        prefixo = inputDadosText("Digite o prefixo do arquivo","o prefixo precisa ser preenchido","nome");
        dao.recuperar(prefixo);
        vld.clickMe();
    }

    private void persistirDados() {
          prefixo = inputDadosText("Digite o prefixo do arquivo","o prefixo precisa ser preenchido","nome");
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
        String nome = "";
        String cpf = "";
        String cnpj = "";
        Integer idade = 0;

        // nome
        if (opcaoAcao.equals("A")) {
            scanner.nextLine();
        }

        nome = inputDadosText("Digite o nome","nome precisa ser preenchido","nome");

        if (opcaoPessoa.equals("f")) {
            // idade
            idade= inputDadosNum("Digite o idade","insira a idade entre 18 e 99 anos","idade");
            // cpf
            cpf =inputDadosText("Digite o cpf","o cpf precisa ser definido","cpf");

            vld.clickMe();

            concluiEntradaDeDadosPessoa(cpf,nome,idade);


        } else {
            // cnpj
            cnpj=inputDadosText("Digite o cnpj","o cnpj precisa ser definido","cnpj");

            vld.clickMe();

            concluiEntradaDeDadosPessoa(cnpj,nome);

        }

    }

    private void concluiEntradaDeDadosPessoa(String cnpj, String nome) {
        if (opcaoAcao.equals("A")) {
            pessoaJuridica.setCnpj(cnpj);
            pessoaJuridica.setNome(nome);
        }else {
            atualizarInstancia(new PessoaJuridica(nome, cnpj));
        }
    }

    private void concluiEntradaDeDadosPessoa(String cpf, String nome, Integer idade) {

        if (opcaoAcao.equals("A")) {
            pessoaFisica.setCpf(cpf);
            pessoaFisica.setNome(nome);
            pessoaFisica.setIdade(idade);
        }else{
            atualizarInstancia(new PessoaFisica(nome, idade, cpf));
        }
    }

    private String inputDadosText(String msgTitulo,String msgErr,String tp) {
        boolean inloop = true;
        String sReturn="";
        while (inloop) {
            System.out.println(msgTitulo);
            sReturn = scanner.nextLine();
            if (!vld.campoValido(sReturn, msgTitulo)) {
                if (opcaoAcao.equals("A")) {
                    switch (tp) {
                        case "nome":
                            sReturn = (opcaoPessoa.equals("f")) ? pessoaFisica.getNome() : pessoaJuridica.getNome();
                            break;
                        case "cpf":
                            sReturn =pessoaFisica.getCpf();
                            break;
                        case "cnpj":
                            sReturn =pessoaJuridica.getCnpj();
                            break;


                    }

                    inloop = false;
                }
            } else {
                inloop = false;
            }
        }
        return sReturn;
    }

    private Integer inputDadosNum(String msgTitulo,String msgErr,String tp) {
        boolean inloop = true;
        Integer sReturn=0;
        while (inloop) {
            System.out.println(msgTitulo);
            String entrada = scanner.nextLine();
            if (!vld.campoValido(entrada, msgTitulo)) {
                if (opcaoAcao.equals("A")) {
                    switch (tp) {
                        case "idade":
                            sReturn = pessoaFisica.getIdade();
                    }

                    inloop = false;
                }
            } else {
                sReturn =Integer.parseInt(entrada);
                inloop = false;
            }
        }
        return sReturn;
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
            System.out.println("=============================================================================");
            System.out.println("Caso deseja manter o valor original, tecle [enter] para seguir o proximo item");
            System.out.println("=============================================================================");
            vld.clickMe();
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
