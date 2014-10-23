/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parserrecursivo;

/**
 * 
 * @author Ulises Mercado Martinez>
 */


public class Token {
    String lex;
    int clase;
    char atomo;
    int line;
    int column;
    
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
    
    public void imp(){
        System.out.println("Token ...........");
        System.out.println("Lexema: "+ lex);
        System.out.println("Clase: "+clase);
        System.out.println("Linea: "+line);
        System.out.println("Columna: "+column);
    }
}
