package objetos;
import java.util.*;

public class ListaTipos{

	public static ArrayList<Tipo> lista;

	public ListaTipos(){
		this.lista = new ArrayList<Tipo>();
		init();
	}

	public void add(int id, String val, int tam){
		lista.add(new Tipo(id,val,tam));
	}

	public void init(){
		add(0,"void",0);
		add(1,"char",1);
		add(2,"int",2);
		add(3,"float",4);
		add(4,"double",8);
	}

	public static Tipo getIdByTipo(String tipo){
		int id; 
		Tipo tipo_;
		Iterator<Tipo> iter = lista.iterator();
		while(iter.hasNext()){
			tipo_ = iter.next();
			if(tipo_.val.equals(tipo)){
				return tipo_;
			}
		}
		return null;
	}

}