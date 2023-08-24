import entidades.PessoaFisica;
import entidades.PessoaJuridica;
import model.PessoaFisicaRepo;
import model.PessoaJuridicaRepo;

public class MainPart1 {
    public static void main(String[] args) {
        PessoaFisicaRepo repo1= new PessoaFisicaRepo();
        PessoaFisicaRepo repo2= new PessoaFisicaRepo();
        PessoaJuridicaRepo repo3= new PessoaJuridicaRepo();
        PessoaJuridicaRepo repo4= new PessoaJuridicaRepo();
        /*
        repo1.inserir(new PessoaFisica("Matheus",25,"1222611")) ;
        repo1.inserir(new PessoaFisica("Marcia",22,"1223211")) ;
        repo1.inserir(new PessoaFisica("Carlos",19,"122222")) ;

        repo3.inserir(new PessoaJuridica("BB1","1222611")) ;
        repo3.inserir(new PessoaJuridica("M.LUIZA","1223211")) ;
        repo3.inserir(new PessoaJuridica("LOJAS S/A","122222")) ;

        System.out.println("");
        System.out.println("");
        System.out.println("Dados Pessoa Fisica Aramzenados");
        repo1.persistir("listaPessoafisica");

         */
        System.out.println("Dados Pessoa Fisica Recuperados");
        repo2.recuperar("listaPessoafisica");

        /*
        System.out.println("Dados Pessoa Juridicas Aramzenados");
        repo3.persistir("listaPessoaJuridica");
         */
        System.out.println("Dados Pessoa Juridica Recuperados");
        repo4.recuperar("listaPessoaJuridica");
        System.out.println("");
        System.out.println("Dados Pessoa Fisica Recuperados");
        repo2.recuperar("listaPessoafisica");

        System.out.println("");
        System.out.println("Dados Pessoa Jjuridicas Recuperados");
        repo4.recuperar("listaPessoaJuridica");
    }
}