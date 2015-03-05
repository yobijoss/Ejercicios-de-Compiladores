//package calculadora;

/*
 * @author 
    García Salinas Jose Angel
    Hernandez Salazar Sofía Pricila
    Lozano Arriaga Antonio de Jesús
    Padilla Valencia Noemi
    Perez Castillo Mauricio
 */
public class CodigoCuadrupla{
        private String operador; //Variables privadas para la cuadrupla
        private String operando1;
        private String operando2;
        private String resultado;
        
        CodigoCuadrupla(String operador, String operando1, String operando2, String resultado){ //Metodo que recibe los 4 this
            this.operador = operador;
            this.operando1 = operando1;
            this.operando2 = operando2;
            this.resultado = resultado;
        }

        public String obOperador() {
            return operador;
        }

        public void ponOperador(String operador) {
            this.operador = operador;
        }

        public String obOperando1() {
            return operando1;
        }

        public void ponOperando1(String operando1) {
            this.operando1 = operando1;
        }

        public String obOperando2() {
            return operando2;
        }

        public void ponOperando2(String operando2) {
            this.operando2 = operando2;
        }

        public String obResultado() {
            return resultado;
        }

        public void ponResultado(String resultado) {
            this.resultado = resultado;
        }
        
        public boolean equals(CodigoCuadrupla fin){
            if(this.obOperador().equals(fin.obOperador()))
                return true;
            return false;
        }
        
        public boolean equals(String fin){
            if(this.obOperador().equals(fin))
                return true;
            return false;
        }
    }
    
