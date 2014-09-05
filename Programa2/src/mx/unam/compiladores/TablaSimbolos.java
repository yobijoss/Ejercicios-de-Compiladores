package mx.unam.compiladores;

import mx.unam.compiladores.estructuras.ListaDE;
import mx.unam.compiladores.estructuras.Token;

/**
 * Created by jagspage2013 on 02/09/14.
 */
public class TablaSimbolos {

    public static ListaDE<Token> listaSimbolos;


    public TablaSimbolos() {
        this.listaSimbolos = new ListaDE<Token>();
    }


    public void imprime(){
        listaSimbolos.listar();
    }

}
