@update
Feature Transferencias bancarias

Scenario : Comletar datos del nombre de cliente
    Given que el usuario ha iniciado sesión
    When completa   datos de cliente
        | monto | cuenta | resultado |
        | 100 | 123456789 | Transferencia exitosa |
        | -50 | 123456789 | Monto inválido |
        | 100 | 000000000 | Cuenta destino no válida |
    Then  ver el mensaje de datos almacenados