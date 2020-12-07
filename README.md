# Proyecto Final: Emulador de CPU

## Instalación
Proceso de instalación de IDE: 
Primero es necesario descargar Java SE 11, el cual se puede descargar [aquí](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) dependiendo la versión de la máquina con la que se trabaja.
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

Una vez ejecutado, el programa nos da la opción de entrar a modo manual ingresando 1, donde nos permite introducir operaciones manualmente, o en automático ingresando, que lee los registros para ejecutar las instrucciones.
![3](/images/3.png)

Si se ingresó 1 para entrar a modo manual, se debe ingresar la operación a realizar utilizando ACC para referirse al acumulador y R(Número de registro a utilizar, ejemplo: R0 o R4). Al ingresar la operación, podemos observar los valores en PC, MAR, ROM y en el acumulador para facilidad de uso, donde podemos continuar ingresando operaciones hasta cerrar el programa.
![4](/images/4.png)

Si se ingresó 2 para entrar a modo automático, el programa realizará las intrucciones y valores de los registros empezando por R0 leyendo cada registro, continuando recursivamente hasta que el programa sea detenido.

![auto](/images/auto.png)

## Memoria Virtual, registros y banderas

Esta versión del emulador cuenta con 16 registros funcionales, pero solo se hace uso de los primeros ocho para la ejecución de acciones e instrucciones integradas dentro del programa. La implementación de banderas no fue necesaria para esta versión del emulador y respecto a la memoria virtual. Y finalmente, en esta versión se ha implementado una memoria de tipo ROM (memoria de solo lectura). 

## Conjunto de instrucciones

| CODIGO DE OPERACIÓN | BINARIO | TIPO              | CATEGORIA             | DESCRIPCIÓN                                                                       |
|---------------------|---------|-------------------|-----------------------|-----------------------------------------------------------------------------------|
| ADD                 | 000     | Inmediato/Directo | Operación de ALU      | Suma un número decimal o el valor de registro al acumulador.                      |
| SUB                 | 001     | Inmediato/Directo | Operación de ALU      | Resta un número decimal o el valor de registro al acumulador.                     |
| AND                 | 010     | Inmediato/Directo | Operación de ALU      | Operación de Bitwise AND al acumulador con un número decimal o valor de registro. |
| OR                  | 011     | Inmediato/Directo | Operación de ALU      | Operación de Bitwise OR al acumulador con un número decimal o valor de registro.  |
| MOV                 | 100     | Directo           | Transferencia de dato | Transfiere el valor de un registro al acumulador.                                 |
| NOT                 | 101     | Inmediato/Directo | Operación de ALU      | No realiza nada.                                                                 

## Formato de datos

El manejo de la información se lleva a cabo en arreglos de variables de tipo booleano. Se han implementado funciones para convertir datos dentro de arreglos de tipo booleano a datos en sistema binario y viceversa.

En el caso del modo manual el usuario puede ingresar los datos de 3 diferentes maneras:
De forma decimal, indicando con un ‘#’ al inicio del dato (Ej. MOV ACC, #1)
De forma hexadecimal, indicando con una ‘h’ al final del dato (EJ. MOV ACC, ah)
Con el valor de un registro, es decir, con un valor almacenado en la ROM (Ej. MOV ACC, R0)

En el caso del modo automático siempre se obtienen los datos e instrucciones en base al contenido de la ROM.


| Tipo de instrucción | Primer segmento     | Segundo segmento  | Tercer segmento | Cuarto segmento   | Quinto segmento | S                  |
|---------------------|---------------------|-------------------|-----------------|-------------------|-----------------|--------------------|
| Inmediato(Decimal)  | Código de operación | Espacio en blanco | ACC,            | Espacio en blanco | #               | Número decimal     |
| Inmediato(Hexadecimal)| Código de operación | Espacio en blanco| ACC,           | Espacio en blanco | h               | Número hexadecimal     |
| Directo             | Código de operación | Espacio en blanco | ACC,            | Espacio en blanco | R               | Número de registro |

## Ciclo de búsqueda-decodificación-ejecución (máquina de estado)

![ciclo](/images/ciclo.png)

## Problemas de temporización y rendimiento

Lo único que es importante mencionar en esta sección es que al elegir la opción de “AutoMode” se genera un retraso de 10s entre cada acción, con el propósito de permitirle al usuario el identificar las acciones realizadas con mayor claridad, esperando que obtenga un mejor aprendizaje o conocimiento respecto a los modos de operación y la lógica del emulador en esta funcionalidad más amigable al usuario.

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
