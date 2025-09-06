# Simulación de Pruebas de carga con JMeter#

## Settings ##

### Thread Group ###

- **web**: blazedemo.com
- **Number of threads**: 100
- **Ramp-up period**: 5
- **Loop count**: 1

### HTTP Requests endpoints ###

- **GET: Página Principal**: /
- **POST: Seleccionar Vuelo**: /reserve.php

### Assertions ###

- **Response**: 200 OK

## Análisis Final de Resultados ##

### Tiempo promedio de respuesta ###

- **GET: Página Principal**: 1.128 ms .
- **POST: Seleccionar Vuelo**: 508 ms.
- **Promedio total**: 818 ms.

### Resultados de los assertions ###

- Ambos endpoints generaron generaron 0% de errores, pasando todas las aserciones con respuesta 200 OK.

## Conclusiones y recomendaciones ##

- Optimizar la carga de la página principal ya que su tiempo promedio es
de 1,1 segundos siendo el más alto. Ideal que pueda ser1 segundo.
o Mantener bajo control la variabilidad; esto ya que existe cierta
dispersión que se refleja en la desviación estándar