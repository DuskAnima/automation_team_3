# Simulación de Pruebas funcionales iniciales con JMeter #

## Settings ##

### Thread Group ###

- **web**: blazedemo.com
- **Number of threads**: 1
- **Ramp-up period**: 1
- **Loop count**: 1

### HTTP Requests endpoints ###

- **GET: Página Principal**: /
- **POST: Seleccionar Vuelo**: /reserve.php

### Assertions ###

- **Response**: 200 OK

- **Errores** : 0%

## Análisis Final de Resultados ##

### Tiempo promedio de respuesta ###

- **GET: Página Principal**: 1762 ms 
- **POST: Seleccionar Vuelo**: 490 ms
- **Promedio total**: 1126 ms


## Análisis ##

### Tiempo de respuesta ###

- El GET de la página principal muestra un tiempo relativamente alto (1762 ms). Esto puede deberse a la carga inicial de recursos, como HTML, CSS, JS o imágenes.

- El POST de selección de vuelo es considerablemente más rápido (490 ms), lo que indica que las operaciones transaccionales simples en el backend responden bien.

- El promedio global de 1126 ms se encuentra dentro de un rango aceptable para una primera prueba funcional, pero podría optimizarse, sobre todo en el endpoint principal.

### Disponibilidad y estabilidad ###

- Ambos endpoints generaron generaron 0% de errores, pasando todas las aserciones con respuesta 200 OK.

### Cobertura ###

- La prueba actual utilizó un único hilo y un loop, lo cual verifica la funcionalidad pero no simula carga de múltiples usuarios.

## Recomendaciones ##

- Existe una diferencia notable entre los tiempos de carga de la página principal (1.7 segundos) y los de selección de vuelo (0.5 segundos), la optimización debería centrarse en la página principal. 

- Esta diferencia podría deberse a consultas iniciales de la base de datos, renderizado o dependencias externas, se sugiere una revisión de estos recursos.