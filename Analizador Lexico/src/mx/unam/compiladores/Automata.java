package mx.unam.compiladores;

/**
 * Created by José Ángel García Salinas and Antonio Lozano Arriaga on 01/09/14.
 * Clase que genera un automata con el cual podemos obtener el estado siguiente
 *
 */
public class Automata  {

    private char ESPACIO[] = {' ','\n','\t','\r'};
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

    public static int[][] tabla_estados={

            {1,8,1,1,7,1,5,4,2,6,3,3,14},
            {1,1,1,1,1,1,14,-1,1,14,14,14,14},
            {-1,-1,-1,-1,-1,-1,14,4,2,14,14,14,14},
            {14,14,14,14,14,14,14,14,14,14,14,14,14},
            {-1,-1,-1,-1,-1,-1,-1,-1,9,-1,-1,-1,-1},
            {14,14,14,14,14,14,-1,14,14,-1,14,-1,14},
            {14,14,14,14,14,14,-1,14,14,-1,14,-1,14},
            {1,1,1,1,1,10,14,-1,1,14,14,14,14},
            {1,1,11,1,1,1,14,-1,1,14,14,14,14},
            {-1,-1,-1,-1,-1,-1,14,-1,9,14,14,14,14},
            {1,1,1,1,1,1,14,-1,1,14,14,14,14},
            {1,1,1,12,1,1,14,-1,1,14,14,14,14},
            {1,13,1,1,1,1,14,-1,1,14,14,14,14},
            {1,1,1,1,1,1,14,-1,1,-1,14,14,14},
            {0,0,0,0,0,0,0,0,0,0,0,0,0}

    };


    /**
     * Método que devuelve el estado siguiente al ingresar un estado actual
     * y un caracter
     * @param ctr
     * @param estado
     * @return estado siguiente
     */
    public int getEdoSiguiente(char ctr, int estado){
        int edo_siguiente = -1;

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
        if(compararArreglo(ctr, this.ESPACIO) && edo_siguiente ==-1)
            edo_siguiente = Automata.tabla_estados[estado][12];
        return edo_siguiente;
    }

    /**
     * Devuelve true or false dependiendo si el caracter ent está en el arreglo
     * @param ent
     * @param arreglo
     * @return estaEnArreglo
     */
    private boolean compararArreglo(char ent,char[] arreglo){
        for(char item :arreglo ){
            if(item == ent) {
                //System.out.print("El caracter es "+ent);
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve true or false dependiendo si los caracteres son iguales.
     * @param ent
     * @param comparar
     * @return esIgual
     */
    private boolean compararCaracter(char ent,char comparar){
        return ent == comparar;
    }

    /**
     * Devuelve true or false dependiendo si un estado es de aceptación
     * @param edoSig
     * @return esDeAceptación
     */
    public boolean esAceptacion(int edoSig) {
        int arreglo[] = {1,2,3,5,6,7,8,9,10,11,12,13};
        for(int item : arreglo ){
            if(item == edoSig) {
                return true;
            }
        }
        return false;
    }
}
