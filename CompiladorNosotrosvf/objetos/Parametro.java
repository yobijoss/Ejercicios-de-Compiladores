package objetos;

public class Parametro{

	public String val;
	public int id_tipo;
	public int tam;

	public Parametro( String val, int id_tipo,int tam){
		this.val= val;
		this.id_tipo = id_tipo;
		this.tam = tam;
	}

	public void imprimir(){
		System.out.println("<"+this.val+" "+this.id_tipo+" "+this.tam+">");
	}
}