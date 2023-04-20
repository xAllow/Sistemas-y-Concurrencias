/*
 ============================================================================
 Name        : prPalabras3_13.c
 Author      : PONGA SU NOMBRE AQUI <<<<<<<<<<<<<<<<<<<<<
 Version     : 1
 Copyright   : Examen parcial abril 2017
 Description : Programa principal, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "lista.h"
#define MIN_LETRA (3)
#define MAX_LETRA (13)
#define NUM_TAMANO (MAX_LETRA - MIN_LETRA +1)


void escribir_salida(FILE * fp, Lista* lp);


int main(void) {

	setvbuf(stdout, NULL, _IONBF, 0);
	setvbuf(stderr, NULL, _IONBF, 0);
	char *inputFileName = "Lorem_Ipsum.txt";
	char *outputFileName = "Palabras3_13.txt";


	/* Crear Array de Palabras de tamaño NUM_TAMANO */
	Lista arrayL[NUM_TAMANO];
	/* Incializar lista palabras */
	for(int i = 0; i < NUM_TAMANO; i++){
		crear(&arrayL[i]);
	}

	/* Abrir Fichero de Entrada */
	FILE *fent = fopen(inputFileName,"rt");
	if(fent == NULL){
		perror("No se ha podido abrir");
	} else {
		char cadena[20];
		while(fscanf("%s", cadena) == 1){
			int n = strlen(cadena);
			if(n >= MIN_LETRA && n <= MAX_LETRA){
				insertar(cadena, &arrayL[n-MIN_LETRA]);
			}
		}
		fclose(fent);
	}

	/* Leer palabras del fichero de entrada */

	/* Escribir en consola */

	/* Escribir archivo (para el apartado B) */

	/* Destruir las listas creadas */



	return EXIT_SUCCESS;
}
