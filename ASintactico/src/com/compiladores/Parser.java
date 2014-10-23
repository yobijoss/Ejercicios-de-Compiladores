/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.compiladores;

import com.compiladores.estructuras.Token;

import java.io.IOException;

/**
 *
 * Los metodos devuelven false si no hay error y 1 si hay algún error
 *
 * @author José Ángel García Salias, Antonio Lozano Arriaga>
 */


public class Parser {
    
    Yylex lex;
    TablaSimbolos tabla;
    
    
    Parser(Yylex yylex){
        this.lex = yylex;
        tabla = new TablaSimbolos();
    }
    
    public final char ID='i',PR = 'p',NAT='n', REAL ='r', 
                      CAR='c', MAS='+', MUL='*',
                      MENOS='-',DIV='/',IGUAL='=',
                      LPAR='(', RPAR=')', PYC=';',COMA=',';
    
    Token tok;
    
    void error(Token t){
        System.out.println("Error Sintáctico.........");
        System.out.println("Linea: " + t.line);
        System.out.println("Columna: " + t.column);
        System.out.println("Token: "+ t.lex);
    }
    void avanzar() throws IOException{
        tok = lex.yylex();
        tabla.add(tok);
        tok.imp();
    }
    
    boolean consumir(char t) throws IOException{
        if(t==tok.atomo) {
            avanzar();
            return false;
        }else{
            error(tok);
            return true;
        }
    }


    public void parse() throws IOException{
        avanzar();
        program();
        consumir('e');
        tabla.listar();
    }
    

    public boolean program() throws IOException{
        System.out.println("program()");
        boolean error = false;
        if(!error) error = decl();
        if(!error) error = sent();
        return error;
    }
    /**
     * decl -> tipo var; declp
     * @throws IOException
     */
    public boolean decl() throws IOException{
        System.out.println("decl()");
        boolean error = false;
        error  = tipo();
        if(!error) error = var();
        if(!error) error = consumir(PYC);
        if(!error) error = declp();
        return error;
    }

    /**
     * declp -> tipo var; declp | e
     * @throws IOException
     */
    public boolean declp() throws IOException{
        System.out.println("declp()");
        boolean error = false;
        error = tipo();
        if(!error){
            error = var();
            if(!error)
                error = consumir(PYC);
            if(!error)
                error = declp();
        }else{
            error = false;
        }
        return error;
    }

    /**
     * tipo -> int|float|double|char
     * @throws java.io.IOException
     */
    public boolean tipo() throws IOException{
        System.out.println("tipo()");
        boolean error = false;
        switch(tok.atomo){
            case PR:
                avanzar();
                break;
            default:
                error(tok);
                error = true;
                break;
        }
        return error;
    }


    /**
     * var -> init varp
     * @throws IOException
     */
    public boolean var() throws IOException{
        System.out.println("var()");
        boolean error = false;
        error= init();
        if(!error)
            error = varp();
        return error;
    }

    /**
     * varp-> ,init varp | e
     * @throws IOException
     */
    public boolean  varp() throws IOException{
        System.out.println("varp()");
        boolean error=false;
        while(COMA==tok.atomo){
            avanzar();
            error = init();
            if(!error)
                error = varp();
        }
        return error;
    }
    /**
     * init-> id initp
     * @throws IOException
     */
    public boolean init() throws IOException{
        System.out.println("init()");
        boolean error=false;
        error = consumir(ID);
        if(!error)
            error =initp();
        return error;
    }

    /**
     * initp -> := exp | e
     * @throws IOException
     */
    public boolean initp() throws IOException{
        System.out.println("intp()");
        boolean error=false;
        while(IGUAL==tok.atomo){
            avanzar();
            if(!error)
            error = exp();
        }
        return error;
    }

    /**
     * sent-> exp ; sentp
     * @throws IOException
     */
    public boolean sent() throws IOException{
        System.out.println("sent()");
        boolean error = false;
        error = exp();
        if(!error)
            error = consumir(PYC);
        if(!error)
            sentp();
        return error;
    }

    /**
     * sentp -> exp; sentp | e
     * @throws IOException
     */
    public boolean sentp() throws IOException{
        System.out.println("sentp()");
        boolean error = false;
        error = exp();
        if(!error){ // si el primero no es correcto, pues todo lo demás tampoco y asumimos es epsilon
            error = consumir(PYC);
            if(!error)
                error=sentp();
        }else{
            error = false; //entonces tomamos epsilon
        }
        return error;
    }

    /**
     * exp -> term expp
     * @throws IOException
     */
    public boolean  exp() throws IOException{
        System.out.println("exp()");
        boolean error = false;
        error = term();
        if (!error)
            error = expp();
        return error;
    }

    /**
     * expp -> + term expp | - term expp | e
     * @throws IOException
     */
    public boolean expp() throws IOException{
        System.out.println("expp()");
        boolean error = false;
        switch (tok.atomo){
            case MAS:
            case MENOS:
                avanzar();
                error = term();
                if(!error)
                    error = expp();
                break;

        }

        return error;
    }

    /**
     * term -> factor termp
     * @throws IOException
     */
    public boolean term() throws IOException{
        System.out.println("term()");
        boolean error=false;
        error = factor();
        if(!error)
         error = termp();
        return error;
    }

    /**
     * termp -> * factor termp | / factor termp | e
     * @throws IOException
     */
    public boolean termp() throws IOException{
        System.out.println("termp()");
        boolean error=false;
        switch (tok.atomo){
            case MUL:
                avanzar();
                error = factor();
                if(!error)
                    error = termp();
                break;
            case DIV:
                avanzar();
                error = factor();
                if(!error)
                    error = termp();
                break;
            default:
                break;
        }// si no entra, es epsilon y el error es false;
        return error;
    }

    /**
     * @throws java.io.IOException
     */
    public boolean factor() throws IOException{
        System.out.println("Factor()");
        boolean error=false;

        switch(tok.atomo){
            case ID:
                avanzar();
                break;
            case LPAR:
                avanzar();
                error = exp();
                if(!error)
                    error = consumir(RPAR);
                break;
            case NAT:
                avanzar();
                break;
            case REAL:
                avanzar();
                break;
            case CAR:
                avanzar();
                break;
            default:
                error(tok);
                error = true;
                break;
        }
        return error;
    }
}
