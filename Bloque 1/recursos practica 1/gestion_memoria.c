#include <stdlib.h>
#include <stdio.h>
#include "gestion_memoria.h"
#define MAX 1000

/* Crea la estructura utilizada para gestionar la memoria disponible. Inicialmente, s�lo un nodo desde 0 a MAX */
void crear(T_Manejador* manejador){
    (*manejador)->inicio = 0;
    (*manejador)->fin = MAX - 1;
    (*manejador)->sig = NULL;
}

/* Destruye la estructura utilizada (libera todos los nodos de la lista. El par�metro manejador debe terminar apuntando a NULL */
void destruir(T_Manejador* manejador){
    if(*manejador != NULL){
            destruir(&(*manejador)->sig);
            free(*manejador);
            *manejador = NULL;
    }
}

/* Devuelve en �dir� la direcci�n de memoria �simulada� (unsigned) donde comienza el trozo de memoria continua de tama�o �tam� solicitada.
Si la operaci�n se pudo llevar a cabo, es decir, existe un trozo con capacidad suficiente, devolvera TRUE (1) en �ok�; FALSE (0) en otro caso.
 */
void obtener(T_Manejador *manejador, unsigned tam, unsigned* dir, unsigned* ok){
    struct T_Nodo *prev = NULL;
    struct T_Nodo *act = *manejador;

    int lleno = 0;
    while(act != NULL && act->fin - act->fin + 1 < tam){
        prev = act;
        act = act->sig;
        lleno = (act->fin - act->fin + 1) == tam;
    }

    if(act != NULL){
        *dir = act->inicio;
        *ok = 1;
        act->inicio += tam;
        if(lleno){
            if(prev != NULL){
                prev->sig = act->sig;
            } else {
                *manejador = act->sig;
            }
            free(act);
        }
    } else {
        *ok = 0; 
    }


    /* Posible implementacion de obtener recursivo
    if((*manejador) == NULL){
        *ok = 0;
    } 
    unsigned mem_libre = (*manejador)->fin - (*manejador)->inicio + 1;

    if (mem_libre < tam){
        obtener(&(*manejador)->sig, tam, dir, ok);
    } else {
        *dir = (*manejador)->inicio;
        if(mem_libre - tam == 0){
            struc T_Nodo aux = *manejador;
            *manejador = (*manejador)->sig;
            free(aux);
        } else {
            (*manejador)->inicio += tam;
        }
        *ok = 1;
    }
    */
}

/* Muestra el estado actual de la memoria, bloques de memoria libre */
void mostrar (T_Manejador manejador){
    while (manejador != NULL){
        printf("(%u, %u)", manejador->inicio, manejador->fin);
        manejador = manejador->sig;
    }
    printf("\n");
}

/* Devuelve el trozo de memoria continua de tama�o �tam� y que
 * comienza en �dir�.
 * Se puede suponer que se trata de un trozo obtenido previamente.
 */
void devolver(T_Manejador *manejador,unsigned tam,unsigned dir){

}

