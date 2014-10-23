/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.compiladores.estructuras;

/**
 * 
 * @author Ulises Mercado Martinez>
 */


public class Token {
    public String lex;
    public int clase;
    public char atomo;
    public int line;
    public int column;
    


    public Token(String lex, int clase, char atomo, int line, int column){
        this.lex = lex;
        this.clase = clase;
        this.atomo = atomo;
        this.line = line;
        this.column = column;
    }
    
    public boolean equals(Token t){
        if(this.lex.equals(t.lex)){
            if(this.clase == t.clase)
                return true;
        }
        return false;
    }
    
    public String toString(){
        return "<"+this.lex+" | "+this.clase+">";
    }
    public void imprimir(){
        System.out.println( "<"+this.lex+" | "+this.clase+">");
    }
    public String getValor(){return this.lex;}

    public void imp(){
        System.out.println("Token ...........");
        System.out.println("Lexema: "+ lex);
        System.out.println("Clase: "+clase);
        System.out.println("Linea: "+line);
        System.out.println("Columna: "+column);
    }
}
