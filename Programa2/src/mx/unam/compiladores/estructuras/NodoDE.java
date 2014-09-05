package mx.unam.compiladores.estructuras;

/**
 * Created by jagspage2013 on 01/09/14.
 */

class NodoDE<Te>{
    private Te dato;
    private NodoDE<Te> sig;
    private NodoDE<Te> ant;

    public NodoDE(NodoDE<Te> ant, Te dato, NodoDE<Te> sig){
        this.dato = dato;
        this.ant = ant;
        this.sig = sig;
    }

    public NodoDE<Te> getSig(){
        return sig;
    }

    public NodoDE<Te> getAnt(){
        return ant;
    }

    public Te getDato(){
        return dato;
    }

    public void setSig(NodoDE<Te> sig){
        this.sig = sig;
    }

    public void setAnt(NodoDE<Te> ant){
        this.ant = ant;
    }

    public void setDato(Te dato){
        this.dato = dato;
    }
}