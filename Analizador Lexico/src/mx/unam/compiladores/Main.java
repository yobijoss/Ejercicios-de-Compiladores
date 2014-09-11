package mx.unam.compiladores;

import mx.unam.compiladores.estructuras.Token;

/**
 * Created by José Ángel García Salinas and Antonio Lozano Arriaga on 01/09/14.
 * Clase principal dond eobtenemos los tokens de un código fuente en un archivo
 */
public class Main {

    /**
     * Metodo principal donde se listan los tokens de on código fuente
     * @param args
     */
    public static void main(String[] args) {

        TablaSimbolos tabla = new TablaSimbolos();

        AnalizadorSuperFeliz analizadorSuperFeliz = new AnalizadorSuperFeliz(args[0]);

        Token token ;
        while((token= analizadorSuperFeliz.getSigToken()) != null){
            //i("\nToken encontrado estado de aceptación");
        }


        TablaSimbolos.imprime();
    }

    /**
     * Imprime la cadena ingresada
     * @param i cadena ingresada
     */
    private static  void i(String i){
        System.out.println(i);
    }
}
