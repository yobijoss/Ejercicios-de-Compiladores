package mx.unam.compiladores.estructuras;

/**
 * Created by jagspage2013 on 02/09/14.
 */
public class Clases  {

    private ListaDE<Token> listaClases ;

    public Clases ( ){
        listaClases = new ListaDE<Token>();
        listaClases.InsertarAlFinal(new Token("PR","if"));
        listaClases.InsertarAlFinal(new Token("PR","else"));
        listaClases.InsertarAlFinal(new Token("PR","if"));
        listaClases.InsertarAlFinal(new );

    }


}
