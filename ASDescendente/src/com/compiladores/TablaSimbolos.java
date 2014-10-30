package com.compiladores;

import com.compiladores.estructuras.ListaDE;
import com.compiladores.estructuras.Token;

/**
 * Created by José Ángel García Salinas & Antonio Lozano Arriaga on 23/10/14.
 * Clase que genera la tabla de simbolos utilizada en el Parser
 * También genera una tabla diccionario donde se muestran los tipos de tokens
 */
public class TablaSimbolos {

    public ListaDE<Token> tabla_simbolos;
    public ListaDE<Token> diccionario;

    /**
     * Constructor donde inicializamos nuestra tabla de simbolos con las dos listas a utilizar
     *
     */
    public TablaSimbolos(){
        tabla_simbolos = new ListaDE<Token>();
        diccionario = new ListaDE<Token>();
        init();
    }


    /**
     * Función donde poblamos la lista con los tokens diccionario
     */
    public void init(){
        diccionario.insertarAlFinal(new Token("PALABRA RESERVADA", 1, 'p',0, 0));
        diccionario.insertarAlFinal(new Token("ID", 2, 'i',0, 0));
        diccionario.insertarAlFinal(new Token("ENTERO", 3, 'n',0, 0));
        diccionario.insertarAlFinal(new Token("REAL", 4, 'r',0, 0));
        diccionario.insertarAlFinal(new Token("CARACTER", 5, 'c',0, 0));
        diccionario.insertarAlFinal(new Token("+", 6, '+',0, 0));
        diccionario.insertarAlFinal(new Token("-", 7, '-',0, 0));
        diccionario.insertarAlFinal(new Token("*", 8, '*',0, 0));
        diccionario.insertarAlFinal(new Token("/", 9, '/',0, 0));
        diccionario.insertarAlFinal(new Token(":=", 10, '=',0, 0));
        diccionario.insertarAlFinal(new Token(";", 11, ';',0, 0));
        diccionario.insertarAlFinal(new Token("(", 12, '(',0, 0));
        diccionario.insertarAlFinal(new Token(")", 13, ')',0, 0));
        diccionario.insertarAlFinal(new Token(",", 14, ',',0, 0));
        diccionario.insertarAlFinal(new Token("EOF", -1, 'e',0, 0));
    }

    /**
     * Funcion que agrega un nuevo Token a la lista de simbolos, sin repetir.
     * @param tok el token a insertar
     */
    public void add(Token tok){
        if(tabla_simbolos.buscar(tok)==null){
            tabla_simbolos.insertarAlFinal(tok);
        }
    }

    /**
     * Función que lista el diccionario de datos y la tabla de simbolos.
     */
    public void listar(){

        System.out.println("\n---Diccionario de datos----");
        diccionario.listar();


        System.out.println("\n\n----Tabla de Simbolos----");
        tabla_simbolos.listar();

    }


}
