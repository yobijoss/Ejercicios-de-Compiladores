package mx.unam.compiladores.estructuras;
/**
 * * Created by José Ángel García Salinas and Antonio Lozano Arriaga on 01/09/14.
 * Clase que define una lista doblemente enlazada por que YOLO
 * @param <Te> Tipo de dato del objeto que almacenará la lista
 */
public class ListaDE<Te>  {

	private NodoDE<Te> H;
	private NodoDE<Te> T;

    /**
     * Constructor donde definimos como nulos nuestros nodos inicial y final
     */
	public ListaDE(){
		H=T=null;
	}

    /**
     * Método que inserta un valor al inicio de la lista
     * @param nombre
     */
	public void insertarAlInicio(Te nombre){
		NodoDE<Te> q = new NodoDE<Te>(null,nombre,H);
		if(!vacio())
			H.setAnt(q);
		else	
			T=q;
		H=q;
	}

    /**
     * Método que inserta un valor al final de la lista;
     * @param nombre
     */
	public void insertarAlFinal(Te nombre){
		NodoDE<Te> q = new NodoDE<Te>(T,nombre,null);
		if(!vacio())
			T.setSig(q);
		else	
			H=q;
		T=q;
	}

    /**
     * Método que busca un Nodo y devuelve su referencia
     * @param nombre
     * @return Nodo donde su dato es nombre
     */
	public NodoDE<Te> buscar(Te nombre){
		NodoDE<Te> q;
		q=H;
		if(T==null)
			return null;
		while(q!=null){
			if(q.getDato().equals(nombre))
				return q;
			q=q.getSig();
		}
		return null;
	}

    /**
     * Inserta nodo entre un dos nodos después del nodo con nombre anterior
     * @param nombrenuevo
     * @param anterior
     */
	public void insertarNodo(Te nombrenuevo, NodoDE<Te> anterior){
		NodoDE<Te> nuevo = new NodoDE<Te>(anterior,nombrenuevo,anterior.getSig());
		if(anterior.getSig()==null)
			T=nuevo;
		else
			anterior.getSig().setAnt(nuevo);
		anterior.setSig(nuevo);
	}

    /**
     * Método que inserta un nodo después de algun nodo con cierto nombre
     * @param nombre
     * @param nombrenuevo
     */
	public void InsertaDespues(Te nombre, Te nombrenuevo){
		NodoDE<Te> q = buscar(nombre);
		if(q!=null)
			insertarNodo(nombrenuevo,q);
		else
			System.out.println("[!]No existe el nombre : "+nombre);
	}

    /**
     * Método que inserta un nodo antes de algun nodo con cierto nombre
     * @param nombre
     * @param nombrenuevo
     */
	public void InsertaAntes(Te nombre, Te nombrenuevo){
		NodoDE<Te> q = buscar(nombre);
		if(q==null)
			System.out.println("[!]No existe el nombre " + nombre);
		else if(q.getAnt()==null)
			insertarAlInicio(nombrenuevo);
		else
			insertarNodo(nombrenuevo,q.getAnt());
	}

    /**
     * Lista losdatos dentro de los nodos en la lista
     */
	public void listar(){
		NodoDE<Te> q = H;
		if(q==null){
			System.out.println("\n[!]No hay datos\n");
		}
		while(q!=null){
			System.out.println(q.getDato());
			q=q.getSig();
		}
	}

    /**
     * Actualiza el dato de algún nodo dentro de la lista
     * @param nombre
     * @param nombrenuevo
     */
	public void actualizar(Te nombre,Te nombrenuevo){
		NodoDE<Te> q = buscar(nombre);
		if(q != null)
			q.setDato(nombrenuevo);//Cambiar si cambia el tipo de dato
		else
			System.out.println("[!] No existe el dato");
	}

    /**
     * Indíca si la lista esta vacio
     * @return estaLleno
     */
	public boolean vacio(){
		return (T==null);
	}

    /**
     * borra un nodo de la lista
     * @param q
     * @return dato que tenía el nodo
     */
	public Te borrar(NodoDE<Te> q){
		Te aux;
		NodoDE<Te> q1;
		if (H == T) {
			aux=H.getDato();
			H=T=null;
		}
		else if(q == null){
			aux=H.getDato();
			H=H.getSig();
		}else{
			q1=q.getSig();
			aux=q1.getDato();
			q.setSig(q.getSig().getSig());
			if (q.getSig()==null) {
				T=q;
			}
		}
		return aux;
	}

    /**
     * devuelve el tamaño de la lista
     * @return tamaño lista
     */
    public int size(){
        int i = 0;
        NodoDE<Te> q = H;
        if(q==null){
            return i;
        }
        while(q!=null){
            System.out.println(q.getDato());
            q=q.getSig();
            i++;
        }
        return i;
    }

    /**
     * devuelve una cadena con todos los datos dentro de los nodos
     * @return cadena
     */
    @Override
    public String toString() {
        String cadena ="";

        NodoDE<Te> q = H;
        if(q!=null){
            while(q!=null){
                System.out.println(q.getDato());
                cadena +=   String.valueOf(q.getDato());
                q=q.getSig();
            }
        }
        return cadena;
    }
}