/*
 * ListaReproduccion.h
 *
 */

#ifndef LISTAREPRODUCCION_H_
#define LISTAREPRODUCCION_H_

typedef struct Nodo* ListaRep;

struct Nodo{
	char cancion[30];
	ListaRep sig;
};

//PARTE 1
/* Crea una lista de reproducción vacía */
void crear(ListaRep * lista);

/* Inserta una nueva canción al final de la lista de reproducción */
void insertar(ListaRep *lista,char * cancion);

/* Libera la memoria asociada a la lista de reproducción */
void borrar(ListaRep *lista);

/* Muestra por pantalla la lista de canciones almacenada */
void mostrar(ListaRep lista);

//PARTE 2
/* Elimina de la lista la canción cuyo título coincide con el parámetro de la función.
 * Devuelve 0 si la canción no está en la lista, y 1 en otro caso */
int eliminar(ListaRep *lista, char * cancion);

/* Devuelve en el array canción el título de la primera
 * canción que está en la lista de reproducción,
 * y mueve el puntero externo de la lista a la siguiente canción.
 * Devuelve 0 si la lista está vacía, y 1, en otro caso  */
int siguiente(ListaRep *lista,char *cancion);

//PARTE 3

/* Lee del fichero de binario nombreF la lista de canciones
 * y la almacena en la lista de reproducción lista. */
void cargarListaCanciones(ListaRep* lista,char *nombreFichero);

/* Guarda en el fichero de texto nombreF la lista de canciones. 
 * Cada línea del fichero contiene el título de una canción y aparecerán 
 * en el orden de reproducción */
void guardarListaCanciones(ListaRep lista, char *nombreF);

#endif /* LISTAREPRODUCCION_H_ */
