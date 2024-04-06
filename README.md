# AREP-Parcial2

## Arquitectura

La arquitectura que se va a manejar es la siguiente:
![img.png](imagenes/img_4.png)

### Proxy

Es la clase que se encarga de conectar al cliente con los servicios MathServices, además contiene un metodo  de balanceador de cargas(Round-Robin).

### MathServices

Es la clase que se encarga de realizar las respectivas operaciones de busqueda. 

## Diseño

### MathServices

Esta clase contiene los endopints donde se realizan las respectivas busquedas(estas estan documentadas en el codigo), dentro de estos endpoints hay funciones que se encargan de hacer las operaciones(logica). Por otro lado, la clase MathServices recibe los datos a traves de la clase HttpConnectionExample.

Estado inicial:

![img.png](imagenes/img_3.png)

Estado final:

![image](https://github.com/XimenaRodriguez20/AREP-Parcial2/assets/123812926/df10a838-9628-433e-9066-b62a2e27f5da)

### HttpConnectionExample 

Es la clase que va a permitir conectar el Proxy con el MathServices por medio de peticiones HTTP.

![image](https://github.com/XimenaRodriguez20/AREP-Parcial2/assets/123812926/98be8d1d-f762-4013-b87c-f8836d65abe0)

### Proxy 

Esta clase contiene los endpoints para cada respectiva busqueda(lineal y binaria), además hace uso de la clase HttpConnectionExample para conectar a traves de peticiones Http con el servicio MathServices, por ultimo delega a las dos instancias del servicio de ordenamiento usando un algoritmo de round-robin.

![image](https://github.com/XimenaRodriguez20/AREP-Parcial2/assets/123812926/a2d35271-788b-42d1-98af-a88d16d77249)

## Empezando

* Para obtener una copia del proyecto en su maquina local:

    - Se debe ubicar en la carpeta donde desea bajar el proyecto y le da click donde señala la flecha y esribe cmd:

      ![image](https://github.com/XimenaRodriguez20/AREP-Taller2/assets/123812926/52f8f03c-3b3e-48cf-bd2c-f7b029c2d8bb)

    - Después de esto debe escribir el siguiente comando:

        ~~~                  
        git clone https://github.com/XimenaRodriguez20/AREP-Parcial2.git
        ~~~                                                                   

* Para poder correr el código abra el IDE de su preferencia y anote los siguientes comandos en la terminal:

    - Para compilar el proyecto utilice:

        ~~~                 
            mvn clean install
        ~~~  

* En este caso debemos iniciar ambos servicios ya sea por comando o con ayuda del IDE por comando debera ingresar estos comandos:

    - Para correr el servicio Proxy:

        ~~~
            java -cp "target/classes;target/dependency/*" org.example.Proxy
        ~~~

    - Para correr el servicio MathServices:

        ~~~
            java -cp "target/classes;target/dependency/*" org.example.MathServices
        ~~~

* Si desea correr desde el cmd haga los pasos mencionados anteriormente, pero antes de eso recuerde ubicarse en la carpeta del proyecto:

    ~~~
        cd AREP-Parcial2/
    ~~~

* Para correr con ayude del IDE ingrese a la clase de Proxy, MathServices y ponga a correr cada clase como se muestra a continuación:

    Se puede dar click derecho y se pone a correr o se le da click en la flechita verde que se señala en las siguientes imagenes:
  
    ![image](https://github.com/XimenaRodriguez20/AREP-Parcial2/assets/123812926/e104b365-3c74-42c1-b6aa-e9e77af84711)

    Como podemos evidenciar cuando corremos MathServices se puede evidenciar que ya esta corriendo el otro servicio:
  
    ![image](https://github.com/XimenaRodriguez20/AREP-Parcial2/assets/123812926/5eb0fa8f-dd7a-4427-8b45-63454d53f1d0)

## Pruebas

Para realizar estas pruebas debemos ingresar a la siguiente url:

~~~
    http://localhost:4569/Cliente.html
~~~

### Implementando el cliente 

Estas pruebas son cuando el valor a buscar se encuentra dentro del array.
    
* Inicial 

![img.png](imagenes/img_2.png)

* Final 

![image](https://github.com/XimenaRodriguez20/AREP-Parcial2/assets/123812926/85fdfa04-2a34-4055-845a-0241517e134c)

Cuando no se encuentra el valor dentro del array nos retorna el respectivo valor de -1.

![image](https://github.com/XimenaRodriguez20/AREP-Parcial2/assets/123812926/7c688159-d23f-412e-969e-23f5aeeb16cc)

Ademas para aclarar en el caso de la busqueda binaria si la lista no se encuentra en orden, deja en pantalla el valor de la ultima consulta pero en el IDE aparece un mensaje, esto se realizo con la ayuda de un try, catch para evitar que saliera un error 500 de un fallo en el servidor.

![image](https://github.com/XimenaRodriguez20/AREP-Parcial2/assets/123812926/aec951fc-1062-4ffc-ad28-25b45d8e8a30)

### Busqueda lineal 

* Inicial

![img.png](imagenes/img.png)
![img.png](imagenes/img_1.png)

* Final
  
![image](https://github.com/XimenaRodriguez20/AREP-Parcial2/assets/123812926/360f6f4f-f937-4ea1-821d-6c071d81fbb5)
![image](https://github.com/XimenaRodriguez20/AREP-Parcial2/assets/123812926/6c88e274-812a-4764-aa0f-e5bbea8d119f)


### Busqueda binaria 

![image](https://github.com/XimenaRodriguez20/AREP-Parcial2/assets/123812926/82661460-d7c5-40ce-861b-67e9c8af07cd)
![image](https://github.com/XimenaRodriguez20/AREP-Parcial2/assets/123812926/1ccddfc8-c074-4ab8-bb3f-1d458c0c35dd)

## Despliegue en AWS

* [Despliegue](https://youtu.be/GEWri45_XdA)

## Autor

* **Ximena Rodriguez** 
