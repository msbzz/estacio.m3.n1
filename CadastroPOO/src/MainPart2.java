import services.Acoes;

import java.util.Scanner;


public class MainPart2 {

    static Scanner scanner = new Scanner(System.in);
    static String opcaoPessoa = "";
    static String opcaoAcao = "";

    public static void main(String[] args) {
        while (true) {
            menuAcoes();
            menuPessoa();
            executarAcao();
        }

    }

    private static void executarAcao() {
        Acoes acoes = new Acoes(opcaoPessoa,opcaoAcao);
        acoes.executandoAcoes();
    }

    private static void menuPessoa() {
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica  ");

        while (true) {
            String pessoa = scanner.next();
            if (pessoa.contains("f") || pessoa.contains("F") || pessoa.contains("j") || pessoa.contains("J")) {
                if (pessoa.contains("f") || pessoa.contains("F")) {
                    //dados = new PessoaFisica();
                    opcaoPessoa = "f";
                } else {
                    //dados = new PessoaJuridica();
                    opcaoPessoa = "j";
                }
                break;
            } else {
                System.out.println("Pessoa deve ser (F)isica ou (J)uridica");
            }
        }


    }

    private static void menuAcoes() {
        while (true) {
            System.out.println("");
            System.out.println("");
            System.out.println("==========================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("==========================================");
            System.out.print("opção : ");
            int escolha = -1;
            try {
                escolha = scanner.nextInt();

            } catch (RuntimeException e) {
                escolha = -1;
                scanner.nextLine();  // Limpa o buffer de entrada
            }

            switch (escolha) {
                case 1:
                    opcaoAcao = "I";
                    //Incluir;
                    return;
                case 2:
                    opcaoAcao = "A";
                    //Alterar
                    return;
                case 3:
                    opcaoAcao = "R";
                    //excluir (Remove);
                    return;
                case 4:
                    opcaoAcao = "B";
                    //buscarPeloId
                    return;
                case 5:
                    opcaoAcao = "S";
                    //exibirTodos (Show all)
                    return;
                case 6:
                    opcaoAcao = "P";
                    //persistirDados
                    return;
                case 7:
                    opcaoAcao = "G";
                    //recuperarDados(Get)
                    return;
                case 0:
                    opcaoAcao = "E";
                    //sair(Exit)
                    System.out.println("Encerrar processo ...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Escolha inválida. Tente novamente.");

            }

        }
    }





}