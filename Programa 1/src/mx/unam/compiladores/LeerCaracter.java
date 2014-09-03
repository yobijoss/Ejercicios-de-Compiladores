
package mx.unam.compiladores;
/**
 * Created by Antonio on 02/09/2014.
 */
public class LeerCaracter {

        public void readFile(){ // metodo para leer el archivo
            File f = new File( "Codigo.txt" );
            BufferedReader entrada;
            try {
                entrada = new BufferedReader( new FileReader( f ) );
                String linea;
                while(entrada.ready()){
                    linea = entrada.readLine();
                    char [] arreglo; //Variable temporal para guardar el string
                    arreglo= linea.toCharArray(); //Transforma string to char array
                    for (int i=0; i<linea.length(); i++){ //Este ford solo demuestra que el metodo lo realiza
                        System.out.println(arreglo[i]);
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
/* Solo una cosa, si en el archivo no termina con un espacio va a juntar las cadenas ejemplo 150\nAntonio 150 y Antonio seran una misma cadena
pero si poner 150\n  Antonio ya se guardan las cadenas de forma separada, ya no supe como hacer eso sin darle un espacio al final de cada linea para evitar eso
Estas son las librerias que opcupe, segun tu no se necesitan poner pero aun asi las incluyo en la zona de comentarios
import java.util.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
*/
}
