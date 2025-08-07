@login
Feature Transferencias bancarias

Scenario: Login exitoso
    Given que el cliente accede a página del login
    When ingresa credenciales válidas
    Then accede a su cuenta bancaria


Scenario : Comletar datos del nombre de cliente
    Given que el usuario ha iniciado sesión
    When completa   datos de cliente
    Then  ver el mensaje de datos guardados


