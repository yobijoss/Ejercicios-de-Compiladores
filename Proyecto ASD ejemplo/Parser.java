/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parserrecursivo;

import java.io.IOException;

/**
 * 
 * @author Ulises Mercado Martinez>
 */


public class Parser {
    
    Yylex lex;
    
    
    Parser(Yylex yylex){
        this.lex = yylex;
    }
    
    public final char ID='i', REAL ='r', ENT='n', MAS='+', MUL='*', LPAR='(', RPAR=')', SALTO='\n';
    
    Token tok;
    
    void error(Token t){
        System.out.println("Error Sintáctico.........");
        System.out.println("Linea: " + t.line);
        System.out.println("Columna: " + t.column);
        System.out.println("Token: "+ t.lex);
    }
    void avanzar() throws IOException{
        tok = lex.yylex();
        tok.imp();
    }
    
    void consumir(char t) throws IOException{
        if(t==tok.atomo)
            avanzar();
        else
            error(tok);
    }
    
    public void parse() throws IOException{
        avanzar();
        L();
        consumir('e');
    }
    
    /**
     * L-> EL'
     * @throws java.io.IOException
     */
    public void L() throws IOException{
        E();
        LP();
    }
    
    /**
     * L' -> \nE | epsilon
     * @throws java.io.IOException
     */
    public void LP() throws IOException{
        while(SALTO==tok.atomo){
            avanzar();
            E();
        }
    }
    
    /**
     * E->TE'
     * @throws IOException 
     */
    public void E() throws IOException{
        System.out.println("E()");
        T();
        EP();
    }
    
    /**
     * E'->+TE' | epsilon
     * @throws IOException 
     */
    public void EP() throws IOException{
        System.out.println("EP");
        while(MAS==tok.atomo){
            avanzar();
            T();
            EP();
        }
    }
    
    /**
     * T->FT'
     * @throws IOException 
     */
    public void T() throws IOException{
        System.out.println("T()");
        F();
        TP();
    }
    
    /**
     * T'->*FT' | epsilon
     * @throws IOException 
     */
    public void TP() throws IOException{
        System.out.println("TP()");
        while(MUL==tok.atomo){
            avanzar();
            F();
            TP();
        }
    }
    
    /**
     * F-> id | (E) | num | real
     * @throws IOException 
     */
    public void F() throws IOException{
        System.out.println("F()");
        switch(tok.atomo){
            case ID:
                avanzar();
                break;
            case LPAR:
                avanzar();
                E();
                consumir(RPAR);
                break;
            case ENT:
                avanzar();
                break;
            case REAL:
                avanzar();
                break;
            default:
                error(tok);
        }
    }
}
