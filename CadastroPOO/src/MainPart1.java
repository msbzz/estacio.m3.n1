import entidades.PessoaFisica;
import entidades.PessoaJuridica;
import model.PessoaFisicaRepo;
import model.PessoaJuridicaRepo;

public class MainPart1 {
    public static void main(String[] args) {


        // a - Instanciar um repositório de pessoas físicas (repo1).
        PessoaFisicaRepo repo1= new PessoaFisicaRepo();

        // b - Adicionar duas pessoas físicas, utilizando o construtor
        //completo.

        repo1.inserir(new PessoaFisica("Matheus",25,"1222611")) ;
        repo1.inserir(new PessoaFisica("Marcia",22,"1223211")) ;
        repo1.inserir(new PessoaFisica("Carlos",19,"122222")) ;

        System.out.println("");
        System.out.println("");

        // c -Invocar o método de persistência em repo1, fornecendo
        //um nome de arquivo fixo, através do código.

        repo1.persistir("listaPessoafisica");

        System.out.println("Dados Pessoa Fisica Aramzenados");

        //d - Instanciar outro repositório de pessoas físicas (repo2).
        PessoaFisicaRepo repo2= new PessoaFisicaRepo();

        // e- Invocar o método de recuperação em repo2, fornecendo o
        //mesmo nome de arquivo utilizado anteriormente.

        repo2.recuperar("listaPessoafisica");

        // f- Exibir os dados de todas as pessoas físicas recuperadas.
        System.out.println("Listar Pessoas Fisicas Recuperadas");
        repo2.ListarTodas();


        // g - Instanciar um repositório de pessoas jurídicas (repo3).
        PessoaJuridicaRepo repo3= new PessoaJuridicaRepo();

        // h- Adicionar duas pessoas jurídicas, utilizando o construtor
        //completo.
        repo3.inserir(new PessoaJuridica("BB1","1222611")) ;
        repo3.inserir(new PessoaJuridica("M.LUIZA","1223211")) ;
        repo3.inserir(new PessoaJuridica("LOJAS S/A","122222")) ;

        // i - Invocar o método de persistência em repo3, fornecendo
        //um nome de arquivo fixo, através do código.
        System.out.println("Dados Pessoa Juridicas Aramzenados");
        repo3.persistir("listaPessoaJuridica");

        // j - Instanciar outro repositório de pessoas jurídicas (repo4).
        PessoaJuridicaRepo repo4= new PessoaJuridicaRepo();

        // k - Invocar o método de recuperação em repo4, fornecendo o
        //mesmo nome de arquivo utilizado anteriormente.
        repo4.recuperar("listaPessoaJuridica");

        // l - Exibir os dados de todas as pessoas jurídicas
        //recuperadas.
        System.out.println("Dados Pessoa Juridicas Recuperados");
        repo4.ListarTodas();


    }
}