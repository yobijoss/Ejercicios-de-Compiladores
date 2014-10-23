package com.compiladores;

import com.compiladores.estructuras.ListaDE;
import com.compiladores.estructuras.Token;

/**
 * Created by jagspage2013 on 23/10/14.
 */
public class TablaSimbolos {

    public ListaDE<Token> tabla_simbolos;
    public ListaDE<Token> diccionario;

    public TablaSimbolos(){
        tabla_simbolos = new ListaDE<Token>();
    }
    public void add(Token tok){
        if(tabla_simbolos.buscar(tok)==null){
            tabla_simbolos.insertarAlFinal(tok);
        }
    }

    public void listar(){
        System.out.println("\n----Tabla de Simbolos----");
        tabla_simbolos.listar();

    }


}
