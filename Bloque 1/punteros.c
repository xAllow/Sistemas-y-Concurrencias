#include <stdio.h>
#include <stdlib.h>

/*
void suma(int a, int b, int *s) { Para modificar s hay que hacerlo desde el puntero
    *s = a + b;
}
*/ 

void intercambiar(int *pa, int *pb){
    //pa es un puntero, *pa es un entero
    int aux = *pa;
    *pa = *pb;
    *pb = aux;
}
int main(){
    /*
    int a = 25; // 4 bytes
    int *pa = &a; //Puntero a entero 4 bytes, hay que referenciar la direccion de a
    char c = 'A'; // 1 bytes
    char *pc = &c; // 4 bytbitses al ser un puntero
    *pa = 8;
    printf("Valor de a %d, valor del puntero %d \n", *pa, a);
    */

   int a = 5;
   int b = 8;
   intercambiar(&a, &b);
   printf("a=%d, b=%d \n", a, b);
    return 0;

}