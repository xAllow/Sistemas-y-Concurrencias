    #include "Lista.h"
#include <stdlib.h>
#include <stdio.h>

void crearLista(Lista *l){
    *l = NULL;
} //crea una lista vacía

void recorrerLista(Lista l){
    Lista aux = l;
    while(aux != NULL){
        printf("%d", aux->elem);
        aux = aux->sig;
    }
} //escribe en la pantalla los elementos de la lista

void recorrerListaR(Lista l){
    if(l == NULL){
        printf("\n");
    }else{
        printf("%d", l->elem);
        recorrerListaR(l->sig);
    }
}

int listaVacia(Lista l){
    return l == NULL;
} //devuelve 0 sii la lista está vacía

void insertarLista(Lista *l,int elem){
    Lista aux = malloc(sizeof(struct NodoLista));
    aux->elem = elem;
    aux->sig = NULL; //hago un nodo con su elemento y el siguiente
    Lista act = *l; //Primer nodo de la lista
    Lista ant = NULL;
    while (act != NULL && act->elem < elem){
        ant = act;
        act = act->sig;
    }
    //act == NULL o act -> elem >= elem;
    if(ant == NULL){ //Lista vacia o elem menor que primer elemento
        aux->sig = act; //Se pueden sacar los dos aux -> sig = act porque se repite
        *l = aux;
    } else { //anterior != NULL
        aux->sig = act;
        ant->sig = aux;
    }
} // inserta el elemento elem en la lista l de forma que quede ordenada de forma creciente

void insertarListaR(Lista *l,int elem){

}

int extraerLista(Lista *l,int elem){
    Lista act = *l;
    Lista ant = NULL;
    while(act != NULL && act->elem < elem){
        ant = act;
        act = act->sig;   
    }
    if(act == NULL || act->elem > elem){
        return 0;
    }else{
        if(ant == NULL){
            *l = act->sig;
            free(act);
        }else { // ant y act != null
            ant->sig = act->sig;
            free(act);
        }
        return 1;
    }
} // elimina de la lista el elemento elem. Devuelve 1 si se ha podido eliminar y 0, si elem no estaba en la lista.

int extraerListaR(Lista *l,int elem){
    return 0;
}

void borrarLista(Lista *l){
    Lista aux;
    while(*l != NULL){
        aux = *l;
        *l = aux->sig;
        free(aux);
    }
} //elimina todos los nodos de la lista y la deja vacía

void borrarListaR(Lista *l){
}