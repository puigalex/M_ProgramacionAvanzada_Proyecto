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

