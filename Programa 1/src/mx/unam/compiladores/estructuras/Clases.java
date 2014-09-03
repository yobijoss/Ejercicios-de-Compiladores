package mx.unam.compiladores.estructuras;

/**
 * Created by jagspage2013 on 02/09/14.
 */
public class Clases  {

    private ListaDE<Token> listaClases ;

    public Clases ( ){
        listaClases = new ListaDE<Token>();
        listaClases.InsertarAlFinal(new Token("0","if"));
        listaClases.InsertarAlFinal(new Token("0","else"));
        listaClases.InsertarAlFinal(new Token("1","ID"));
        listaClases.InsertarAlFinal(new Token ("2",""));

    }


}
