package objetos;

import java.util.ArrayList;
public class Tabla<T>{

	private ArrayList<T> lista;
	private String nombre;


	public Tabla(String nombre){
		lista = new ArrayList<T>();
		this.nombre = nombre;
	}

	public boolean exists(T objeto){
		return lista.contains(objeto);
	}

	public void add(T objeto){
		if(!lista.contains(objeto)){
			lista.add(objeto);
		}
	}

	public void printTable(){
		if(lista.size()>=1){
			System.out.println("Lista "+nombre);
			System.out.println("--------------------");
			for(T objeto : lista){
				objeto.toString();
			}
		}else{
			System.out.println("Lista "+nombre+" vacia");
		}
	}

	public T getLast(){
		return lista.get(lista.size()-1);
	}
}


