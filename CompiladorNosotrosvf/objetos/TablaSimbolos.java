package objetos;

import java.util.*;


public class TablaSimbolos{

	public ArrayList<Simbolo> lista;


	public TablaSimbolos(){
		lista = new ArrayList<Simbolo>();
	}

	public boolean add(int id, String val, int id_tipo, int num_param, int lista_param, int direccion){
		
		System.out.println("value= "+ val);

		if(buscarVal(val)==-1 && id_tipo!=-1){
			lista.add(new Simbolo(id,val,id_tipo,num_param,lista_param,direccion));
			//System.out.println("Simbolo Agregado: id= "+id+" val= "+val+" id_tipo= "+id_tipo+ " numero parametros= "+num_param+
								//" id lista de parametros= "+ lista_param+ " direccion= "+direccion);
			return true;
		}
		return false;
	}

	public int buscarVal(String val){
		Simbolo sym_;
		Iterator<Simbolo> iter = lista.iterator();
		while(iter.hasNext()){
			sym_ = iter.next();
			if(sym_.val.equals(val)){
				return sym_.id;
			}
		}
		return -1;
	}

	public int getParamsIdByVal(String val){
		Simbolo sym_;
		Iterator<Simbolo> iter = lista.iterator();
		while(iter.hasNext()){
			sym_ = iter.next();
			if(sym_.val.equals(val)){
				return sym_.lista_param;
			}
		}
		return -1;
	}

	public void imprime(){
		System.out.println("-------------------------");
		System.out.println("Tabla Simbolos");
		Iterator<Simbolo> iter = lista.iterator();
		Simbolo sym_;
		while(iter.hasNext()){
			sym_ = iter.next();
			sym_.imprimir();
		}
	}


}