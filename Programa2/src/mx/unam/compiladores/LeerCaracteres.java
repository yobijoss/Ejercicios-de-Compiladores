package mx.unam.compiladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Antonio on 02/09/2014.
 */
public class LeerCaracteres {

        public char[] readFile(){ // metodo para leer el archivo
            File f = new File( "assets/ProgramaPrueba.txt" );
            BufferedReader entrada;
            String texto = "";

            try {

                entrada = new BufferedReader( new FileReader( f ) );
                String linea ="";
                while((linea = entrada.readLine())!=null){
                    texto = texto + linea;
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
            return texto.toCharArray();
        }

}
