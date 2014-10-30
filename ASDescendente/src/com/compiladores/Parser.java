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
 * Los metodos devuelven false si no hay error y 1 si hay algún error.
 * @author José Ángel García Salias, Antonio Lozano Arriaga
 */


public class Parser {
    
    Yylex lex;
    TablaSimbolos tabla;

    /**
     * Constructor que incializa el analizador léxico y la tabla de simbolos.
     * @param yylex
     */
    Parser(Yylex yylex){
        this.lex = yylex;
        tabla = new TablaSimbolos();
    }
    
    public final char ID='i',PR = 'p',NAT='n', REAL ='r', 
                      CAR='c', MAS='+', MUL='*',
                      MENOS='-',DIV='/',IGUAL='=',
                      LPAR='(', RPAR=')', PYC=';',COMA=',';
    
    Token tok;

    /**
     * Método que imprime el error en un token
     * @param t token de entrada en el cual se encontró el erro
     */
    void error(Token t){
        System.out.println("Error Sintáctico.........");
        System.out.println("Linea: " + t.line);
        System.out.println("Columna: " + t.column);
        System.out.println("Token: "+ t.lex);
    }

    /**
     * Método que devuelve el siguiente token para su procesamiento.
     * @throws IOException
     */
    void avanzar() throws IOException{
        tok = lex.yylex();
        tabla.add(tok);
        tok.imp();
    }

    /**
     * Método que consume el léxema de un caracter
     * @param t lexema del token que estás consumiendo
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
     */
    boolean consumir(char t) throws IOException{
        if(t==tok.atomo) {
            avanzar();
            return false;
        }else{
            error(tok);
            return true;
        }
    }

    /**
     * La funcion parse inicia el analizador léxico, llama a la primera producción que es program y avanza
     * para obtener el primer token
     * @throws IOException 

     */
    public void parse() throws IOException{
        avanzar();
        program();
        consumir('e');
        tabla.listar();
    }

    /**
     * Describe este método la siguiente producción
     * program -> decl sent
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
     */
    public boolean program() throws IOException{
        System.out.println("program()");
        boolean error = false;
        if(!error) error = decl();
        if(!error) error = sent();
        return error;
    }

    /**
     * Describe este método la siguiente producción
     * decl -> tipo var; declp
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * declp -> tipo var; declp | e
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * tipo -> int|float|double|char
     * @throws java.io.IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * var -> init varp
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * varp-> ,init varp | e
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * init-> id initp
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * initp -> := exp | e
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * sent-> exp ; sentp
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * sentp -> exp; sentp | e
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * exp -> term expp
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * expp -> + term expp | - term expp | e
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * term -> factor termp
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * termp -> * factor termp | / factor termp | e
     * @throws IOException
     * @return error : true si la producción genera un error, false si no
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
     * Describe este método la siguiente producción
     * factor-> id|(exp)|entero| flotante | caracter | doble
     * @throws java.io.IOException
     * @return error : true si la producción genera un error, false si no
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
