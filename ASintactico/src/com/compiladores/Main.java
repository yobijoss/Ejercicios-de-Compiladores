package com.compiladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * Created by José Ángel García Salinas & Antonio Lozano Arriaga on 23/10/14.
 */

public class Main {

    /**
     *Clase la cual lee un archivo con el programa y se encuentra en la raiz de esta carpera
     *después utilizando el analizador léxico con Yylex lo pasa como parametro a
     *nuestro parser recursivo.
     * Finalmente iniciamos el parseo
     *
     * @param args
     */
    public static void main(String[] args) {
        File f=null;
        FileReader fr=null;
        Yylex lex;
        Parser parser;
        try {
            f = new File("programa.txt");
            fr = new FileReader(f);
        } catch (FileNotFoundException ex) {

        }

        if(fr == null){
            try {
                f = new File(args[0]);
                fr = new FileReader(f);
            } catch (FileNotFoundException ex) {
                System.out.println("No se encontro");

            }

        }
        if(fr==null){
            System.out.println("No se pudo abrir archivo");
        }else{
            lex = new Yylex(fr);
            parser = new Parser(lex);

            int tok;
            try {
                parser.parse();

            }catch (IOException E){

            }

        }
        System.out.println("Analizis Finalizado");
    }

}
