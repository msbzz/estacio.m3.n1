import entidades.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridicaRepo;

public class MainPart1 {
    public static void main(String[] args) {
        PessoaFisicaRepo repo1= new PessoaFisicaRepo();
        PessoaFisicaRepo repo2= new PessoaFisicaRepo();
        PessoaJuridicaRepo repo3= new PessoaJuridicaRepo();
        PessoaJuridicaRepo repo4= new PessoaJuridicaRepo();

        repo1.inserir(new PessoaFisica("Matheus",25,"1222611")) ;
        repo1.inserir(new PessoaFisica("Marcia",22,"1223211")) ;
        repo1.inserir(new PessoaFisica("Carlos",19,"122222")) ;

        System.out.println("");
        System.out.println("");
        System.out.println("Dados Pessoa Fisica Aramzenados");
        repo1.persistir("listaPessoafisica");
        System.out.println("");
        System.out.println("Dados Pessoa Fisica Recuperados");
        repo2.recuperar("listaPessoafisica");

    }
}