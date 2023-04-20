#include <stdlib.h>
#include <stdio.h>
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
        printf("Esta vacia\n");
    } else {
        LProc aux = lroundrobin->sig;
        printf("Lista de procesos: \n");
        while(aux != lroundrobin){
            printf("%d, ", aux->pid);
            aux = aux->sig;
        }
        printf("%d",aux->pid);
    }
}


void EliminarProceso(int id, LProc *lista){
    if((*lista)->pid == id && (*lista)->sig == *lista){
        LProc aux = *lista;
        *lista = NULL;
        free(aux);
    } else {
        LProc ant = *lista;
        LProc act = (*lista)->sig;
        while(act->pid != id){
            ant = act;
            act = act->sig;
        }
        if(act == *lista){
            ant->sig = act->sig;
            *lista = act->sig;
            free(act);
        } else {
            ant->sig = act->sig;
            free(act);
        }
    }
}

void EscribirFichero (char * nomf, LProc *lista){

    FILE *f = fopen(nomf, "wb");
    if(f == NULL){
        perror("Error al abrir");
    } else {
        int nproc = 0;
        LProc aux = (*lista)->sig;
        if(aux != NULL){
            do{
                nproc++;
                aux = aux->sig;
            } while (aux != *lista);
        }

        LProc l = *lista;
        fwrite(&nproc, sizeof(int), 1, f);
        if(nproc > 0){
            do {
                fwrite(&(l)->pid, sizeof(unsigned),1,f);
                l = l->sig;
            } while(l != *lista);
        }

        fclose(f);
    }
    
    
}