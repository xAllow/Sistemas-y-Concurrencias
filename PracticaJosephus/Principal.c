/*
 * Principal.c
 *
 *  Created on: 12/4/2016
 *      Author: mmar
 */

#include "ListaCircular.h"
#include <stdio.h>


// Lee el fichero y lo introduce en la lista
void cargarFichero (const char *nombreFich, TListaCircular *lc){
	// REALIZAR ESTE PROCEDIMIENTO

}

void leerFichero(char* nombreFich){
	FILE *f = fopen(nombreFich, "rt");
	if (f == NULL){
		perror("No se ha podido abrir el fichero");
	} else {
		char cadena[20];
		while(fscanf(f,"%s ", cadena)==1){
			printf("%s\n", cadena);
		}
		fclose(f);
	}
}

void copiarFichero(char* nombreref, char* nombreD){
	FILE *fent = fopen(nombreref, "rt");
	FILE *fsal = fopen(nombreD, "wt");
	
	if(fent == NULL || fsal == NULL){
		perror("No se ha podido abrir el archivo");
	} else {
		char cadena [20];
		while(fscanf(fent, "%s",cadena)==1){
			fprintf(fsal, "%s\n", cadena);
		}

		fclose(fent);
		fclose(fsal);

	}
}
/*
int main2(){

	TListaCircular lc;
	crear(&lc);

	char nombre[30];

	int n;

	cargarFichero ("listaNombres.txt",&lc);
	recorrer(lc);
	printf("Introduce un n�mero entre 0 y 60: ");
	fflush(stdout);
	scanf("%d",&n);
	while (longitud(lc)>1){
		mover(&lc,n);
		extraer(&lc,nombre);
		printf("%s ha salido del c�rculo \n",nombre);
	}

	extraer(&lc,nombre);
	printf("--------------------------------------\n");
	printf("%s ha sido seleccionado !!!!! \n",nombre);
	fflush(stdout);

	return 0;
}
*/
int main(){
	leerFichero("listaNombres.txt");
	copiarFichero("listaNombre.txt", "tumama.txt");
}
