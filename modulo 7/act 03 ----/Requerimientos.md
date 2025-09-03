Sistema bajo prueba
Sitio: https://blazedemo.com
Flujo de negocio:
1. Home: GET /
2. Buscar vuelo: POST /reserve.php (envía fromPort, toPort)
3. Seleccionar vuelo: POST /purchase.php (envía datos correlacionados del vuelo)
4. Completar compra: POST /confirmation.php (envía datos del pasajero)
Nota: No se permite “grabar” el flujo con proxy. Deben modelarlo manualmente.
ACTIVIDAD:
Material entregado
CSV: blaze_users_fixed.csv
Encabezados obligatorios (en este orden):
inputName,address,city,state,zipCode,cardType,creditCardNumber,creditCardMonth,creditCardYear,nameOnCard,fromPort,toPort
Restricciones de valores:
- fromPort ∈ {Paris, Philadelphia, Boston, Portland, San Diego, Mexico City, São Paolo}
- toPort ∈ {Buenos Aires, Rome, London, Berlin, New York, Dublin, Cairo}
- cardType ∈ {visa, amex, dinersclub}



Requisitos del Test Plan (mínimos para aprobar)
1) Thread Groups:
(OK)- TG – Smoke: 10 usuarios, ramp-up 10 s, loop 1. 
- TG – Load: 50 usuarios, ramp-up 30 s, loop 2.
2) Configuración global:
(OK)- HTTP Request Defaults: Protocol=https, Server Name=blazedemo.com.
(OK)- HTTP Cookie Manager y HTTP Cache Manager.
(OK)- CSV Data Set Config apuntando al CSV entregado (ignorar encabezado).
3) Transacciones (usar Transaction Controllers):
(OK)- 1. Home → 1 sampler GET /.
(OK)- 2. Buscar Vuelo → 1 sampler POST /reserve.php con parámetros fromPort=${fromPort}, toPort=${toPort}.
- 3. Seleccionar Vuelo → 1 sampler POST /purchase.php que envíe solo valores correlacionados desde la respuesta de reserve.php (no hardcodear).
(OK)- 4. Completar Compra → 1 sampler POST /confirmation.php con parámetros desde CSV (inputName, address, city, state, zipCode, cardType, creditCardNumber, creditCardMonth, creditCardYear, nameOnCard).
Opcional: marcar Remember me. Añadir un sufijo único a nameOnCard (UUID) para evitar colisiones.
4) Correlación (obligatoria):
- En la respuesta HTML de reserve.php extraer cinco valores: flight, price, airline, fromPort (hidden), toPort (hidden).
- Usar CSS Selector Extractor o Regular Expression Extractor. No se aceptan valores hardcodeados.
5) Timers:
- Gaussian Random Timer en el Thread Group → Constant Offset: 1200 ms, Deviation: 400 ms.
6) Assertions:
- En 4. Completar Compra: Texto contiene “Thank you for your purchase” (cubrir respuesta final tras redirección).
- Código HTTP = 200.
7) Buenas prácticas obligatorias:
- Enviar datos por Parameters (no Body Data, ni multipart).
- Follow Redirects: ON, Redirect Automatically: OFF.
- No duplicar listeners pesados en pruebas de carga.
(OK)- No modificar el CSV.


Criterios de aceptación
- Funcional: flujo completo sin errores; assertions en verde.
- Correlación: en POST /purchase.php → Request Body se ven valores reales (no ${variable} ni %24%7B…%7D).
- Rendimiento (referencia): p95 de “4. Completar Compra” < 2.5 s; Error % ≤ 1%; Throughput estable.
Entregables
- BlazeDemo.jmx (o nombre que usen) con ambos Thread Groups.
- blaze_users_fixed.csv (el entregado, sin cambios).
- results.jtl.
- Carpeta html-report/ generada por JMeter.
- README.md (máx. 1 página) con ejecución GUI/CLI, capturas de Request Body de purchase.php y Summary Report, y 2–3 conclusiones.
Comando CLI sugerido:
jmeter -n -t BlazeDemo.jmx -l results.jtl -e -o html-report