package dao;

import entidades.Pessoa;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;
import model.PessoaFisicaRepo;
import model.PessoaJuridicaRepo;

import javax.swing.plaf.PanelUI;
import java.util.List;

public class Dao {

    private String opcaoPessoa;
    static PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
    static PessoaJuridicaRepo repo2 = new PessoaJuridicaRepo();

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

    public Pessoa obterPessoa(Integer id) {
        if (opcaoPessoa == "f") {
            return repo1.obter(id);
        } else {
            return repo2.obter(id);
        }
    }

    public void obterTodos() {
        List<PessoaFisica> lPessaFisica;
        List<PessoaJuridica> lPessaJuridica;
        if (opcaoPessoa == "f") {
            lPessaFisica=repo1.obterTodos();
            imprimeTodos(lPessaFisica);
        } else {
            lPessaJuridica =repo2.obterTodos();
            imprimeTodos(lPessaJuridica);
        }
    }

    private void imprimeTodos(List<? extends Pessoa> lista) {
        for (Pessoa p:lista) {
            System.out.println(p.exibir());
        }
    }

}
