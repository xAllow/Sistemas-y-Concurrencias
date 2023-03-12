#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void eliminar(char c, char* cadena){
    int i = 0;
    int j = 0;
    while(cadena[j] != '\0'){
        if(cadena[j] != c){
            cadena[i] = cadena[j];
            i++;
        }
        j++;
    }
    cadena[i] = '\0';
}

void binario(unsigned char c, int tam){
    if(tam > 0){
        binario(c/2, tam-1);
        printf("%u", c%2);
    } else {
        printf("%u", c%2);
    }
}


int main(){

    /*Ejercicio 1*/
    printf("Ejercicio 1 \n");
    int t[30];
    int *p;

    for(int i = 0; i < 30; i++){
        t[i] = i;
    }

    p = t; //Esto se puede hacer
    //t = p no se puede hacer 
    for (int i = 0; i < 30; i++) {
        printf("%d ", *(p+i));
    }
    printf("\n");
    /*Ejercicio 2
    No se puede poner a la izquierda, no tiene sentido poner una direccion de memoria
    */
    /*Ejercicio 3*/
    printf("Ejercicio 3 \n");
    char c1 = 'P';

    char c2 = 'Q';

    char *d,*f;

    d = &c1;

    f = &c2;

    *d = *f;

    printf("%c",c1);
    printf("\n");

    /*Ejercicio 4*/
      float r[3];

    float *ptr;

    r[0] = 1.0;

    r[1] = 2.0;

    r[2] = 3.0;

    ptr = r;

    printf("%.2f",*ptr);
    printf("\n");

    /*Ejercicio 5*/
    printf("Ejercicio 5 \n");
        char *str = "Empezamos una y otra vez";
        char *ptrc;
        char a[28];
        ptrc = str;
        strcpy(a, str);
        str = &a[0];
    printf("str: %s\n", str); // imprime la cadena a la que apunta str
    printf("ptr: %s\n", ptrc); // imprime la cadena a la que apunta ptr

    /*Ejercicio 8*/
    printf("Ejercicio 8 \n");
    char cadena[20] = "Hola Mundo";
    eliminar('o', cadena);
    printf("%s\n", cadena);

    /* Extra*/
    unsigned char bin = 25;
    binario(bin, 8);
    printf("\n");


}