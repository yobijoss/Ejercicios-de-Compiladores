
//package calculadora;

import java.util.ArrayList;

/*
 * @author 
    García Salinas Jose Angel
    Hernandez Salazar Sofía Pricila
    Lozano Arriaga Antonio de Jesús
    Padilla Valencia Noemi
    Perez Castillo Mauricio
 */

public class CodInter{
    
    ArrayList<CodigoCuadrupla> cod;
    Integer entero;
    Double doble;
    Float flotante;
    Character caracter;

    
    CodInter(){
        cod = new ArrayList();
    }
    
    void agregar(String operador, String operando1, String operando2, String resultado){
        cod.add(new CodigoCuadrupla(operador,operando1, operando2, resultado));        
    }
    
   CodigoCuadrupla obtener(int entero){
        this.entero.intValue();
        doble.doubleValue();
        flotante.floatValue();
        return cod.get(entero);
    }
    

    void agregar(CodigoCuadrupla caracter) {
        cod.add(caracter);
    }
    
}
