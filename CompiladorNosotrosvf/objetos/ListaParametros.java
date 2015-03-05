package objetos;

import java.util.*;

public class ListaParametros{

	public ArrayList<Parametro> lista;
	public int id;
	public int tam;

	
	public ListaParametros(int id){
		lista = new ArrayList<Parametro>();
		this.id = id;
		this.tam = 0;
	}

	public void add( String val, int id_tipo,int tam){
		lista.add(new Parametro(val,id_tipo,tam));
		this.tam++;
	}
	public void add(Parametro parametro){
		lista.add(parametro);
		this.tam++;
	}

	public boolean exists(String val){
		Iterator<Parametro> iter = lista.iterator();
		Parametro temp;
		while(iter.hasNext()){
			temp = iter.next();
			if(val.equals(temp.val)){
				return true;
			}
		}
		return false;
	}

	public void imprimir(){
		Iterator<Parametro> iter = lista.iterator();
		Parametro temp;
		while(iter.hasNext()){
			temp = iter.next();
			temp.imprimir();
		}
	}
}