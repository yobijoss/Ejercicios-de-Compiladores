package mx.unam.compiladores.estructuras;

/**
 * Created by jagspage2013 on 02/09/14.
 */
public class Token {

    String clase;
    String valor;


    public Token(String valor, String clase) {
        this.valor = valor;
        this.clase = clase;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }


    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public void imprime(){
        System.out.print("\n< "+this.getClase()+" | "+getValor()+" >");
    }

    @Override
    public String toString() {
        return "\n< "+this.getClase()+" | "+getValor()+" >";
    }
}
