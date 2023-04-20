#include <stdio.h>
#include <stdlib.h>
#include "ListaJugadores.h"

//crea una lista vac�a (sin ning�n nodo)
void crear(TListaJugadores *lc){
    *lc = NULL;
}

//inserta un nuevo jugador en la lista de jugadores, poniendo 1 en el n�mero de goles marcados.
//Si ya existe a�ade 1 al n�mero de goles marcados.
void insertar(TListaJugadores *lj,unsigned int id){
    TListaJugadores aux = malloc(sizeof(struct Nodo));
    aux->goles = 1;
    aux->id = id;
    aux->sig = NULL;

    if(*lj == NULL){
        *lj = aux;
    } else {
        TListaJugadores act = *lj;
        TListaJugadores ant = NULL;
        while(act != NULL && act->id > id){
            ant = act;
            act = act->sig;
        }
        if(act != NULL && act->id == id){
            act->goles = act->goles + 1;
            free(aux);
        } else {
            if(ant == NULL){
                *lj = aux;
            } else {
                ant->sig = aux;
            }
            aux->sig = act;
        }
    }
}

//recorre la lista circular escribiendo los identificadores y los goles marcados
void recorrer(TListaJugadores lj){
    if(lj == NULL){
        printf("Lista vacia\n");
    } else {
        TListaJugadores aux = lj;
        while(aux != NULL){
            printf("El jugador %d ha marcado %d goles\n", aux->id, aux->goles);
            aux = aux->sig;
        }
    }
}

//devuelve el n�mero de nodos de la lista
int longitud(TListaJugadores lj){
    int tam = 0;
    TListaJugadores aux = lj;
    while(aux != NULL){
        tam++;
        aux = aux->sig;
    }
    return tam;
}

//Eliminar. Toma un n�mero de goles como par�metro y
//elimina todos los jugadores que hayan marcado menos que ese n�mero de goles
void eliminar(TListaJugadores *lj,unsigned int n){
    TListaJugadores act = *lj;
    TListaJugadores ant = NULL;
    TListaJugadores temp = NULL;

    while(act != NULL){
        if(act->goles < n){
            temp = act;
            act = act->sig;
            if(ant == NULL){
                *lj = act;
            } else {
                ant->sig = act;
            }
            free(temp);
        } else {
            ant = act;
            act = act->sig;
        }
    }
    /*
    TListaJugadores act = *lj;
    TListaJugadores ant = NULL;
    TListaJugadores temp = NULL;

    while (act != NULL) {
        if (act->goles < n) {
            temp = act;
            act = act->sig;
            if (ant == NULL) {
                *lj = act;
            } else {
                ant->sig = act;
            }
            free(temp);
        } else {
            ant = act;
            act = act->sig;
        }
    }
    */
}


// Devuelve el ID del m�ximo jugador. Si la lista est� vac�a devuelve 0. Si hay m�s de un jugador con el mismo n�mero de goles que el m�ximo devuelve el de mayor ID
// Hay que devolver el identificador, no el n�mero de goles que ha marcado
unsigned int maximo(TListaJugadores lj){
    unsigned int maxgol = 0;
    unsigned int idjug = 0;
    if(lj == NULL){
        return 0;
    } else {
        TListaJugadores aux = lj;
        while(aux != NULL){
            if(aux->goles > maxgol || aux->goles == maxgol && aux->id > idjug){
                maxgol = aux->goles;
                idjug = aux->id;
            }
            aux = aux->sig;
        }
    }
    return idjug;
}

//Destruye la lista y libera la memoria)
void destruir(TListaJugadores *lj){
    if(*lj == NULL){
        printf("Lista vacia \n");
    } else {
        TListaJugadores aux;
        while(*lj != NULL){
            aux = *lj;
            *lj = (*lj)->sig;
            free(aux);
        }
    }
}