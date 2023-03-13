#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "ListaReproduccion.h"

//PARTE 1
/* Crea una lista de reproducción vacía */
void crear(ListaRep * lista){
    *lista = NULL;
}

/* Inserta una nueva canción al final de la lista de reproducción */
void insertar(ListaRep *lista,char * cancion){
    ListaRep aux = malloc(sizeof(struct Nodo));
    strcpy(aux->cancion, cancion);
    if(*lista == NULL){
        *lista = aux;
        aux->sig = aux;
    } else {
        aux->sig = (*lista)->sig;
        (*lista)->sig = aux;
        *lista = aux;
    }
}

/* Libera la memoria asociada a la lista de reproducción */
void borrar(ListaRep *lista){
    ListaRep actual = (*lista)->sig;
    while (actual != *lista) {
        free(actual);
        actual = actual->sig;
    }
    free(*lista);
    *lista = NULL;
    /*
    ListaRep aux;
    while(*lista != NULL){
        aux = *lista;
        *lista = (*lista)->sig;
        free(aux);
    }
    *lista = NULL;*/
}
/* Muestra por pantalla la lista de canciones almacenada */
void mostrar(ListaRep lista){
    if( lista == NULL){
        printf("Lista vacia\n");
    } else{
        ListaRep aux = lista->sig;
        while(aux != lista){
            printf("%s\n", aux->cancion);
            aux = aux->sig;
        }
        printf("%s\n", aux->cancion); //Imprimo la ultima cancion

    }
}

//PARTE 2
/* Elimina de la lista la canción cuyo título coincide con el parámetro de la función.
 * Devuelve 0 si la canción no está en la lista, y 1 en otro caso */
int eliminar(ListaRep *lista, char * cancion){
    if(*lista == NULL){
        return 0;
    } 
    ListaRep act = *lista;
    ListaRep ant = NULL;
    while(strcmp(act->cancion,cancion) != 0){
        ant = act;
        act = act->sig;
        if(act == *lista){
            return 0;
        }
    }
    if(ant != NULL){ //No está vacia
        ant->sig = act->sig;
    } else {
        *lista = act->sig;
    }

    free(act);
    *lista = NULL;
    return 1;

}

/* Devuelve en el array canción el título de la primera
 * canción que está en la lista de reproducción,
 * y mueve el puntero externo de la lista a la siguiente canción.
 * Devuelve 0 si la lista está vacía, y 1, en otro caso  */
int siguiente(ListaRep *lista,char *cancion){
    if (*lista == NULL) {
        return 0;
    }
    strcpy(cancion, (*lista)->cancion);
    *lista = (*lista)->sig;
    return 1;

}