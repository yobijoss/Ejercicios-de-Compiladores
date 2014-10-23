package com.compiladores;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File f=null;
        FileReader fr=null;
        Yylex lex;
        Parser parser;
        try {
            f = new File("programa.txt");
            fr = new FileReader(f);
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontro");
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
