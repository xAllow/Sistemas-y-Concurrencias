#include <stdlib.h>
#include <stdio.h>
#include "Componentes.h"
#include <string.h>

/*
La rutina Lista_Vacia devuelve 1 si la lista que se le pasa
como parametro esta vacia y 0 si no lo esta.
*/
int Lista_Vacia(Lista lista){
    return (lista == NULL);
}

/*Num_Elementos es una funcion a la que se le pasa un puntero a una lista 
y devuelve el numero de elementos de dicha lista.
*/
int Num_Elementos(Lista  lista){
    int elem = 0;
    while(elem != NULL){
        elem++;
        lista = lista->sig;
    }
    return elem;
}

/*
La rutina Adquirir_Componente se encarga de recibir los datos de un nuevo 
componente (codigo y texto) que se le introducen por teclado y devolverlos 
por los parametros pasados por referencia "codigo" y "texto".
*/
 void Adquirir_Componente(long *codigo,char *texto){
    printf("Introduce el codigo: \n");
    scanf("%ld", codigo);
    printf("Introduce el texto: \n");
    scanf("%s", texto);
 }

/*
La funcion Lista_Imprimir se encarga de imprimir por pantalla la lista 
enlazada completa que se le pasa como parametro.
*/
void Lista_Imprimir( Lista lista){
    Lista aux = lista;
    if(aux == NULL){
        printf("Lista vacia");
    } else {
        while(aux != NULL){
            printf("Codigo: %ld. Texto: %s", aux->codigoComponente, aux->textoFabricante);
            aux = aux->sig;
        }
    }
}

/*
La funcion Lista_Salvar se encarga de guardar en el fichero binario 
"examen.dat" la lista enlazada completa que se le pasa como parametro. 
Para cada nodo de la lista, debe almacenarse en el fichero
el código y el texto de la componente correspondiente.
*/
void Lista_Salvar( Lista  lista){
        
}


/*
La funcion Lista_Crear crea una lista enlazada vacia
de nodos de tipo Componente.
*/
Lista Lista_Crear(){
    Lista l = NULL;
    return l;
}

/*
La funcion Lista_Agregar toma como parametro un puntero a una lista,
el código y el texto de un componente y  anyade un nodo al final de 
la lista con estos datos.
*/
void Lista_Agregar(Lista *lista, long codigo, char* textoFabricante){
    Lista aux = malloc(sizeof(struct elemLista));
    strcpy(aux->textoFabricante, textoFabricante);
    aux->codigoComponente = codigo;
    aux->sig = NULL;

    if(*lista == NULL){
        *lista = aux;
    } else {
        Lista act = *lista;
        Lista ant = NULL;
        while(act != NULL){
            ant = act;
            act = act->sig;
        }
        ant->sig = aux;
    }
}

/*
Lista_Extraer toma como parametro un puntero a una Lista y elimina el
Componente que se encuentra en su ultima posicion.
*/
void Lista_Extraer(Lista *lista){
    if(*lista == NULL){
        printf("Lista vacia");
    } else {
        Lista act = *lista;
        Lista ant = NULL;
        while(act != NULL){
            ant = act;
            act = act->sig;
        }
        if(ant == NULL){
            *lista = NULL;
            free(act);
            
        } else {
            ant->sig = NULL;
            free(act);
        }
    }
}

/*
Lista_Vaciar es una funcion que toma como parametro un puntero a una Lista
y elimina todos sus Componentes.
*/
void Lista_Vaciar(Lista *lista){
    Lista aux = *lista;
    while(*lista != NULL){
        aux = *lista;
        *lista = (*lista)->sig;
        free(aux);
    }
    *lista = NULL;
}
