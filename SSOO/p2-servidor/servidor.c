/*
 *  Práctica 2 - Aplicaciones multiproceso en entornos POSIX.
 *  Codificación de un servidor.
 *
 *  Alumnos:
 *  Álvaro Miguel Arroyo González   -   alvaromiguel.arroyo.gonzalez@alumnos.upm.es
 *  Bárbara Muñoz Ibáñez            -   b.mibanez@alumnos.upm.es
*/

/* Bibliotecas */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>
#include <unistd.h>
#include <sysexits.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <sys/wait.h>
#include <errno.h>
#include <signal.h>

/* Definición de constantes */
#define RES_FIN_CORRECTO 1
#define RES_ERROR 2
#define RES_DATO_INVALIDO 3

/* Prototipos de funciones */
/*
 *  activar_escucha: Establece la escucha en un puerto y con una dirección determinada.
 *  Parámetros de entrada:
 *      puerto_escucha: Entero. Indica el puerto en el que se realiza la escucha.
 *  Valor devuelto por la función:
 *      Entero: Devuelve 0 si tiene éxito, -1 si hay error.
*/
int activar_escucha(const int puerto_escucha);

/*
 *  aceptar_cliente: Acepta la conexión a un socket.
 *  Parámetros de entrada:
 *      s_escucha: Entero. Indica si hay o no escucha.
 *  Valor devuelto por la función:
 *      Entero: Devuelve 0 si tiene éxito, -1 si hay error.
*/
int aceptar_cliente(const int s_escucha);

/*
 *  atender_cliente: si existe un cliente se crea un proceso hijo para atenderle.
 *  Parámetros de entrada:
 *      s_cliente: Entero. Valida la existencia de un cliente.
*/
void atender_cliente(const int s_cliente);

/*
 *  procesar_sesion: Comienza la sesión de atender al cliente, realiza las operaciones pertinentes y, si el cliente
 *  no quiere continuar la sesión, cierra la sesión.
 *  Parámetros de entrada:
 *      s_cliente: Entero. Valida la existencia de un cliente.
 *  Valor devuelto por la función:
 *      Entero: Devuelve 0 si tiene éxito, -1 si hay error.
*/
int procesar_sesion(const int s_cliente);

/*
 *  buscar_primo_superior: opera para encontrar el número primo inmediatamente superior
 *  al número introducido desde el cliente.
 *  Parámetros de entrada:
 *      n: Long. Número arbitrario.
 *  Valor devuelto por la función:
 *      Long: Devuelve un número primo inmediatamente superior a la entrada proporcionada.
*/
long buscar_primo_superior(const long n);

/*
 *  es_primo: valida que el número introducido sea primo.
 *  Parámetros de entrada:
 *      n: Long. Número arbitrario.
 *  Valor devuelto por la función:
 *      Entero: Devuelve 1 si es primo, 0 en caso de no serlo.
*/
int es_primo(const long n);

/*
 *  esperar_muerte_hijo: espera hasta recibir el pid de un proceso hijo muerto.
 *  Parámetros de entrada:
 *      sig: Entero. Valor de la señal.
*/
void manejador_SIGCHLD(int sig);


/* Función principal */
int main(int argc, char** argv)
{
    // Señal para la captación de zombies
    signal(SIGCHLD, manejador_SIGCHLD);

	int puerto_escucha, s_escucha, s_cliente;

	if (argc == 1)
		puerto_escucha = 1234;
	else
		puerto_escucha = atoi(argv[1]);

	s_escucha = activar_escucha(puerto_escucha);
	if (s_escucha == -1) {
		fprintf(stderr, "ERROR: no se ha podido activar la escucha en el puerto %d.\n", puerto_escucha);
		fprintf(stderr, "Recomendaciones: mata todos los procesos de pruebas anteriores (servidor y nc). Como alternativa, prueba con un puerto diferente (> 1024).\n");
		exit(EX_UNAVAILABLE);
	}
	printf("INFO PID %d: escucho en el puerto %d.\n", getpid(), puerto_escucha);

	do {
		s_cliente = aceptar_cliente(s_escucha);
		if (s_cliente != -1) {
			atender_cliente(s_cliente);
		}
	} while (s_cliente != -1);
    close(s_escucha);
	exit(EX_OK);
}

void atender_cliente(const int s_cliente)
{
    int res_proc;

    switch (fork()){
        case (pid_t)-1: // Caso 1: Error en la llamada al fork()
            perror ("No se ha podido crear el hijo.");    //mensaje de error
            break;
        case (pid_t)0:  //Caso 2: Código del hijo
            res_proc = procesar_sesion(s_cliente);
            if (res_proc == RES_DATO_INVALIDO) {
                printf("AVISO: el cliente ha introducido un dato inválido.\n");
            }
            exit(0);    // Aquí el hijo pasa a estado zombie.
        default:        //Caso por defecto: Código del padre
            fclose(fdopen(s_cliente, "r+"));
            break;
    }
}

int procesar_sesion(const int s_cliente)
{
	FILE* f_cliente;
	char linea[100];
	long numero, primo_superior;
	int fin;

	printf("INFO PID %d: atiendo a cliente nuevo.\n", getpid());
	if ((f_cliente = fdopen(s_cliente, "r+")) == NULL) {
		perror("fdopen");
		return RES_ERROR;
	}
	setlinebuf(f_cliente);
	fprintf(f_cliente, "Bienvenido. Mi PID es %d.\n", getpid());
	do {
		fprintf(f_cliente, "Número: ");
		fin = (fgets(linea, sizeof linea, f_cliente) == NULL || strcmp(linea, "fin\n") == 0);
		if (! fin) {
			numero = atol(linea);
			if (numero > 0) {
				fprintf(f_cliente, "Calculando...\n");
				primo_superior = buscar_primo_superior(numero);
				fprintf(f_cliente, "%ld es el primer primo superior a %ld.\n", primo_superior, numero);
			}
		}
	} while (! fin && numero > 0);
	fprintf(f_cliente, "Adios\n");
	fflush(NULL);
	fclose(f_cliente);
	printf("INFO PID %d: fin de la sesión.\n", getpid());
	if (fin)
		return RES_FIN_CORRECTO;
	else
		return RES_DATO_INVALIDO;
}


int activar_escucha(const int puerto_escucha)
{
	int s_escucha;
	struct sockaddr_in sa_escucha;

	if ((s_escucha = socket(PF_INET, SOCK_STREAM, 0)) == -1) {
		perror("socket");
		return -1;
	}
	bzero(&sa_escucha, sizeof sa_escucha);
	sa_escucha.sin_family      = AF_INET;
	sa_escucha.sin_port        = htons(puerto_escucha);
	sa_escucha.sin_addr.s_addr = htonl(INADDR_ANY);
	if (bind(s_escucha, (struct sockaddr*) &sa_escucha, sizeof sa_escucha) == -1) {
		perror("bind");
		return -1;
	}
	if (listen(s_escucha, 10) == -1) {
		perror("listen");
		return -1;
	}
	return s_escucha;
}

int aceptar_cliente(const int s_escucha)
{
	int s_cliente;

	s_cliente = accept(s_escucha, NULL, NULL);
	if (s_cliente == -1)
		perror("accept");
	return s_cliente;
}

long buscar_primo_superior(const long n)
{
	long p;

	for (p = n+1; ! es_primo(p); p++);
	return p;
}

int es_primo(const long n)
{
	long i;

	for (i = 2; i < n; i++)
		if (n % i == 0)
			return 0;
	return 1;
}

void manejador_SIGCHLD(int sig)
{
    int estado;
    pid_t pid_hijo;

    do{
        // wait3 es no bloqueante.
        pid_hijo = wait3(&estado, WNOHANG, NULL);
    } while (pid_hijo > 0);
}

// ENTREGA FINAL.
