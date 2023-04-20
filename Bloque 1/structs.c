#include <stdio.h>
#include <stdlib.h> 
typedef struct Persona Persona;
struct Persona{
    char nombre[25];
    int edad;
};

int main(){
    //struct Persona
    Persona ps;

    //printf("%u, %u", &c[0], c);
    //scanf("%c", &car);
    printf("Introduce el nombre:");
    scanf("%s",ps.nombre);
    printf("el nombre es: %s \n",ps.nombre);

    //Lista de persona
    Persona listaP[25];
    int l=0;
    printf("Introduce como mucho 25 personas: ");
    while(scanf("%d %s", &listaP[l].edad, listaP[l].nombre) !=0 ){
        l++;
    }

    printf("Longitud de la lista %d \n", l);

    for(int i = 0; i < l;i++){
        printf("Nombre: %s, Edad: %d \n", listaP[i].nombre, listaP[i].edad);
    }
}