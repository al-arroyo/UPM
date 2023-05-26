#include <sys/param.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sysexits.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <errno.h>

/*****************************************
 *
 * Alumno: alvaromiguel.arroyo.gonzalez@alumnos.upm.es  
 *
 ****************************************/

static void uso ( const char* nombre );
static void procesar ( const char* problema, const char* programa );
static void mandarTerminar ( void );
static void manejador_SIGTERM(int sig);

int sigterm=0;

int main ( const int argc, const char* argv[] )
{
	int estado;
	pid_t pid_hijo;
	if ( argc < 3 )
	{
		uso ( argv[0] );
		exit ( EX_USAGE );
	}
	for ( int i = 2; i < argc; i++ )
		procesar ( argv[1], argv[i] );
	/* esperar a que termine uno de ellos */
 	do{
        pid_hijo = wait(&estado);
    } while (pid_hijo == -1 && errno == EINTR);
	/* ordenar terminar a todos los programas que aún no hayan terminado */
	signal(SIGTERM, manejador_SIGTERM);
	mandarTerminar();
	/* esperar a que terminen todos los programas */
	 do{
        // wait3 es no bloqueante.
        pid_hijo = wait3(&estado, WNOHANG, NULL);
    } while (pid_hijo > 0);
	/* terminar con éxito */
	exit ( EX_OK );
}

static void uso ( const char* nombre )
{
	fprintf ( stderr, "Uso: %s problema aplicación...\n", nombre );
	fprintf ( stderr, "Ejemplo: %s 758hy2j9dv987 ./aplicación*\n", nombre );
}

/* Función de ejemplo que ejecuta el programa en primer plano */
static void procesar ( const char* problema, const char* programa )
{
	execl(programa, programa ,problema, NULL);
}

/* ordena terminar a todos los hijos que aún no hayan terminado */
static void mandarTerminar ( void )
{
	signal ( SIGTERM, SIG_IGN );
	kill ( -getpgid(0), SIGTERM );
}
static void manejador_SIGTERM(int sig)
{
  mandarTerminar();
}

