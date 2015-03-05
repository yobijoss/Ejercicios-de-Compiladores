int sumar(int a,int b){
	return a+b;
}

int main(void){
	char cadena[100];
	char cadena2[100];
	int len; 
	int i;
	int j;
	cadena = "yolo" ;
	i = 0;
	len = 4;
	for(i= 0 ;i<len;i++){
		cadena2[i] = cadena[len -i -1];
	}
	print("Cadena volteada "+i );
	print("La suma es "+j);
	return 0;
}

