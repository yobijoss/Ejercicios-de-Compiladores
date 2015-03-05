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
public class TokenTabla extends Token{
    int dir;
    String typeVar;
    
    TokenTabla(String lex, String type, int dir, String typeVar)
    {
        super(lex, type);
        this.dir = dir;
        this.typeVar = typeVar;
    }
    
    public boolean equals(String lex, String type, int dir, String typeVar){
        if(super.equals(lex, type))
            if(this.dir== dir && this.typeVar.equals(typeVar))
                return true;
        return false;
    }
    
    public boolean equals(TokenTabla tt){
        if(super.equals(tt.lex, tt.type))
            if(this.dir== tt.dir && this.typeVar.equals(tt.typeVar))
                return true ;
        return false;
    }
    
}
