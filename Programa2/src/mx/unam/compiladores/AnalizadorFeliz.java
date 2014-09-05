package mx.unam.compiladores;

/**
 * Created by jagspage2013 on 04/09/14.
 *
 * Clase que  maneja metodos para saber si un token es igual a:
 *
 * letra e
 * letra i
 * digito
 * simbolo
 * =
 * operador
 */
public class AnalizadorFeliz  {

    private char ESPACIO = ' ';
    private char DIGITOS[]={'0','1','2','3','4','5','6','7','8','9'};
    private char PUNTO = '.';
    private char IGUAL= '=';
    private char ELSE[] = {'e','l','s'};
    private char IF[] = {'i','f'};
    private char P_ABRE= '(';
    private char OPERADORES[]={'+','-','*','/'};
    private char CARACTER_ESPECIAL[]={')',',',';'};
    private char LETRAS[]= {
        'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
        'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
    };


    /**
     *
     * @param ctr
     * @param estado
     * @return
     */
    public int AnalizarCaracter(char ctr, int estado){
        int edo_siguiente = -1;

        //else
        if(compararCaracter(ctr,this.ELSE[0]) && edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][1];
        if(compararCaracter(ctr,this.ELSE[1])&& edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][2];
        if(compararCaracter(ctr,this.ELSE[2])&& edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][3];


        if(compararCaracter(ctr,this.IF[0])&& edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][4];
        if(compararCaracter(ctr,this.IF[1])&& edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][5];


        if(compararArreglo(ctr,this.LETRAS)&& edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][0];
        if(compararArreglo(ctr,this.DIGITOS)&& edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][8];
        if(compararArreglo(ctr,this.OPERADORES)&& edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][6];
        if(compararArreglo(ctr,this.CARACTER_ESPECIAL)&& edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][11];
        if(compararCaracter(ctr,this.IGUAL)&& edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][9];
        if(compararCaracter(ctr,this.P_ABRE)&& edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][10];
        if(compararCaracter(ctr,this.PUNTO)&& edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][7];
        if(compararCaracter(ctr,this.ESPACIO) && edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][12];

       return edo_siguiente;
    }

    /**
     *
     * @param ent
     * @param arreglo
     * @return
     */
    private boolean compararArreglo(char ent,char[] arreglo){
        for(char item :arreglo ){
            if(item == ent)
                return true;
        }
        return false;
    }

    /**
     *
     * @param ent
     * @param comparar
     * @return
     */
    private boolean compararCaracter(char ent,char comparar){
        return ent == comparar;
    }


}
