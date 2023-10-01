package dao;

import entidades.Pessoa;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;
import model.PessoaFisicaRepo;
import model.PessoaJuridicaRepo;

import java.util.List;

public class Dao {
    private static PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
    private static PessoaJuridicaRepo repo2 = new PessoaJuridicaRepo();
    private String opcaoPessoa;

    public Dao(String opcaoPessoa) {
        this.opcaoPessoa = opcaoPessoa;
    }
    public void inserirDados(Pessoa pessoa) {
        if (opcaoPessoa == "f") {
            repo1.inserir((PessoaFisica) pessoa);

        } else {
            repo2.inserir((PessoaJuridica) pessoa);
        }
    }

    public void exluir(Integer id) {
        repo1.excluir(id);
    }

    public Pessoa obterPessoa(Integer id) {
        if (opcaoPessoa == "f") {
            return repo1.obter(id);
        } else {
            return repo2.obter(id);
        }
    }

    public void obterTodos() {
        if (opcaoPessoa == "f") {
            List<PessoaFisica> lPessaFisica = repo1.obterTodos();
            imprimeTodos(lPessaFisica);
        } else {
            List<PessoaJuridica> lPessaJuridica = repo2.obterTodos();
            imprimeTodos(lPessaJuridica);
        }

    }

    public void alterar(Pessoa pessoa) {
        if (opcaoPessoa == "f") {
            repo1.alterar((PessoaFisica) pessoa);
        } else {
            repo2.alterar((PessoaJuridica) pessoa);
        }
    }
    private void imprimeTodos(List<? extends Pessoa> lista) {
        for (Pessoa p : lista) {
            System.out.println(p.exibir());
        }
    }
    public void recuperar(String prefixo) {
        if (opcaoPessoa.equals("f")) {
            repo1.recuperar(prefixo + ".fisica.bin");
        } else {
            repo2.recuperar(prefixo + ".juridica.bin");
        }
    }
    public void persistir(String prefixo) {
        if (opcaoPessoa.equals("f")) {
            repo1.persistir(prefixo + ".fisica.bin");
        } else {
            repo2.persistir(prefixo + ".juridica.bin");
        }
    }
}
