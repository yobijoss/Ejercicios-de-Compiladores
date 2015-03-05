int main(void){
	char cadena[100];
	char cadena2[100];
	int len; 
	int i;
	
	i = 0;
	len = strlen(cadena);
	for(i= 0 ;i<len;i++){
		cadena2[i] = cadena[len -i -1];
	}
	printf("Cadena volteada %s\n",cadena2 );
	return 0;
}