package mx.unam.compiladores.estructuras;

class Nodo<Algo>{
	private Algo dato;
	private Nodo<Algo> sig;
    
    public Nodo(){}

	public Nodo(Algo dato, Nodo sig){
		this.dato=dato;
		this.sig = sig;
	}

	public Nodo getSig(){
		return sig;
	}
	public Algo getDato(){
		return dato;
	}
	public void setDato(Algo info){
		dato = info;
	}
	public void setSig(Nodo algo){
		sig = algo;
	}

}