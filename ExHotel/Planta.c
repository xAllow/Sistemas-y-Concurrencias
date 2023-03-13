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
    *lh = NULL;
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
    ListaHab aux = malloc(sizeof(struct Nodo));
    aux->numHab = nh;
    aux->fechaSalida = fs;
    strcpy(aux->nombre, nombre);

    ListaHab act = *lh;
    ListaHab ant = NULL;
    while(act != NULL && act->numHab < nh){
        ant = act;
        act = act->sig;
    }

    if(act != NULL && act->numHab == nh){
        strcpy(act->nombre, nombre);
        act->fechaSalida = fs;

    } else {
        if (ant == NULL){
            *lh = aux;
        } else {
            ant->sig = aux;
        }
        aux->sig = act;
    }
}

/**
 * Escribe por la pantalla la información de cada una de las habitaciones
 * almacenadas en la lista.
 * El formato de salida debe ser:
 * \t habitacion "nh" ocupada por "nombre" con fecha de salida "fs"
 */
void imprimir(ListaHab lh){
    while(lh != NULL){
        printf("\n\t habitacion %d ocupada por %s con fecha de salida %d", lh->numHab, lh->nombre, lh->fechaSalida);
        lh = lh->sig;
    }
}

/**
 * Borra todos los nodos de la lista y la deja vacía
 */
void borrar(ListaHab *lh){
    ListaHab aux = *lh;
    while(aux != NULL){
        free(aux);
        aux = aux->sig;
    }
    *lh = NULL;
    printf("Lista borrada\n");

}

/**
 * Borra todos las habitaciones cuya fecha de salida es fs.
 */

void borrarFechaSalida(ListaHab *lh,unsigned fs){
    ListaHab act = *lh;
    ListaHab ant = NULL;

    while(act != NULL){
        if(act->fechaSalida == fs){
            if(ant == NULL){
                *lh = act->sig;
            }else {
                ant->sig = act->sig;
            }
            free(act);
        }
        ant = act;
        act = act->sig;
    }
}   
