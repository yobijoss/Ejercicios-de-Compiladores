package mx.unam.compiladores.estructuras;

import java.io.Serializable;
import java.util.Collection;

public class ListaDE<Te>  {

	private NodoDE<Te> H;
	private NodoDE<Te> T;

	public ListaDE(){
		H=T=null;
	}

	public void InsertarAlInicio(Te nombre){
		NodoDE<Te> q = new NodoDE<Te>(null,nombre,H);
		if(!vacio())
			H.setAnt(q);
		else	
			T=q;
		H=q;
	}

	public void InsertarAlFinal(Te nombre){
		NodoDE<Te> q = new NodoDE<Te>(T,nombre,null);
		if(!vacio())
			T.setSig(q);
		else	
			H=q;
		T=q;
	}

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

	public void InsertarNodo(Te nombrenuevo, NodoDE<Te> anterior){
		NodoDE<Te> nuevo = new NodoDE<Te>(anterior,nombrenuevo,anterior.getSig());
		if(anterior.getSig()==null)
			T=nuevo;
		else
			anterior.getSig().setAnt(nuevo);
		anterior.setSig(nuevo);
	}

	public void InsertaDespues(Te nombre, Te nombrenuevo){
		NodoDE<Te> q = buscar(nombre);
		if(q!=null)
			InsertarNodo(nombrenuevo,q);
		else
			System.out.println("[!]No existe el nombre : "+nombre);
	}

	public void InsertaAntes(Te nombre, Te nombrenuevo){
		NodoDE<Te> q = buscar(nombre);
		if(q==null)
			System.out.println("[!]No existe el nombre " + nombre);
		else if(q.getAnt()==null)
			InsertarAlInicio(nombrenuevo);
		else
			InsertarNodo(nombrenuevo,q.getAnt());
	}

	public void listar(){
		NodoDE<Te> q = H;
		if(q==null){
			System.out.println("[!]No hay datos\n");
		}
		while(q!=null){
			System.out.println(q.getDato());
			q=q.getSig();
		}
	}

	public void actualizar(Te nombre,Te nombrenuevo){
		NodoDE<Te> q = buscar(nombre);
		if(q != null)
			q.setDato(nombrenuevo);//Cambiar si cambia el tipo de dato
		else
			System.out.println("[!] No existe el dato");
	}

	public boolean vacio(){
		return (T==null);
	}

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
}