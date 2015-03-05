package objetos;

import java.util.*;
public class ListaVariables{

	public ArrayList<Variable> lista;
	public int id;
	public int tam;
	public String nombre;

	public ListaVariables(String nombreFuncion){
		this.nombre = nombreFuncion;
		this.tam = 0;
		lista = new ArrayList<Variable>();
	}

	public void add(Variable var){
		this.lista.add(var);
		this.tam++;
	}

	public boolean exists(String nombre_var, String funcion){
		Iterator<Variable> iter = lista.iterator();
		Variable tmp;
		while(iter.hasNext()){
			tmp = iter.next();
			if(nombre_var.equals(tmp.val) && this.nombre.equals(funcion)){
				return true;
			}
		}
		return false;
	}



}