#include <stdlib.h>
#include <stdio.h>
#include "Polinomio.h"

//PRIMERA PARTE

/*Crea el polinomio 0 (es decir, un polinomio vacío).
*/
void polinomioCero(TPolinomio *p){
    *p = NULL;
}

/*Devuelve el grado del polinomio, es decir, el mayor exponente de los
* monomios que no son nulos. En el ejemplo, el grado es 7.
*/
int grado(TPolinomio p){
    int maxgrado = 0;
    if(p != NULL){
        while(p != NULL){
            if(p->exp > maxgrado){
                maxgrado = p->exp;
            }
            p = p->sig;
        }
    }
    return maxgrado;
}

/* Devuelve el coprintf("%d", p->exp);eficiente de exponente exp del polinomio p.
*/
int coeficiente(TPolinomio p,unsigned int exp){
    while(p != NULL && exp != p->exp){
        p = p->sig;
    }
    if(p == NULL){
        return 0;
    }
    return p->coef;
}

/* Insertar el monomio con coeficiente coef, y exponente exp en el polinomio,
 * de manera que el polinomio quede ordenado.
 * Asegurarse que no se insertan monomios cuyo coeficiente sea 0 y
 * tampoco dos monomios con el mismo exponente.
 * Si al insertar un monomio ya hay otro con el mismo exponente
 * los coeficientes se sumarán. Se puede asumir que el valor del coeficiente coef a
 *  sumar siempre será un número natural (un entero no negativo)
*/
void insertar(TPolinomio *p,unsigned int coef,unsigned int exp){
    TPolinomio nuevo = (TPolinomio)malloc(sizeof(struct TNodo));
    nuevo->coef = coef;
    nuevo->exp = exp;
    nuevo->sig = NULL;
    if(*p == NULL){
        *p = nuevo;
    }else{
        TPolinomio aux = *p;
        TPolinomio ant = NULL;
        while(aux != NULL && exp > aux->exp){
            ant = aux;
            aux = aux->sig;
        }
        if(aux == NULL){ //Se coloca el ultimo
            ant->sig = nuevo;
        }else if(aux->exp == exp){ //Se suman coef y se elimina nuevo
            aux->coef += coef;
            free(nuevo);
        }else{
            if(ant == NULL){ // Se coloca el primero
                nuevo->sig = *p;
                *p = nuevo;
            }else{ //Se coloca entre medias
                ant->sig = nuevo;
                nuevo->sig = aux;
            }

        }
    }
}

/*Escribe por la pantalla el polinomio con un formato similar al siguiente:
* [(9,0)(5,1)(3,3)(2,5)(3,7)] para el polinomio ejemplo.
*/
void imprimir(TPolinomio p){
    printf("[");
    while(p != NULL){
        printf("(%d,%d)", p->coef, p->exp);
        p = p->sig;
    }
    printf("]");
}


/* Elimina todos los monomios del polinomio haciendo
 * que el polinomio resultante sea el polinomio 0.
*/
void destruir(TPolinomio *p){
    TPolinomio aux;
    while(*p != NULL){
        aux = *p;
        *p = (*p)->sig;
        free(aux);
    }
}

/* Lee los coeficientes de un polinomio que están almacenados en
* un fichero de texto, y crean la lista de monomios p.
* Los coeficientes en el fichero de texto van de menor exponente a mayor exponente
* y son dígitos del 0 al 9.
* Por ejemplo, para el polinomio ejemplo el fichero de texto estaría compuesto por
* la secuencia de caracteres “95030203”. La conversión de un valor de tipo ‘char’
* que contenga un valor númerico (ej. char c = ‘2’) a su correspondiente valor
* entero (int valor), se puede hacer de la siguiente forma: valor = c – ‘0’.
*/

void crearDeFichero(TPolinomio *p, char *nombre){
    FILE *f = fopen(nombre, "rb");
    if(f == NULL){
        perror("No se ha podido abrir el archivo");
    } else {
        char c;
        int coef = 0;
        int exp = 0;
        while(fread(&c, sizeof(char), 1, f) == 1){
            coef = c - '0';
            insertar(p, coef, exp);
            exp++;
        }
        fclose(f);
    }
}