package entidades;

import utils.IDControle;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    private static Integer id =0;
    private Integer individualId;
    private String nome;

    public Pessoa() {

    }

    public Pessoa( String nome) {
        //this.id += 1;
        this.id= IDControle.getID();
        this.individualId=id;

        this.nome = nome;
    }

    public Integer getId() {
        return individualId;
    }

    /*
     public void setId(Integer id) {
        this.id = id;
    }

     */

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public abstract String exibir();

}