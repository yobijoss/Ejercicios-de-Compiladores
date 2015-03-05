package objetos;

public class Simbolo{

	public int id;
	public String val;
	public int id_tipo; 
	public int num_param;
	public int lista_param;
	public int direccion;

	public Simbolo(int id, String val, int id_tipo, int num_param, int lista_param, int direccion){
		this.id = id;
		this.val = val;
		this.id_tipo = id_tipo;
		this.num_param = num_param;
		this.lista_param = lista_param;
		this.direccion = direccion;
	}

	public void imprimir(){
		System.out.println("id: "+this.id+" val: "+this.val+" id_tipo: "+this.id_tipo+" num_param: "+this.num_param+" lista_param: "+this.lista_param
			+" direccion: "+this.direccion);
	}
}