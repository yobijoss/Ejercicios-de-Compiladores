package mx.unam.compiladores;

import mx.unam.compiladores.estructuras.Token;

/**
 * Created by José Ángel García Salinas and Antonio Lozano Arriaga on 01/09/14.
 *
 * Clase que obtiene tokens y las clases de los token obtenidos al leer un archivo.
 */

public class AnalizadorSuperFeliz {

    private int estado_actual = 0;
    private int cont = 0;
    private Automata automata;
    private char[] caracteres;
    private Character c;

    /**
     * Constructor que inicializa un automata y obtiene un arreglo de caracteres
     * resultado de leet un archivo
     */
    public AnalizadorSuperFeliz(String path){
        automata = new Automata();
        caracteres = new LeerCaracteres().readFile(path);
        c=getC();
    }


    /**
     * Devuelve el siguiente token
     * @return token
     */
    public Token getSigToken(){
        Token token ;
        String buffer="";
        int edoSig=0;
        int edoAnt=0;
        int tipo;

        while(estado_actual != 14) {
            edoAnt = estado_actual;
            edoSig = automata.getEdoSiguiente(c, estado_actual);
            //System.out.print("\ncaracter: "+c+" Edoactual: "+ estado_actual + "edo sig :"+ edoSig);

            if (automata.esAceptacion(edoSig) || (edoAnt ==0 && edoSig == 14) || edoSig==4) {
                buffer += c;
                c = getC();
            }
            estado_actual = edoSig;

            if (c == '$') {
                return null;
            }

            if (estado_actual == -1) {
                System.out.println("\nerror con el caracter :" + c);
                return null;
            }
        }

        estado_actual = 0;

        tipo = getClaseToken(edoAnt);
        if(tipo == -1 ){
            return null;
        }
        token = new Token(String.valueOf(tipo),buffer);
        //token.imprime();
        if(TablaSimbolos.listaSimbolos.buscar(token)==null && tipo!=7)
             TablaSimbolos.listaSimbolos.insertarAlFinal(token);
        return token;
    }

    /**
     * Devuelve la clase que pertenece el token dependiendo del estado de aceptacion
     * @param estado
     * @return número de clase
     */
    public static int getClaseToken(int i){
        switch (i){
            case 13:
                return 0;
            case 1:
                return 1;
            case 2:
                return 5;
            case 3:
                return 2;
            case 5:
                return 4;
            case 6:
                return 3;
            case 7:
                return 1;
            case 8:
                return 1;
            case 9:
                return 6;
            case 10:
                return 0;
            case 11:
                return 1;
            case 12:
                return 1;
            case 14:
                return 7;
            case 0:
                return 7;
        }

        return -1;
    }

    /**
     * Devuelve el siguiente caracter del arreglo o '$' si ya leyó todo el archivo
     * @return caracter
     */
    public Character getC(){
        return ( (cont<caracteres.length) ? caracteres[cont++]:'$');
    }
}
