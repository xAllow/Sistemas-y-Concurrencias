/*
 * Driver.c
 *
 *  Created on: 
 *      Author: mmar
 */


/*
 * Principal.c
 *
 */

#include "ListaReproduccion.h"
#include <stdio.h>
#include <unistd.h>



int main(){
	ListaRep lista;
	char cancion[30];
	int i;
	crear(&lista);
	insertar(&lista, "Resistiré 2020");
	
	insertar(&lista, "La vida sigue igual");
	
	insertar (&lista, "Chica de ayer");
	
	mostrar(lista);	
	
	//PARTE 2

	for (i=0; i<20; i++){
		siguiente(&lista,cancion);
		printf("%d: Reproduciendo canción %s\n",i,cancion);
		sleep(1);
	}
	
	if (eliminar(&lista,"Se acabó")){
		printf("Canción %s eliminada\n","Se acabó");
	} else {
		printf("La canción %s no está en la lista\n","Se acabó");
	}
	sleep(1);
		
	if (eliminar(&lista,"Chica de ayer")){
		printf("Canción %s eliminada\n","Chica de ayer");
	} else {
		printf("La canción %s no está en la lista\n","Chica de ayer");
	}
	sleep(1);
	if (eliminar(&lista,"La vida sigue igual")){
		printf("Canción %s eliminada\n","La vida sigue igual");
	} else {
		printf("La canción %s no está en la lista\n","La vida sigue igual");
	}
	sleep(1);
	if (eliminar(&lista,"Resistiré 2020")){
		printf("Canción %s eliminada\n","Resistiré 2020");
	} else {
		printf("La canción %s no está en la lista\n","Resistiré 2020");
	}
	
	mostrar(lista);	
	
	
	//PARTE 3
	/*cargarListaCanciones(&lista,"ListaCanciones.bin");
	mostrar(lista);
	guardarListaCanciones(lista, "ListaCanciones.txt");
	}*/

	//borrar(&lista);
	//mostrar(lista);
	return 0;
}
