/*
 * driverPlanta.c
 *
 *  Created on: 9 abr. 2021
 *      Author: mmar
 */


#include "Planta.h"
#include <stdio.h>

int main(){

	ListaHab lh;
	crear(&lh);

	imprimir(lh);
	/*
	printf("\n\nAhora borramos los que salen el día 26\n\n");
	borrarFechaSalida(&lh,26);
	imprimir(lh);
	printf("\n\nAhora borramos toda la lista\n\n");
	borrar(&lh);
	imprimir(lh);
	*/
	return 1;
}
