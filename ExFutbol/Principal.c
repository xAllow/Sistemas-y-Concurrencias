/*
 * Principal.c
 *
 *  Created on: 14/6/2016
 *      Author: esc
 */

#include "ListaJugadores.h"
#include <stdio.h>



// Lee el fichero y lo introduce en la lista
void cargarFichero (char * nombreFich, TListaJugadores *lj)
{
	FILE *f = fopen(nombreFich, "rb");
	if(f == NULL){
		perror("Error");
	} else {
		unsigned int id;
		while(fread(&id, sizeof(unsigned int), 1, f) == 1){
			insertar(lj, id);
		}
	}
}


int main(){

	TListaJugadores lj;
	crear(&lj);
	/*
	insertar(&lj, 14);
	insertar(&lj, 10);
	insertar(&lj, 11);
	insertar(&lj, 12);
	insertar(&lj, 13);
	insertar(&lj, 13);
	insertar(&lj, 13);
	insertar(&lj, 13);
	insertar(&lj, 13);
	insertar(&lj, 13);
	recorrer(lj);
	printf("Numero de nodos: %d\n", longitud(lj));
	printf("Goleador maximo: %d\n", maximo(lj));
	destruir(&lj);
	recorrer(lj); */
	
    unsigned int num_goles;
	cargarFichero ("goles.bin",&lj);
	printf("Hay un total de %d jugadores\n",longitud(lj));
	fflush(stdout);

	recorrer(lj);
	fflush(stdout);
	printf("Introduce un n�mero de goles: \n");
	fflush(stdout);
	scanf("%d",&num_goles);


	eliminar(&lj,num_goles);
	printf("--------------------------------------\n");
	recorrer(lj);
	printf("Hay un total de %d jugadores\n",longitud(lj));
	fflush(stdout);

	printf ("El jugador que mas goles ha marcado es el que tiene ID: %d",maximo(lj));
	fflush(stdout);
	destruir (&lj);
	

	return 0;
}
