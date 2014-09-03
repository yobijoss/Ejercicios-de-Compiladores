package mx.unam.compiladores.estructuras;

class PilaLSE <Te>{
	private Nodo<Te> tope;

	public static void main(String []args){
		System.out.println("\nPila");
		PilaLSE <Integer> pila = new PilaLSE<Integer>();
		pila.push(67);
		pila.push(12);
		pila.listar();
		pila.pop();
		pila.listar();
		System.out.println("La pila esta vacia?: "+ pila.vacio());
		pila.pop();
		pila.listar();
		System.out.println("La pila esta vacia?: "+ pila.vacio());
		if(!pila.vacio()){
			pila.pop();
		}else{
			System.out.println("La pila esta vacia no puedes borrar");
		}
	}

	public PilaLSE(){
		tope=null;
	}

	public boolean vacio(){
		return(tope==null);
	}

	public void push(Te dato){
		Nodo<Te> q = new Nodo<Te>(dato,tope);
		tope = q;
		System.out.println("Se inserto : "+ dato);
	}

	public Te pop(){
		Te aux = tope.getDato();
		tope = tope.getSig();
		System.out.println("Se borro un elemento");
		return aux;
	}

	public void listar(){
		Nodo<Te> q =tope;
		if(q==null){
			System.out.println("Pila vacia");
		}else{
			System.out.println("Pila actual:");
			while( q !=null){
				System.out.println("\n-----------\n    "+q.getDato());
				 q=q.getSig();
			}
			System.out.println("-----------");
		}
		System.out.println("-----------");
	}
}