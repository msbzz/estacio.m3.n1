import entidades.PessoaFisica;
import model.PessoaFisicaRepo;

public class MainPart1 {
    public static void main(String[] args) {
        PessoaFisicaRepo repo1= new PessoaFisicaRepo();

        repo1.inserir(new PessoaFisica("Matheus",25,"1222611")) ;
        repo1.inserir(new PessoaFisica("Marcia",22,"1223211")) ;
        repo1.inserir(new PessoaFisica("Carlos",19,"122222")) ;

        System.out.println("");
        System.out.println("");
        System.out.println("Dados Pessoa Fisica Aramzenados");
        repo1.persistir("listaPessoafisica");
        System.out.println("");
        System.out.println("Dados Pessoa Fisica Recuperados");
        repo1.recuperar("listaPessoafisica");

    }
}