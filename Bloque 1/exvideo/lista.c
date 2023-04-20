#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include "lista.h"


/* Crea una lista vacia */
void crear(TLista *lista){
    *lista = NULL;
}

/* Comprueba si la lista esta vacia o no
    - Devuelve 0 si no esta vacia
    - Devuelve un valor distinto de 0 si esta vacia
*/
int estaVacia(TLista lista){
    int vacia = 0;
    if(lista != NULL){
        vacia = 0;
    } else{
        vacia = 1;
    }
    return vacia;
}

/* Inserta en la lista indicada como primer parámetro, un frame con nombre "nombre" 
   que ocupa la posición "pos" en el video original. 
   
   La inserción se realiza ordenada de forma ascendente por la posicion 
   del frame. No pueden existir en la lista dos frames que ocupen la misma posicion
   en el vídeo original. En caso de que ya exista en la lista un frame en la misma
   posición, solo se insertará el primero, y el resto se considerarán que 
   son erróneos y no se insertarán. 
*/
void insertar(TLista *lista, unsigned int pos, char *nombre){
    TLista aux = malloc(sizeof(struct TNodo));
    aux->nFrame = pos;
    strcpy(aux->nombre, nombre);
    aux->sig = NULL;

    if(*lista == NULL){
        *lista = aux;
    } else {
        TLista ant = NULL;
        TLista act = *lista;
        while(act != NULL && act->nFrame < pos){
            ant = act;
            act = act->sig;
        }
        if(act == NULL){
            ant->sig = aux;
        } else {
            if(act->nFrame == pos){
                printf("No se puede insertar. Ya existe un frame en la posición %d\n", pos);
                free(aux);
            } else {
                aux->sig = act;
                if(ant == NULL){
                    *lista = aux;
                } else {
                    ant->sig = aux;
                }
            }
        }
    }
}

/* Crea una lista a partir de un fichero de texto
   El fichero estará formado por una línea por cada frame a insertar.
   El formato de cada línea será <nFrame> <nombreFichero>, donde 
     - nFrame: es un valor de tipo entero
     - nombreFichero: es una cadena de caracteres de una sola palabra
       (sin espacios o ningún otro tipo de separador)

   Ejemplo: 0 nombreFrame0
*/
void crearDesdeFichero(TLista *lista, FILE *fich){
    if(fich == NULL){
        perror("No se ha podido abrir el fichero");
    }else {
        int nfram = 0;
        char cad[30];
        while(fscanf(fich, "%d %s", &nfram, cad) == 2){
            insertar(lista, nfram, cad);
        }
        fclose(fich);
    }
    
}

/* Muestra los elementos de la lista. Se empieza por el elemento en el 
   inicio de la lista y se termina por el elemento en el final de la lista.
 */
void mostrar(TLista lista){
    TLista aux = lista;
    if(lista == NULL){
        printf("Lista vacia\n");
    } else {
        while(aux != NULL){
            printf("%d %s\n", aux->nFrame, aux->nombre);
            aux = aux->sig;
        }
        
    }
}

/* Elimina de la lista un conjunto de frames, de acuerdo al valor del segundo
   parámetro "salto".
    - salto debe ser mayor que 1. Si no se cumple esta condición en "numFrames" 
    se devuelve 0 y no se escribe nada en el fichero de texto que se pasa como cuarto parámetro.
    - se empezará a contar a partir del primer nodo de la lista, y si haya nodos suficientes
    en la lista se avanzará tantas veces como diga salto y el nodo correspondiente
    se saca de la lista, incrementando el número de nodos extraídos y guardando la
    posición y el nombre del frame eliminado en el fichero de texto indicando como cuarto 
    parámetro (formato de cada línea del fichero: <nframe> <nombreFichero>)
    - se repetirá lo mismo mientras haya nodos en la lista
*/
void sacar(TLista *lista, unsigned int salto, unsigned int *numFrames, FILE *eliminados){
    if (salto <= 1) {
        fprintf(eliminados, "Error: el salto debe ser mayor que 1\n");
        *numFrames = 0;
        return;
    }
    
    *numFrames = 0;
    unsigned int pos;
    char nombre[30];
    
    while (*lista != NULL) {
        for (int i = 1; i < salto && (*lista)->sig != NULL; i++) {
            lista = &((*lista)->sig);
        }
        
        pos = (*lista)->nFrame;
        strcpy(nombre, (*lista)->nombre);
        eliminar(lista, &pos, nombre);
        fprintf(eliminados, "%d %s\n", pos, nombre);
        (*numFrames)++;
    }
}

/* Se saca el primer nodo de la lista devolviendo los datos almacenados en ese nodo
   en los parámetros "numFrames" y "nombre"

   Devolverá un 1 si la lista no estaba vacía y ha podido eliminar un nodo
   Devolverá 0 si la lista estaba vacía y no ha podido eliminar un nodo
*/
int eliminar(TLista *lista, unsigned int *numFrame, char *nombre){
    if(*lista == NULL){
        return 0;
    } else {
        TLista aux = *lista;
        *numFrame = aux->nFrame;
        strcpy(nombre, aux->nombre);
        *lista = (*lista)->sig;
        free(aux);
        return 1;
    }
}

/* Se libera toda la memoria reservada para los nodos de la lista */
void destruir(TLista *lista){
    TLista aux = *lista;
    while(*lista != NULL){
        aux = *lista;
        *lista = (*lista)->sig;
        free(aux);
    }
    *lista = NULL;
}