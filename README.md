# Proyecto Final: Emulador de CPU

## Instalación
Proceso de instalación de IDE: 

Si el usuario no cuenta con un IDE, puede entrar a la siguiente página para instalar [Visual Studio Code Download Visual Studio Code - Mac, Linux, Windows](https://code.visualstudio.com/download). Esta herramienta gratuita desarrollada por Microsoft es muy sencilla de utilizar y permite ejecutar archivos de Java si se siguen los pasos de forma correcta.  

Una vez en la página que se anexó anteriormente, el usuario debe de elegir la versión apropiada para la máquina con la que esté trabajando. Después de descargar y ejecutar los protocolos de instalación, el usuario puede de inmediato modificar y crear archivos dentro del IDE. Lo que se debe de hacer en primera instancia es ubicar la pestaña de extensiones que se ubica del lado derecho de la ventana dentro de la aplicación. Como se muestra en la imagen a continuación.

![1](/images/1.png)]

Una vez dentro del menú de extensiones, dentro de la barra buscadora puede ingresar “Java” para encontrar la extensión desarrollada por Microsoft para ejecutar archivos del tipo “.java”.

![2](/images/2.png)

La extensión que se muestra en la primera posición en orden descendente en la captura de pantalla a la izquierda muestra la extensión llamada “Debugger for Java”, que es la que debe de instalarse para hacer uso de este emulador.

El proceso de instalación consiste solamente de entrar a la extensión mencionada desde el menú de extensiones y oprimir el botón “instalar” que se encuentra en la primera sección del contenido de la extensión. O basta con oprimir el botón verde con el mismo nombre desde la barra de extensiones.

Finalmente lo que resta es abrir el archivo de “RUN.java” desde el directorio en donde se ubique dependiendo de los ajustes de su máquina. Una vez abierto el archivo, podrá observar el código del emulador y al oprimir la tecla F5 en el caso de Windows o Command + F5 en el caso de MacOS, el emulador comenzará el proceso de ejecución de forma inmediata. De forma alterna, si el usuario oprime el botón del triángulo verde (Play) en la sección superior derecha de la ventana (debajo de los botones para maximizar, minimizar o cerrar el programa) se ejecuta el emulador.


## Configuración

Para configurar la ROM se tiene que dirigir a su clase donde se inicializan los registros.

![5](/images/5.png)

Aquí se pueden modificar los valores de los registros (los primeros ocho) utilizando booleanos para expresar un uno o cero.

## Uso

![3](/images/3.png)
![4](/images/4.png)
## Conjunto de instrucciones

| CODIGO DE OPERACIÓN | TIPO              | CATEGORIA             | DESCRIPCIÓN                                                                       |
|---------------------|-------------------|-----------------------|-----------------------------------------------------------------------------------|
| ADD                 | Inmediato/Directo | Operación de ALU      | Suma un número decimal o el valor de registro al acumulador.                      |
| SUB                 | Inmediato/Directo | Operación de ALU      | Resta un número decimal o el valor de registro al acumulador.                     |
| AND                 | Inmediato/Directo | Operación de ALU      | Operación de Bitwise AND al acumulador con un número decimal o valor de registro. |
| OR                  | Inmediato/Directo | Operación de ALU      | Operación de Bitwise OR al acumulador con un número decimal o valor de registro.  |
| MOV                 | Directo           | Transferencia de dato | Transfiere el valor de un registro al acumulador.                                 |
| NOT                 | Inmediato/Directo | Operación de ALU      | No realiza nada.                                                                  |

## Formato de datos

| Tipo de instrucción | Primer segmento     | Segundo segmento  | Tercer segmento | Cuarto segmento   | Quinto segmento | S                  |
|---------------------|---------------------|-------------------|-----------------|-------------------|-----------------|--------------------|
| Inmediato           | Código de operación | Espacio en blanco | ACC,            | Espacio en blanco | #               | Número decimal     |
| Directo             | Código de operación | Espacio en blanco | ACC,            | Espacio en blanco | R               | Número de registro |

## Ciclo de búsqueda-decodificación-ejecución (máquina de estado)

## Problemas de temporización y rendimiento

| Código de operación | Tipo              | Tiempo (segundos) |
|---------------------|-------------------|-------------------|
| ADD                 | Inmediato         | 0.000193399       |
| ADD                 | Directo           | 0.0002246         |
| SUB                 | Inmediato         | 0.0002636         |
| SUB                 | Directo           | 0.000186799       |
| AND                 | Inmediato         | 0.0002422         |
| AND                 | Directo           | 0.000314801       |
| OR                  | Inmediato         | 0.0002422         |
| OR                  | Directo           | 0.000224401       |
| MOV                 | Directo           | 0.000299801       |
| NOT                 | Directo/Inmediato | 0                 |

## Integrantes

Marco Velez 26629

Néstor De La Cruz 31116

Héctor Canales 31462
