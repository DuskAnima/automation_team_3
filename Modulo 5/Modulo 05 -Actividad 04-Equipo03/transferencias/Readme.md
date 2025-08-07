Instrucciones:
1. Crea un archivo transferencias.feature con un escenario outline que valide tres tipos de transferencias:
exitosa, monto negativo y cuenta inválida.
2. Usa este formato base:
Scenario Outline: Validar transferencias bancarias
 Given que el usuario ha iniciado sesión
 When transfiere <monto> a la cuenta <cuenta>
 Then debería ver el mensaje "<resultado>"
 Examples:
 | monto | cuenta | resultado |
 | 100 | 123456789 | Transferencia exitosa |
 | -50 | 123456789 | Monto inválido |
 | 100 | 000000000 | Cuenta destino no válida |
3. Crea los Steps Java correspondientes que impriman en consola los valores y simulen una validación
lógica.
4. Agrega un segundo escenario que utilice una tabla DataTable para capturar datos de formulario como:
When completa los siguientes datos:
 | campo | valor |
 | nombre | Carla |
 | apellido | Soto |
 | email | carla@qa.com |
 | edad | 35 |
5. Implementa un Step Definition que recorra la tabla e imprima cada valor en consola.
