package model;

import entidades.PessoaFisica;

import java.io.*;
import java.util.*;

public class PessoaFisicaRepo {

    private List<PessoaFisica> listaPessoasFisicas;

    // Construtor
    public PessoaFisicaRepo() {

        this.listaPessoasFisicas = new ArrayList<>();
    }

    // Método para inserir uma pessoa física
    public void inserir(PessoaFisica pessoaFisica) {

        listaPessoasFisicas.add(pessoaFisica);
    }

    // Método para alterar uma pessoa física

    public boolean alterar(PessoaFisica pessoaFisica) {
        for (int i = 0; i < listaPessoasFisicas.size(); i++) {
            if (listaPessoasFisicas.get(i).getId() == pessoaFisica.getId()) {
                listaPessoasFisicas.set(i, pessoaFisica);
                return true;  // Retorna true indicando sucesso na alteração
            }
        }

        return false;  // Retorna false se a pessoa física não foi encontrada na lista
    }



    // Método para excluir uma pessoa física por ID


    public boolean excluir(int id) {
        for (PessoaFisica p:listaPessoasFisicas) {
            if(p.getId()==id){
                listaPessoasFisicas.remove(p);
                return true;
            }
        }
        return false;  // Retorna false se a pessoa física não foi encontrada na lista
    }


    public PessoaFisica obter(int id) {
        return listaPessoasFisicas.stream()
                .filter(pessoaFisica -> pessoaFisica.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Método para obter todas as pessoas físicas

    public List<PessoaFisica> obterTodos() {
        return listaPessoasFisicas ;
    }
    public void persistir(String nomeArquivo) {

        if(listaPessoasFisicas.isEmpty()){
            System.out.println("Não existem registros a serem persistidos");
            return;
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            out.writeObject(listaPessoasFisicas);
        } catch (IOException e) {
            e.printStackTrace();
         }
    }


    public void recuperar(String nomeArquivo) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            listaPessoasFisicas = (ArrayList<PessoaFisica>) in.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ListarTodas(){
        for (PessoaFisica pf: listaPessoasFisicas) {
            System.out.println(pf.exibir());
        }

    }


}
