/*
 * Planta.c
 *
 *  Created on: 9 abr. 2021
 *      Author:
 */


#include "Planta.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

/**
 * crea una lista de habitaciones vacia
 */
void crear(ListaHab *lh){

}

/**
 * En esta función se añade a la lista, la habitación con número nh,
 * cliente "nombre" y fecha de salida "fs".
 * Si ya existe una habitación numerada con nh en la lista se actualizan sus campos
 * con los nuevos valores ("nombre" y "fs").
 * Si no existe ninguna habitación con ese número, se inserta un nuevo nodo de manera que
 * la lista siempre esté ordenada con respecto a los números de las habitaciones (de
 * menor a mayor)
 */
void nuevoCliente(ListaHab *lh,unsigned nh,char *nombre,unsigned fs){

}

/**
 * Escribe por la pantalla la información de cada una de las habitaciones
 * almacenadas en la lista.
 * El formato de salida debe ser:
 * \t habitacion "nh" ocupada por "nombre" con fecha de salida "fs"
 */
void imprimir(ListaHab lh){

}

/**
 * Borra todos los nodos de la lista y la deja vacía
 */
void borrar(ListaHab *lh){

}

/**
 * Borra todos las habitaciones cuya fecha de salida es fs.
 */

void borrarFechaSalida(ListaHab *lh,unsigned fs){

}
