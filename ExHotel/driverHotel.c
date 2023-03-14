/*
 * driverHotel.c
 *
 *  Created on: 9 abr. 2021
 *      Author: mmar
 */
#include "Planta.h"
#include "Hotel.h"
/**
 * Se almacena en el hotel con nPlantas los clientes del fichero nombre.
 * En el fichero los clientes viene con el siguiente formato
 * Nombre1 numHab1 fechaSal1
 * Nombre2 numHab2 fechaSal2
 * ...
 * Observa que en el fichero viene antes el nombre del cliente que el número
 * de habitación (al contrario que en la funcion nuevoClienteHotel)
 */
void cargarHotel(char* nombre, ListaHab* hotel, unsigned nPlantas){
	FILE *f = fopen(nombre, "rt");
	if(f == NULL){
		perror("Error");
	}
	char nom[30];
	unsigned fs, nh;
	while(fscanf(f, "%s %u %u", nom, &nh, &fs) == 3){
		nuevoClienteHotel(hotel,nPlantas, nh,nom,fs);
	}
	fclose(f);	
}


int main(){
	ListaHab hotel[5];
	crearHotel(hotel,5);
	nuevoClienteHotel(hotel,5,13,"Paco Timo",25);
	nuevoClienteHotel(hotel,5,24,"Rosa Moral",27);
	nuevoClienteHotel(hotel,5,22,"Lorena Campos",26);
	nuevoClienteHotel(hotel,5,32,"Marcos Navarro",26);
	nuevoClienteHotel(hotel,5,4,"Carmen Valero",22);
	nuevoClienteHotel(hotel,5,2,"Yolanda Cabo",23);
	nuevoClienteHotel(hotel,5,44,"Fernando Vera",27);
	nuevoClienteHotel(hotel,5,45,"Abel Justo",27);
	imprimirHotel(hotel,5);
	printf("\n\nBorramos los que salen el 27\n\n");
	borrarFechaSalidaHotel(hotel,5,27);
	imprimirHotel(hotel,5);
	printf("\n\nAhora borramos el hotel\n\n");
	borrarHotel(hotel,5);
	imprimirHotel(hotel,5);
	printf("\n\nHotel borrado\n\n");
	
	printf("\n\nAhora cargamos los datos del fichero DatosHotel.txt \n\n");
	cargarHotel("DatosHotel.txt",hotel,5);
	imprimirHotel(hotel,5);
	
	return 1;
}
