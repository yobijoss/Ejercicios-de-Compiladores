package mx.unam.compiladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Antonio Lozano Arriaga on 01/09/14.
 *
 * Clase que lee un archivo y devuelve una arreglo de caracteres que estaba en el archivo
 */
public class LeerCaracteres {

    /**
     * Lee un archivo y devuelve sus caracteres en un arreglo
     * @return caracteres
     */
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
