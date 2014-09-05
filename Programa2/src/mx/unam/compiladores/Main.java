package mx.unam.compiladores;

import mx.unam.compiladores.estructuras.ListaDE;
import mx.unam.compiladores.estructuras.Token;

public class Main {

    public static void main(String[] args) {
        int cls_token = -1;
        int estado_anterior =-1;
        int estado_actual = 0;

        LeerCaracteres lector = new LeerCaracteres();
        char[] caracteres = lector.readFile();
        TablaSimbolos tabla = new TablaSimbolos();

        ListaDE<Character> buffer = new ListaDE<Character>();

        AnalizadorFeliz analizadorFeliz = new AnalizadorFeliz();
        for(char i : caracteres){
            buffer.insertarAlFinal(i); i("\nCaracter a leer "+ i);
            estado_anterior = estado_actual;
            estado_actual = analizadorFeliz.AnalizarCaracter(i,estado_actual);

            if(estado_actual ==-1){
                System.out.print("Error en el Analizador");
                break;
            }
            if((cls_token =Automata.getClaseToken(estado_anterior))!=-1 && Automata.isEdo13(estado_actual)) {
                i("Era estado final");
                TablaSimbolos.listaSimbolos.insertarAlFinal(new Token(String.valueOf(cls_token),buffer.toString()));
                buffer = new ListaDE<Character>();

            }

        }
        TablaSimbolos.listaSimbolos.listar();
    }

    private static  void i(String i){
        System.out.println(i);
    }
}
