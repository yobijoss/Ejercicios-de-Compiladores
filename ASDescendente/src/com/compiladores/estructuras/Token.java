/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.compiladores.estructuras;

/**
 * Clase Token que define la estructura de nuestros tokens.
 * @author Ulises Mercado Martinez>
 */


public class Token {

    public String lex;
    public int clase;
    public char atomo;
    public int line;
    public int column;


    /**
     * Constructor que genera un nuevo token
     * @param lex lexema
     * @param clase clase a la que pertenece el token
     * @param atomo caracter que indica de que tipo es
     * @param line linea donde se encontró el token
     * @param column columna donde se encontró el token
     */
    public Token(String lex, int clase, char atomo, int line, int column){
        this.lex = lex;
        this.clase = clase;
        this.atomo = atomo;
        this.line = line;
        this.column = column;
    }

    /**
     * Método que compara los lexemas de dos tokens.
     * @param t token que contiene el lexema a comparar
     * @return si son iguales o no
     */
    public boolean equals(Token t){
        if(this.lex.equals(t.lex)){
            if(this.clase == t.clase)
                return true;
        }
        return false;
    }

    /**
     * Método que devuelve la estructura del token en formato de cadena
     * @return
     */
    public String toString(){
        return "<"+this.lex+" | "+this.clase+">";
    }

    /**
     * Método que imprime el token con su lexema y su clase
     */
    public void imprimir(){
        System.out.println( "<"+this.lex+" | "+this.clase+">");
    }

    /**
     * Método que devuelve el lexema de un token
     * @return lexema
     */
    public String getValor(){return this.lex;}

    /**
     * Método que imprime las caracteristicas del token.
     */
    public void imp(){
        System.out.println("Token ...........");
        System.out.println("Lexema: "+ lex);
        System.out.println("Clase: "+clase);
        System.out.println("Linea: "+line);
        System.out.println("Columna: "+column);
    }
}
