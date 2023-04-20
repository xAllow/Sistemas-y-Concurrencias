#include "ListaCircular.h"
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

//crea una lista circular vac�a (sin ning�n nodo)
void crear(TListaCircular *lc){
    *lc = NULL;
}

//inserta un nuevo nodo con el dato nombre al final de la lista circular
void insertar(TListaCircular *lc,char *nombre){
    TListaCircular aux = malloc(sizeof(struct TNodo));
    strcpy(aux->nombre, nombre);
    if(*lc == NULL){
        *lc = aux;
        (*lc)->sig = *lc;

    } else {    
        aux->sig = (*lc)->sig;
        (*lc)->sig = aux;
        *lc = aux;
    }
}
//recorre la lista circular escribiendo los nombres de los nodos en la
//pantalla
void recorrer(TListaCircular lc){
    TListaCircular aux = lc->sig;
    while(aux != lc){
        printf("%s\n", aux->nombre);
        aux = aux->sig;
    }
    printf("%s", lc->nombre);
}

//devuelve el n�mero de nodos de la lista
int longitud(TListaCircular lc){
    if(lc == NULL){
        return 0;
    }
    TListaCircular aux = lc->sig;
    int num = 1;
    while(aux != lc){
        num++;
        aux = aux->sig;
    }
    return num;

}

//mueve el puntero exterto de la lista n nodos (siguiendo la direcci�n de la
//lista)
void mover(TListaCircular *lc,int n){
    TListaCircular aux = *lc;
    int cnt = 0;
    while(cnt != n){
        aux = aux->sig;
        cnt++;
    }
    *lc = aux;
}

//elimina el primer nodo de la lista, y devuelve el nombre que contiene
//a trav�s del par�metro nombre
void extraer(TListaCircular *lc,char *nombre){
    if(*lc != NULL){
        if((*lc)->sig == *lc){
            strcpy(nombre, (*lc)->nombre);
            free(*lc);
            *lc = NULL;
        } else{
            TListaCircular aux = (*lc)->sig;
            strcpy(nombre, aux->nombre);
            (*lc)->sig = aux->sig;
            free(aux);
        }
    }
}