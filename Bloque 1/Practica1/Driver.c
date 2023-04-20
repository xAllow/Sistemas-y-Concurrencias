#include "Pila.h"
#include <stdio.h>
#include <stdlib.h>

int main(){
    Pila p;
    crear(&p);
    mostrar(p);
    for(int i = 0; i < 10; i++){
        insertar(&p, i);
    }
    mostrar(p);
    while(!pilaVacia(p)){
        printf("extraigo %d\n ", extraer(&p));
    }
    printf("\n");
    mostrar(p);
}