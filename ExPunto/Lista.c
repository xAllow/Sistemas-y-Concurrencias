#include "Lista.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>


/*
 * Inicializa la lista de puntos creando una lista vacía
 *
 */
void crearLista(TLista *lista){
    *lista = NULL;
}


/**
 * Inserta el punto de forma ordenada (por el valor de la abscisa x)
 * en la lista siempre que no esté repetida la abscisa.
 *  En ok, se devolverá un 1 si se ha podido insertar, y  0 en caso contrario.
 *  Nota: utiliza una función auxiliar para saber
 *   si ya hay un punto en la lista con la misma abscisa punto.x
 *
 */
void insertarPunto(TLista *lista, struct Punto punto, int * ok){
    if(buscarPunto(*lista, punto.x)){
        *ok = 0;
    } else {
        TLista aux = malloc(sizeof(struct Nodo));
        aux->punto.x = punto.x;
        aux->punto.y = punto.y;
        aux->sig = NULL;
        TLista act = *lista;
        TLista ant = NULL;
        while(act != NULL && act->punto.x < punto.x){
            ant = act;
            act = act->sig;
        }
        if(ant == NULL){
            aux->sig = act;
            *lista = aux;
        } else {
            ant->sig = aux;
            aux->sig = act;
        }
        *ok = 1;
    }
}

int buscarPunto(TLista lista, float x) {
    while (lista != NULL) {
        if (lista->punto.x == x) {
            return 1;
        }
        lista = lista->sig;
    }
    return 0;
}
/*
 * Elimina de la lista el punto con abscisa x de la lista.
 * En ok devolverá un 1 si se ha podido eliminar,
 * y un 0 si no hay ningún punto en la lista con abscisa x
 *
 */
void eliminarPunto(TLista *lista,float x,int* ok){
    if(!buscarPunto(*lista, x)){
        *ok = 0;
    } else {
        TLista act = (*lista)->sig;
        TLista ant = *lista;
        while(act != NULL){
            if(act->punto.x == x){
                if(ant == NULL){
                    *lista = act->sig;
                    free(act);
                } else {
                    ant->sig = act->sig;
                    free(act);
                }
                *ok = 1;
            }
            ant = act;
            act = act->sig;
        }
    }
}

 /**
 * Muestra en pantalla el listado de puntos
 */
void mostrarLista(TLista lista){
    if(lista == NULL){
        printf("Lista vacia\n");
    } else {
        TLista aux = lista;
        while (aux != NULL){
            printf("(x:%.2f, y:%.2f)\n", aux->punto.x,aux->punto.y);
            aux = aux->sig;
        }
        printf("\n");
    }
}

/**
 * Destruye la lista de puntos, liberando todos los nodos de la memoria.
 */
void destruir(TLista *lista){
    TLista aux;
    while (*lista != NULL){
        aux = *lista;
        *lista = (*lista)->sig;
        free(aux);
    }
    *lista = NULL;
}

/*
 * Lee el contenido del archivo binario de nombre nFichero,
 * que contiene una secuencia de puntos de una función polinómica,
 *  y lo inserta en la lista.
 *
 */
void leePuntos(TLista *lista,char * nFichero){
    FILE *f = fopen(nFichero, "rb");
    if(f == NULL){
        perror("Error al abrir");
    } else {
        struct Punto p;
        int ok;
        float id[2];
        *lista = NULL;
        while(fread(&id,sizeof(float),2, f)==2){
            p.x = id[0];
            p.y = id[1];
            insertarPunto(&(*lista), p, &ok);
        }
        fclose(f);
    }
}
