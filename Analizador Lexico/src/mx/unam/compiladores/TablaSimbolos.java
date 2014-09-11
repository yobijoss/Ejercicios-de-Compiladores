package mx.unam.compiladores;

import mx.unam.compiladores.estructuras.ListaDE;
import mx.unam.compiladores.estructuras.Token;

/**
 * Created by José Ángel García Salinas and Antonio Lozano Arriaga on 01/09/14.
 * Clase que contiene una lista de simbolos.
 */
public class TablaSimbolos {

    public static ListaDE<Token> listaSimbolos;

    /**
     * Constructor que inicializa la lista de simbolo, esta contendrá tokens;
     */
    public TablaSimbolos() {
        this.listaSimbolos = new ListaDE<Token>();
    }

    /**
     * Imprime los tokens de la lista de simbolos.
     */
    public static void imprime(){
        System.out.print("\n\n Tabla de Simbolos \n\n");
        listaSimbolos.listar();
    }

}
