#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Mprocesos.h"

void Crear(LProc *lroundrobin){
    *lroundrobin = NULL;
}

void AnadirProceso(LProc *lroundrobin, int idproc){
    LProc aux = malloc(sizeof(struct T_Nodo));
    aux->pid = idproc;
    

    if(*lroundrobin == NULL){
        aux->sig = aux;
        *lroundrobin = aux;
    } else {
        aux->sig = (*lroundrobin)->sig;
        (*lroundrobin)->sig = aux;
        *lroundrobin = aux;
    
    }
}

void EjecutarProcesos(LProc lroundrobin){
    if(lroundrobin == NULL){
        printf("LISTA VACIA\n");
    } else {
        LProc aux = lroundrobin->sig;
        printf("Lista de procesos: \n");
        while(aux != lroundrobin){
            printf("%u, ", aux->pid);
            aux = aux->sig;
        }
        printf("%u", aux->pid);
    }
    printf("\n");
}


void EliminarProceso(int id, LProc *lista){
    LProc act = *lista;
    LProc ant = NULL;
    while(act->pid != id){
        ant = act;
        act = act->sig;
    }
    
    if (act == *lista) {  // Si el proceso a eliminar es el primero de la lista, se actualiza el puntero *lista
        *lista = act->sig;
    }

    ant->sig = act->sig;  // Se salta el proceso a eliminar
    free(act);  // Se libera la memoria ocupada por el proceso eliminado*/
        
    /*   
    if(act == act->sig){
        *lista = NULL;
        free(act);
    } else if (act == *lista){
        *lista = act->sig;
        ant->sig = act->sig;
        free(act);
    }
    if (act == *lista) {  // Si el proceso a eliminar es el primero de la lista, se actualiza el puntero *lista
        *lista = act->sig;
    }

    ant->sig = act->sig;  // Se salta el proceso a eliminar
    free(act);  // Se libera la memoria ocupada por el proceso eliminado*/
        
}

void EscribirFichero (char * nomf, LProc *lista){
    FILE *f = fopen(nomf, "wb");
    
    if(f == NULL){
        perror("Error");
    } else{
        LProc act = *lista;
        int nproc = 0;
        do
        {
            nproc++;
            act = act->sig;
        } while (act != *lista);
        
        LProc aux =*lista;
        fwrite(&nproc, sizeof(int),1,f);
        if(nproc > 0){
            do
            {
                fwrite(&(aux->pid), sizeof(int), 1,f);
                aux = aux->sig;
            } while (aux != *lista);   
        }
        fclose(f);
    }
    Crear(lista);
}