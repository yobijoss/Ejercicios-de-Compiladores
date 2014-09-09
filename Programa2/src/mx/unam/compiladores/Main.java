package mx.unam.compiladores;

import mx.unam.compiladores.estructuras.ListaDE;
import mx.unam.compiladores.estructuras.Token;

public class Main {

    public static void main(String[] args) {

        TablaSimbolos tabla = new TablaSimbolos();

        AnalizadorFeliz analizadorFeliz = new AnalizadorFeliz();

        Token token ;
        while((token=analizadorFeliz.getSigToken()) != null){
            i("\nToken encontrado estado de aceptación");
        }


        TablaSimbolos.listaSimbolos.listar();
    }

    private static  void i(String i){
        System.out.println(i);
    }
}
