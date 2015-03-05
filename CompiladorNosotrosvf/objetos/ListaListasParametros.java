package objetos;

import java.util.*;

public class ListaListasParametros{

	public  ArrayList<ListaParametros> lista;
	public int tam;

	public ListaListasParametros(){
		this.lista = new ArrayList<ListaParametros>();
		this.tam = 0;
	}

	public void add(ListaParametros lista){
		this.lista.add(lista);
		this.tam++;
	}

	public ListaParametros getListaParamsbyId(int id){
		return lista.get(id);
	}


}