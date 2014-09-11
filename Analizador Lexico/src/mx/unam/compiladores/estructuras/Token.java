package mx.unam.compiladores.estructuras;

/**
 * Created by José Ángel García Salinas and Antonio Lozano Arriaga on 01/09/14.
 */
public class Token {

    String clase;
    String valor;

    /**
     * Constructor que recibe como parametro la clase y el valor del token
     * @param valor
     * @param clase
     */
    public Token(String clase, String valor) {
        this.valor = valor;
        this.clase = clase;
    }

    /**
     * Regresa el valor de un token
     * @return valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * Establece el valor de un token
     * @param valor
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Regresa la clase de un token;
     * @return clase
     */
    public String getClase() {
        return clase;
    }

    /**
     * Establece la clase de un token
     * @param clase
     */
    public void setClase(String clase) {
        this.clase = clase;
    }

    /**
     * Imprime el token en consola
     */
    public void imprime(){
        System.out.print("\n< "+this.getClase()+" | "+getValor()+" >");
    }

    /**
     * Devuelve el token como una cadena
     * @return token
     */
    @Override
    public String toString() {
        return "\n< "+this.getClase()+" | "+getValor()+" >";
    }

}
