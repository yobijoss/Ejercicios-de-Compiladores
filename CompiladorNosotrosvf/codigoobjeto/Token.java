/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//package calculadora;

/**
 *
 * @author ulises
 */
public class Token {
    String lex;
    String type;
    
    public Token(String lex, String type ){
        this.lex = lex;
        this.type = type;
    }
    
    
    public boolean equals(String lex, String type){
        if(this.lex.endsWith(lex) && this.type.equals(type))
            return true;
        return false;
    }
}
