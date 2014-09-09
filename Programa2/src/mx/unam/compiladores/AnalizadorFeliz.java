package mx.unam.compiladores;

import mx.unam.compiladores.estructuras.Token;



public class AnalizadorFeliz  {

    private int estado_actual = 0;
    private int cont = 0;
    private Automata automata;
    private char[] caracteres;
    private Character c;

    public AnalizadorFeliz(){
        automata = new Automata();
        caracteres = new LeerCaracteres().readFile();
        c=getC();
    }



    public Token getSigToken(){
        Token token ;
        String buffer="";
        int edoSig=0;
        int edoAnt=0;
        int tipo;

        while(estado_actual != 14) {
            edoAnt = estado_actual;
            edoSig = automata.getEdoSiguiente(c, estado_actual);
            System.out.print("\ncaracter: "+c+" Edoactual: "+ estado_actual + "edo sig :"+ edoSig);

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
        token.imprime();
        TablaSimbolos.listaSimbolos.insertarAlFinal(token);




        return token;
    }


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

    public Character getC(){
        return ( (cont<caracteres.length) ? caracteres[cont++]:'$');
    }
}
