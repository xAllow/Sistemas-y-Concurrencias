#include "Polinomio.h"

/*Crea el polinomio 0 (es decir, un polinomio vacío).
 */
void polinomioCero(TPolinomio *p) {
	*p = NULL;

}

/*Devuelve el grado del polinomio, es decir, el mayor exponente de los
 * monomios que no son nulos. En el ejemplo, el grado es 7.
 */
int grado(TPolinomio p) {
	int maxgrado = 0;
	if (p != NULL) {
		TPolinomio ptr = p;
		while (ptr != NULL) {
			if (ptr->exp > maxgrado) {
				maxgrado = ptr->exp;
			}
			ptr = ptr->sig;
		}
	}
	return maxgrado;
}

/* Devuelve el coeficiente de exponente exp del polinomio p.
 */
int buscarExponente(TPolinomio p, unsigned int exp) {
	int res = 0;
	if (p != NULL) {
		TPolinomio ptr = p;
		while (ptr != NULL && ptr->exp != exp) {
			ptr = ptr->sig;
		}

		if (ptr != NULL) {
			res = 1;
		}
	}
	return res;
}

int coeficiente(TPolinomio p, unsigned int exp) {
	int coeficiente = 0;
	if (buscarExponente(p, exp) == 1) { //encontrado
		if (p != NULL) {
			TPolinomio ptr = p;
			while (ptr != NULL && ptr->exp != exp) {
				ptr = ptr->sig;
			}

			if (ptr != NULL) {
				coeficiente = ptr->coef;
			}
		}
	}

	return coeficiente;
}

/* Insertar el monomio con coeficiente coef, y exponente exp en el polinomio,
 * de manera que el polinomio quede ordenado.
 * Asegurarse que no se insertan monomios cuyo coeficiente sea 0 y
 * tampoco dos monomios con el mismo exponente.
 * Si al insertar un monomio ya hay otro con el mismo exponente
 * los coeficientes se sumarán. Se puede asumir que el valor del coeficiente coef a
 *  sumar siempre será un número natural (un entero no negativo)
 */

TPolinomio crearNodo(unsigned int coef, unsigned int exp){
	TPolinomio nuevoNodo = malloc(sizeof(struct TNodo));
	nuevoNodo->coef=coef;
	nuevoNodo->exp=exp;
	nuevoNodo->sig=NULL;
	return nuevoNodo;
}

void insertar(TPolinomio *p, unsigned int coef, unsigned int exp) {
	TPolinomio ant = NULL;
	TPolinomio ptr = *p;
	TPolinomio aux = NULL;

	while(ptr != NULL && ptr->exp < exp){
		ant = ptr;
		ptr = ptr->sig;
	}

	if(ant==NULL){
		if(ptr==NULL){	//lista vacia
			if(coef != 0){
				*p = crearNodo(coef, exp);
			}

		}else{	//lista llena
			if(ptr->exp == exp){	//mismo exponente
				ptr->coef = ptr->coef+coef;
			}else{	//distinto exponente
				if(coef != 0){
					aux = crearNodo(coef, exp);
					aux->sig = *p;
					*p = aux;
				}
			}
		}
	}else if(ptr == NULL){
		if(coef != 0){
			ptr->sig = crearNodo(coef, exp);
		}

	}else{
		if(ptr->exp == exp){
			ptr->coef = ptr->coef+coef;
		}else{
			if(coef!=0){
				aux = crearNodo(coef, exp);
				aux->sig = ptr;
				ant->sig = aux;
			}
		}
	}
}

/*Escribe por la pantalla el polinomio con un formato similar al siguiente:
 * [(9,0)(5,1)(3,3)(2,5)(3,7)] para el polinomio ejemplo.
 */
void imprimir(TPolinomio p) {
	TPolinomio ptr = p;
	if (p == NULL) {
		printf("El Polinomio esta vacio: [(0,0)]\n");
	} else {
		printf("[");
		fflush(stdout);
		while (ptr != NULL) {

			printf("(%i,%i)", ptr->coef, ptr->exp);
			fflush(stdout);
			ptr = ptr->sig;
		}
		printf("]\n");
	}

}

/* Elimina todos los monomios del polinomio haciendo
 * que el polinomio resultante sea el polinomio 0.
 */
void destruir(TPolinomio *p) {
	if(*p != NULL){
		TPolinomio ptr;
		while(*p != NULL){
			ptr = *p;
			*p = (*p)->sig;
			free(ptr);
		}
	}
}





//////////////////// SEGUNDA PARTE ////////////////////


/* Lee los coeficientes de un polinomio que están almacenados en
* un fichero de texto, y crean la lista de monomios p.
* Los coeficientes en el fichero de texto van de menor exponente a mayor exponente
* y son dígitos del 0 al 9.
* Por ejemplo, para el polinomio ejemplo el fichero de texto estaría compuesto por
* la secuencia de caracteres “95030203�. La conversión de un valor de tipo ‘char’
* que contenga un valor númerico (ej. char c = ‘2’) a su correspondiente valor
* entero (int valor), se puede hacer de la siguiente forma: valor = c – ‘0’.
*/
void crearDeFichero(TPolinomio *p, char *nombre){
	FILE *ptr=fopen(nombre,"r");
		if(ptr==NULL)perror("No se puede abrir el fichero correctamente");
		else{
			char coef;
			char exp;
			unsigned int c;
			unsigned int e;
			while(fscanf(ptr,"%c %c",&coef,&exp)==2 ){
				c = coef -'0';
				e = exp -'0';
				insertar(p,c,e);
			}
			fclose(ptr);
		}
}





//////////////////// TERCERA PARTE ////////////////////


/* Evalúa el polinomio para el valor x y devuelve el resultado.
 * Para la evaluación del polinomio considera utilizar un algoritmo
 * recursivo que implemente el método de Horner, de manera que
 *  "e + dx + cx^2 + bx^3 + ax^4"
 * puede evaluarse en un valor cualquiera x teniendo en cuenta que es equivalente a
 * "e + (d + (c + (b + (a)x)x)x)x".
 */
//int evaluar(TPolinomio p,int x){

//}
