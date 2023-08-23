package utils;

public class Validacoes {
    public Validacoes() {
    }

    public  boolean campoValido(Integer valor, String msg) {

        if ((valor < 18) || (valor > 100)) {
            System.out.println(msg);
            return false;
        }

        return true;
    }

    public  boolean campoValido(String valor, String msg) {

        if (valor.isEmpty()) {
            System.out.println(msg);
            return false;
        }

        return true;
    }

}
