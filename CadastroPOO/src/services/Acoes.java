package services;

import java.util.Scanner;

import dao.Dao;
import entidades.Pessoa;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;

import utils.Util;

public class Acoes {
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;
    private Scanner scanner = new Scanner(System.in);
    private String opcaoPessoa;
    private String opcaoAcao;
    private String prefixo="";
    static Integer idBusca = 0;
    private Util utl;
    private Dao dao;
    private String nome = "";
    private String cpf = "";
    private String cnpj = "";
    private Integer idade = 0;
    public Acoes(String opcaoPessoa, String opcaoAcao) {
        this.opcaoPessoa = opcaoPessoa;
        this.opcaoAcao = opcaoAcao;
        this.utl = new Util(opcaoPessoa, opcaoAcao);
        this.dao = new Dao(opcaoPessoa);
    }


    public void executandoAcoes() {
        String tpPessoa = (opcaoPessoa == "f") ? "Pessoa fisica" : "Pessoa juridica";
        switch (opcaoAcao) {
            case "I":
                novo();
                break;
            case "A":
                System.out.println("");
                editar();
                break;
            case "R":
                excluir();
                break;
            case "B":
                System.out.println("buscar dados by id " + tpPessoa);
                mostrarItemPorId();
                break;
            case "S":
                System.out.println("exibir todos dados " + tpPessoa);
                mostrarTodos();
                break;
            case "P":
                System.out.println("persisitir dados " + tpPessoa);
                System.out.println("");
                persistirDadosBinarios();
                break;
            case "G":
                //recuperarDados(Get)
                System.out.println("recuperar dados " + tpPessoa);
                System.out.println("");
                recuperarDadosBinarios();
                break;
            case "E":
                //sair(exit)
                System.out.println("sair !");
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Escolha inválida. Tente novamente.");
                utl.clickMe();
        }
    }
    private void mostrarItemPorId() {
        obterId();
        obtemDadosById();
    }
    private void editar() {
        obterId();
        alterarDados();
    }
    private void novo() {
        preencheDadosPessoa();
        insercaoListasPessoa();
    }
    private void excluir() {
        obterId();
        obtemDadosById();

        boolean inloop=true;


        while(inloop){
            System.out.println("Confirma a exclusão do item (S/N) ?");
            String resp = scanner.nextLine();

            if(resp.equals("N")||resp.equals("n")){
                return;
            }else if(resp.equals("s")||resp.equals("S")){
                inloop=false;
                dao.exluir(idBusca);
                System.out.println("excluindo dados " + ((opcaoPessoa=="f") ? "Pessoa Fisica": "Pessoa Juridica"));
            }
        }

     }
    private void mostrarTodos() {
        dao.obterTodos();
        utl.clickMe();
    }
    private void recuperarDadosBinarios() {
        prefixo = utl.inputDadosText("Digite o prefixo do arquivo",
                                        "o prefixo precisa ser preenchido",
                                                "");
        dao.recuperar(prefixo);
        utl.clickMe();
    }
    private void persistirDadosBinarios() {
          prefixo = utl.inputDadosText("Digite o prefixo do arquivo",
                                       "o prefixo precisa ser preenchido","");
          dao.persistir(prefixo);

    }
    private void insercaoListasPessoa() {
        if (opcaoPessoa.equals("f")) {
            dao.inserirDados(pessoaFisica);
        } else {
            dao.inserirDados(pessoaJuridica);
        }
    }
    private void edicaoListasPessoa() {
        if (opcaoPessoa.equals("f")) {
            dao.alterar(pessoaFisica);
        } else {
            dao.alterar(pessoaJuridica);
        }
    }
    private void preencheDadosPessoa() {
        String nomeNovo = "";
        String cpfNovo = "";
        String cnpjNovo = "";
        Integer idadeNovo = 0;

        // nome
        if (opcaoAcao.equals("A")) {
            scanner.nextLine();
        }

        nomeNovo = utl.inputDadosText("Digite o nome",
                           "nome precisa ser preenchido",nome);

        if (opcaoPessoa.equals("f")) {
            // idade
            idadeNovo= utl.inputDadosNum("Digite o idade",
                                  "insira a idade entre 18 e 99 anos"
                                 ,idade);
            // cpf
            cpfNovo = utl.inputDadosText("Digite o cpf",
                                       "o cpf precisa ser definido",
                                    cpf );

            utl.clickMe();

            concluiEntradaDeDadosPessoa(cpfNovo,nomeNovo,idadeNovo);


        } else {
            // cnpj
            cnpjNovo= utl.inputDadosText("Digite o cnpj",
                                       "o cnpj precisa ser definido",
                                    cnpj);

            utl.clickMe();

            concluiEntradaDeDadosPessoa(cnpjNovo,nomeNovo);

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
                scanner.nextLine();
                return;
            } catch (RuntimeException e) {
                System.out.println("Id inválido,valor deve ser numerico. Tente novamente");
                utl.clickMe();
            }
        }
    }
    private void alterarDados() {

        if (obtemDadosById()) {
            System.out.println("=============================================================================");
            System.out.println("Caso deseja manter o valor original, tecle [enter] para seguir o proximo item");
            System.out.println("=============================================================================");
            utl.clickMe();
            preencheDadosPessoa();
            edicaoListasPessoa();
        }

    }
    private boolean obtemDadosById() {

        boolean pessoaValida;
        Integer id=idBusca;

        if (opcaoPessoa == "f") {
            pessoaFisica = (PessoaFisica) dao.obterPessoa(id);
            pessoaValida = utl.verificarInstancia(pessoaFisica);
        } else {
            pessoaJuridica = (PessoaJuridica) dao.obterPessoa(id);
            pessoaValida = utl.verificarInstancia(pessoaJuridica);
        }

        if (pessoaValida) {
            if(opcaoPessoa == "f"){
              nome =pessoaFisica.getNome();
              cpf =pessoaFisica.getCpf();
              idade =pessoaFisica.getIdade();
            }else{
                nome =pessoaJuridica.getNome();
                cnpj =pessoaJuridica.getCnpj();
            }
            return true;
        } else {
            nome="";
            idade=0;
            cpf="";
            cnpj="";
            return false;
        }


    }

}
