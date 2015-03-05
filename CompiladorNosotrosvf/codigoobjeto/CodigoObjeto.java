
//package calculadora;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * @author 
    García Salinas Jose Angel
    Hernandez Salazar Sofía Priscila
    Lozano Arriaga Antonio de Jesús
    Padilla Valencia Noemi
    Perez Castillo Mauricio
 */

public class CodigoObjeto {
    
    private CodInter cod;
    ArrayList<CodigoCuadrupla> columna;
    private File txt;
    PrintWriter pw;
    private Tabla tabCad;
    String archivo;
    int dirvariable = 0;
    
    public CodigoObjeto(){
        columna = new ArrayList();
    }
    
    public CodigoObjeto(String archivo) throws IOException{
        cod= new CodInter();
        columna = new ArrayList();
        pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo)));
        
    }
    
    public CodigoObjeto(CodInter cod, String archivo) throws IOException{
        this.cod = cod;
        this.columna = new ArrayList();
        pw = new PrintWriter(new BufferedWriter(new FileWriter(archivo)));
    }

    public void traductor(CodigoCuadrupla c){
        
        // Cargar el valor de un registro y lo pasa a otro
        if(c.equals("load")){            
            pw.println("LD " + c.obResultado() + ", "+ c.obOperando1());
        }else  
         
        // operacion suma declaracion tipo 1 
        if(c.equals("+=")){
            pw.println("ADD "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else if(c.equals("+=F")){
            pw.println("ADDF "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else 
        // operacion resta  declaracion tipo 1   
        if(c.equals("-=")){
            pw.println("SUB "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else if(c.equals("-=F")){
            pw.println("SUBF "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else
            
        // operacion multiplicacion  declaracion tipo 1 
        if(c.equals("*=")){
            pw.println("MUL "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else if(c.equals("*=F")){
            pw.println("MULF "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else
            
        // operacion division   declaracion tipo 1 
        if(c.equals("/=")){
            pw.println("DIV "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else if(c.equals("/=F")){
            pw.println("DIVF "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else

        // operacion suma declaracion tipo 2
        if(c.equals("+")){
            pw.println("ADD "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else if(c.equals("+F")){
            pw.println("ADDF "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else
        // operacion resta  declaracion tipo 2  
        if(c.equals("-")){
            pw.println("SUB "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else if(c.equals("-F")){
            pw.println("SUBF "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else
            
        // operacion multiplicacion tipo 2
        if(c.equals("*")){
            pw.println("MUL "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else if(c.equals("*F")){
            pw.println("MULF "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else
    
        // operacion division tipo 2
        if(c.equals("/")){
            pw.println("DIV "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else if(c.equals("/F")){
            pw.println("DIVF "+ c.obResultado()+", "+c.obOperando1()+", "+c.obOperando2());
        }else

        //Operacion de almacenamiento

        if (c.equals("=")) {
            pw.println("ST"+c.obResultado()+" , "+c.obOperando1()+" , "+c.obOperando2());            
        }
    } // Llave traductor 
    public void traductorString(){
        for (TokenTabla tt : getTablaCadenas().columna){
            if(tt.type.equals("string"))
                pw.println("STR:"+ tt.lex);
            else
                pw.println("CHAR:"+tt.lex);
        }
    }
    
    public void traductorT() throws IOException{
        abrir();
        for(CodigoCuadrupla cuadru : cod.cod){
            traductor(cuadru);
        }
        traductorString();
        cerrar();
    }

    public CodInter getCi() {
        return cod;
    }

    public void ponCod(CodInter cod) {
        this.cod = cod;
    }

    public File obFile() {
        return txt;
    }

    public void ponFile(String archivo) {
        this.txt = new File(archivo);
    }
    
    
    public void abrir() throws IOException{
        pw = new PrintWriter(new BufferedWriter(new FileWriter(txt)));
    }
    
    public void cerrar(){
        pw.close();
    }

    public Tabla getTablaCadenas() {
        return tabCad;
    }

    public void setTablaCadenas(Tabla tabCad) {
        this.tabCad = tabCad;
    }
    
    public void iniciarDireccion(){
        dirvariable = cod.cod.size() + tabCad.columna.size();
    }
    
}
