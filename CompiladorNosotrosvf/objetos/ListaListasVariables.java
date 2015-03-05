package objetos;

import java.util.*;

public class ListaListasVariables{

	public  ArrayList<ListaVariables> lista;
	public int tam;

	public ListaListasVariables(){
		this.lista = new ArrayList<ListaVariables>();
		this.tam = 0;
	}

	public void add(ListaVariables lista){
		this.lista.add(lista);
		this.tam++;
	}

	public ListaVariables getListaParamsbyId(int id){
		return lista.get(id);
	}

}