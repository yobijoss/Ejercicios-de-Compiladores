package com.compiladores.estructuras;

/**
 * Created by José Ángel García Salinas and Antonio Lozano Arriaga on 01/09/14.
 *
 * La clase nodo define un objeto que tiene como varables:
 * Nodo sig: la referencia al nodo que está enlazado por la derecha;
 * Nodo ant: la referencia al nodo que está enlazado por la izquierda;
 *
 * @param <Te> Objeto que corresponde al dato que guardará nuestro nodo;

 */

class NodoDE<Te>{

    private Te dato;
    private NodoDE<Te> sig;
    private NodoDE<Te> ant;

    /**
     * Construcor donde creamos un nuevo nodo.
     * @param ant Nodo anterior
     * @param dato dato a almacenar
     * @param sig Nodo siguiente
     */
    public NodoDE(NodoDE<Te> ant, Te dato, NodoDE<Te> sig){
        this.dato = dato;
        this.ant = ant;
        this.sig = sig;
    }

    /**
     * Metodo que devuelve el nodo siguiente
     * @return Nodo sig
     */
    public NodoDE<Te> getSig(){
        return sig;
    }

    /**
     * Metodo que devuelve el nodo anterior
     * @return Nodo ant
     */
    public NodoDE<Te> getAnt(){
        return ant;
    }

    /**
     * Método que devuelve el dato almacenado
     * @return Te dato
     */
    public Te getDato(){
        return dato;
    }

    /**
     * Metodo que define el nodo siguiente
     * @param sig
     */
    public void setSig(NodoDE<Te> sig){
        this.sig = sig;
    }

    /**
     * Metodo que define el nodo anterior
     * @param ant
     */
    public void setAnt(NodoDE<Te> ant){
        this.ant = ant;
    }

    /**
     * Metodo que define el dato
     * @param dato
     */
    public void setDato(Te dato){
        this.dato = dato;
    }
}