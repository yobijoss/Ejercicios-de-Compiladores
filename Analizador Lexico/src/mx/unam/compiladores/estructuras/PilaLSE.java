class PilaLSE <Te>{
	private Nodo<Te> tope;

	public PilaLSE(){
		tope=null;
	}

	public boolean vacio(){
		return(tope==null);
	}

	public void push(Te dato){
		Nodo<Te> q = new Nodo<Te>(dato,tope);
		tope = q;
	}

	public Te pop(){
		Te aux = tope.getDato();
		tope = tope.getSig();
		return aux;
	}

	public void listar(){
		Nodo<Te> q =tope;
		if(q==null){
			System.out.println("\n No hay transacciones\n ");
		}else{
			System.out.print("\nTransacciones:");
			while( q !=null){
				 System.out.print("  "+q.getDato());
				 q=q.getSig();
			}
			System.out.print("\n");
		}
	}
}