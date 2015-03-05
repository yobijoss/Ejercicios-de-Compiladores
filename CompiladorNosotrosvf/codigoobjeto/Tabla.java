/*
 * @author 
    García Salinas Jose Angel
    Hernandez Salazar Sofía Priscila
    Lozano Arriaga Antonio de Jesús
    Padilla Valencia Noemi
    Perez Castillo Mauricio
 */

//package calculadora;

import java.util.ArrayList;


public class Tabla {
    ArrayList<TokenTabla> columna;
    
    public void addKeyWords(){
        columna.add(new TokenTabla("int","INT",0, "KW"));
        columna.add(new TokenTabla("float","FLOAT",0, "KW"));
        columna.add(new TokenTabla("double","DOUBLE",0, "KW"));
        columna.add(new TokenTabla("complex","COMPLEX",0, "KW"));
        columna.add(new TokenTabla("char","CHAR",0, "KW"));
        columna.add(new TokenTabla("string","STRING",0, "KW"));
    }
    
    public boolean add(String id, String type, int dir, String typeVar){
        TokenTabla token = new TokenTabla(id,type,dir,typeVar);
        for(TokenTabla tb: columna){
            if(tb.equals(token))
                return false;
        }
        columna.add(token);
        return true;
    }
    
    public boolean add(String id, String type, int dir){
        TokenTabla token = new TokenTabla(id,type,dir,"cad");
        for(TokenTabla tb: columna){
            if(tb.equals(token))
                return false;
        }
        columna.add(token);
        return true;
    }
    
    public int getDir(String id){
        for(TokenTabla tt: columna){
            if(tt.lex.equals(id)){
                return tt.dir;
            }
        }
        return -1;
    }
    
    public String getType(String id){
        for(TokenTabla tt: columna){
            if(tt.lex.equals(id)){
                return tt.type;
            }
        }
        return null;
    }
    
    public boolean search(String id){
        for(TokenTabla tt: columna){
            if(tt.lex.equals(id)){
                return true;
            }
        }
        return false;
    }
    
    public Tabla(){
        columna = new ArrayList();
    }
}
