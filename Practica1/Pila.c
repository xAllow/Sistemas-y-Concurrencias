#include "Pila.h"
#include <stdlib.h>
#include <stdio.h>

void crear(Pila *p){
    *p = NULL;
}

void mostrar(Pila p){
    Pila aux = p; //No hace falta
    while (aux != NULL){
        // p->valor;  == (*p).valor
        printf("%d", aux->valor);
        aux = aux->sig;
    }
    printf("fin pila \n");
}

int pilaVacia(Pila p){
    return p==NULL;
}

void insertar(Pila *p, int v){
    Pila aux = malloc(sizeof(struct Nodo));
    aux->valor = v;
    aux->sig = *p;
    *p = aux;
}
int extraer(Pila *p){
 //Suponemos pila no vacia
    Pila aux = *p;
    int v = aux->valor;
    *p = aux->sig;
    free(aux);
    return v;
}

void borrar(Pila *p){
    Pila aux;
   while(*p != NULL){
        aux = *p;
        *p = aux->sig;
        free(aux);
   }
}
