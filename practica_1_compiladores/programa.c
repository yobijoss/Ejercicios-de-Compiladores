#include <stdio.h>
#include <string.h>

// Programa que invierte una cadena 
int  main(int argc, char const *argv[])
{
	char cadena[100] = "Me llamo Jose Angel";
	char cadena2[100];
	printf("Cadena original : %s\n",cadena );
	int len = strlen(cadena);
	int i = 0;
	for(i= 0 ;i<len;i++){
		cadena2[i] = cadena[len -i -1];
	}
	printf("Cadena volteada %s\n",cadena2 );
	return 0;
}