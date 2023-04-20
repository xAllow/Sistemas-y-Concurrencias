#include <stdio.h>
#include <stdlib.h>
#include "arbolbb.h"


// Crea la estructura utilizada para gestionar la memoria disponible.
	void Crear(T_Arbol* arbol){
        *arbol = NULL;
    }

// Destruye la estructura utilizada.
	void Destruir(T_Arbol* arbol){
        if(*arbol != NULL){

            Destruir(&(*arbol)->izq);//izq
            Destruir(&(*arbol)->der);//der
            free(*arbol);
            *arbol = NULL;

        }   
    }

	// Inserta num en el arbol
	void Insertar(T_Arbol* arbol,unsigned num){
        if(*arbol == NULL){
            T_Arbol aux = malloc(sizeof(struct T_Nodo));
            aux->dato = num;
            aux->izq = NULL;
            aux->der = NULL;
            *arbol = aux;
        } else {
            if( num < (*arbol)->dato){
                Insertar(&(*arbol)->izq, num);
            } else {
                Insertar(&(*arbol)->der, num);
            }
        }
    }
	// Muestra el contenido del �rbol en InOrden
	void Mostrar(T_Arbol arbol){
        if(arbol != NULL){
            Mostrar(arbol->izq);
            printf("%d ", arbol->dato);
            Mostrar(arbol->der);
            
        }
    }
	// Guarda en disco el contenido del arbol
	void Salvar(T_Arbol arbol, FILE* fichero){
        if(arbol != NULL){
            Salvar(arbol->izq, fichero);
            fwrite(&(arbol->dato),sizeof(unsigned),1,fichero);
            Salvar(arbol->der, fichero);
            
        }
    }