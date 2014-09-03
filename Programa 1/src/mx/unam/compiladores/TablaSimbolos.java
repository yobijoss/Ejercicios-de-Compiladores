package mx.unam.compiladores;

import mx.unam.compiladores.estructuras.ListaDE;
import mx.unam.compiladores.estructuras.Token;

/**
 * Created by jagspage2013 on 02/09/14.
 */
public class TablaSimbolos {

    private ListaDE<Token> listaSimbolos;

    public void setListaSimbolos(ListaDE<Token> listaSimbolos) {
        this.listaSimbolos = listaSimbolos;
    }

    public TablaSimbolos() {
        this.listaSimbolos = new ListaDE<Token>();
    }

    public ListaDE<Token> getListaSimbolos() {
        return listaSimbolos;
    }

    public void imprime(){
        listaSimbolos.listar();
    }

}
