# M_ProgramacionAvanzada_Proyecto
Este es el repositorio del proyecto de programación avanzada donde se realizan las siguientes actividades:
- El usuario ingresa 
    - Directorio de archivo con 2 millones de registros
    - Columnas a mantener
    - Criterio de filtrado para una columna
- Se revisa el número de núcleos del equipo
- Se divide el archivo en n partes, donde n es el número de núcleos del equipo * 10
- Se salvan los n CSV en una carpeta temporal
- Un manager crea un pool de workers y le asigna a cada uno un CSV
- Cada worker lee el CSV y filtra los registros según el criterio de filtrado y lo escribe en una variable compartida entre los workers **static**
- El manager espera a que todos los workers terminen y une los resultados en un solo archivo
- Se muestra el tiempo de ejecución
- Se elimina el directorio temporal

# Clases
## BosonHiggs.java
Clase principal que contiene el método main y la interfaz para que el usuario defina los parámetros de entrada.

## CSVHandler.java
Clase encargada de hacer operaciones con los CSV y sus directorios. Crear/borrar directorios, contar número de líneas, dividir el archivo en n partes.

## Manager.java
Clase encargada de crear el pool de workers, asignarles un CSV y esperar a que terminen para hacer join y unir los resultados en un solo archivo.

## Worker.java
Clase que extiende de Runnable para los Workers concurrentes. Cada worker lee el CSV asignado, filtra los registros y los escribe en una variable **data** compartida entre los workers. Al momento de entrar a escribir a **data** cada worker aplica un candado para evitar que otro worker escriba al mismo tiempo.

## datosFiltrados.java
Clase que contiene la variable **data** donde se escriben los registros filtrados por cada worker. Esta variable es **static** para que sea compartida entre los workers y el worker.

## Validator.java
Clase que contiene los métodos para validar los parámetros de entrada del usuario. Casos en donde el input es correcto en el tipo de variable pero no en el valor, ejemplo, introducir una columna que no existe en el archivo.



# Pendientes:
- Desarrollo:
  - [ ] Interfaz con variable data
  - [ ] Borrar carpeta TMP
  - [ ] Desarrollo en secuencial para medir tiempo
  - [ ] Revisar modificadores de acceso 
  - [ ] Integrar Try / Catch 
- Integración:
 - [ ] Salvar los CSV en TMP
 - [ ] Escribir el dataframe en un CSV final

