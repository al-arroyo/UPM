/*
    Práctica 2 - Aplicaciones multiproceso en entornos POSIX.
    Procesamiento en paralelo. Controlador.

    Alumnos:
    Álvaro Miguel Arroyo González   -   alvaromiguel.arroyo.gonzalez@alumnos.upm.es
    Bárbara Muñoz Ibáñez            -   b.mibanez@alumnos.upm.es
*/

/* Bibliotecas */
#include <sys/param.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sysexits.h>
#include <stdbool.h>
#include <sys/wait.h>
#include <time.h>
#include <errno.h>
#include <signal.h>

/* Definición de constantes */
#define CORES 4
#define VIVO 1
#define MUERTO 0


/* Definición de tipos y estructuras */
/*
 * Estructura que define el ID de un proceso hijo y su estado.
 */
typedef struct {
    pid_t pid;
    int estado;
}tContenedor;


/* Prototipos de funciones*/
/*
    uso: Indica la forma en la que se emplea el ejecutable y se muestra un ejemplo.
*/
static void uso(void);

/*
 *  convertir: Convierte las imágenes.
 *  Parámetros de entrada:
 *      fich_imagen: Array de caracteres que almacena la ruta de origen de las imágenes.
 *      dir_resultados: Array de caracteres que almacena la ruta de destino de las imágenes.
*/
static void convertir(const char* fich_imagen, const char* dir_resultados);

/*
 *  info: Informa sobre algunas características de ejecución.
 *  Parámetros de entrada:
 *      argc: Entero. Número de argumentos pasados a la función principal.
 *      cores: Entero. Número de procesadores disponibles.
 */
static void info(int argc, int cores);

/*
 *  esperar_muerte_hijo: Espera la muerte de un hijo.
 *  Valor devuelto por la función:
 *      tContenedor: información sobre el pid y el estado del proceso hijo.
 */
static tContenedor esperar_muerte_hijo(void);

/*
 *  difuminar_imagenes: Método para difuminar imágenes proporcionadas desde un directorio.
 *  Parámetros de entrada:
 *      fich_imagen: Array de caracteres que almacena la ruta de origen de las imágenes.
 *      dir_resultados: Array de caracteres que almacena la ruta de destino de las imágenes.
 *  Valor devuelto por la función:
 *      tContenedor: información sobre el pid y el estado del proceso hijo.
 */
static tContenedor difuminar_imagenes(const char* fich_imagen, const char* dir_resultados);

/*
 *  manejador_SIGTERM: Manejador para la señal SIGTERM.
 *  Parámetros de entrada:
 *      sig: Entero. Valor de la señal enviada.
 */
static void manejador_SIGTERM(int sig);

/* Variables globales */
static int sigterm = 0;

/*
 * Proceso principal.
 */
int main(int argc, char** argv)
{
	const char* dir_resultados; // Dirección de la variable del directorio de resultados
    tContenedor pids[CORES];    // Estructura para almacenar PIDs en un array
	tContenedor pid_hijo;       // Estructura de un PID
	tContenedor t_hijo_muerto;  // Muestra el PID de un proceso hijo muerto
    int index = 0;              // Índice
    int contador_muertos = 0;   // Contador de hijos muertos
    bool flag = false;          // Flag que indica el final del bucle

	// Garantiza que se han pasado los argumentos mínimos necesarios
	if (argc < 3) {
		uso();
		exit(EX_USAGE);
	}

	// Captura señal SIGTERM empleando el manejador
    signal(SIGTERM, manejador_SIGTERM);

    // Salida estándar con información sobre características del programa.
	info(argc, CORES);

	// Directorio con los resultados
	dir_resultados = argv[1];



	// Bucle para la lectura de las imágenes pasadas como argumento.
	for (int i = 2; i < argc; i++) {
        // Indicador de llegada del último argumento.
        if (i == argc -1) {
            flag = true;
        }
        //Matar hijos al recibir SIGTERM
        if(sigterm)
        {
            index = 0;
            while (pids[index].estado == VIVO)
            {
                kill(SIGTERM, pids[index].pid);
                pids[index].estado = MUERTO;
                printf("Matando al hijo %d\n", pids[index].pid);
                index --;
                contador_muertos++;
            }
            i = argc;
        }
        else
        {
            printf("---------------------\nPROCESO NÚMERO: %d\n---------------------\n", i - 1);
            // Llamada a la función dada para difufimar las imágenes pasadas por argumento.
            pid_hijo = difuminar_imagenes(argv[i], dir_resultados);

            // Registro del PID hijo en el arreglo.
            pids[index].pid = pid_hijo.pid;
            pids[index].estado = pid_hijo.estado;
            printf("El pid del hijo es: %d\n", pids[index].pid);
            index++;


            // Verificación para no crear más procesos hijos que procesadores disponibles
            if (index == CORES || flag) {
            // Bucle para procesar todos los hijos muertos
                while (index > 0) {
                    t_hijo_muerto = esperar_muerte_hijo();
                    printf("El pid del hijo que ha muerto es: %d\n", t_hijo_muerto.pid);
                    index--;
                    contador_muertos++;
                }
            }
        }
    }

    // Comprobación de que se han procesado todas las imágenes
    if (contador_muertos == argc - 2) {
        printf("\n----------------\nSe han procesado todas las imágenes\n----------------\n");
    } else {
        printf("\n----------------\nError al procesar alguna de las imágenes\n----------------\n");
    }

	exit(EX_OK);
}


/*
 *  Indica la forma en la que se emplea el ejecutable y se muestra un ejemplo.
 */
static void uso(void)
{
	fprintf(stderr, "\nUso: paralelo dir_resultados fich_imagen...\n");
	fprintf(stderr, "\nEjemplo: paralelo difuminadas orig/*.jpg\n\n");
}
/*
 * Convierte las imágenes
 */
static void convertir(const char* fich_imagen, const char* dir_resultados)
{
	const char* nombre_base;
	char nombre_destino[MAXPATHLEN];

	nombre_base = strrchr(fich_imagen, '/');
	if (nombre_base == NULL)
		nombre_base = fich_imagen;
	else
		nombre_base++;
	snprintf(nombre_destino, sizeof(nombre_destino), "%s/%s", dir_resultados, nombre_base);
    execlp("convert","convert",fich_imagen,"-blur","0x8", nombre_destino, NULL);
}

/*
 * Proporciona información de las propiedades del programa.
 */
static void info(int argc, int cores) {
    printf("----------------------------\nNúmero de argumentos: %d\n", argc);
    printf("Número de procesadores: %d\n----------------------------\n", cores);
}

/*
 * Espera la muerte de un hijo
 */
static tContenedor esperar_muerte_hijo(void)
{
    int estado;
    tContenedor t_hijo;
    do{
        t_hijo.pid = wait(&estado);
    } while (t_hijo.pid == -1 && errno == EINTR);
    t_hijo.estado = MUERTO;

    return t_hijo;
}

/*
 *  Método para difuminar imágenes proporcionadas desde un directorio.
 */
static tContenedor difuminar_imagenes(const char* fich_imagen, const char* dir_resultados)
{
    tContenedor pid_hijo;   // Crea la estructura para el pid hijo
    pid_t pid;
    fflush(NULL);
    pid=fork();

    // Almacenaje en estructura
    pid_hijo.pid = pid;
    pid_hijo.estado = VIVO;

    switch (pid){
        case (pid_t)-1:         // Error del fork()
            perror ("fork");    //mensaje de error
            break;
        case (pid_t)0:          //Código del hijo
            convertir(fich_imagen, dir_resultados);
            exit(0);
        default:                //Código del padre
            break;
    }
    return pid_hijo;
}

static void manejador_SIGTERM(int sig)
{
    printf("Valor de la señal enviada es: %d", sig);
    sigterm = 1;
    printf("\nRecibida señal SIGTERM precediendo a matar hijos\n");
}

// ENTREGA FINAL. Incluye cuestión opcional 14.
