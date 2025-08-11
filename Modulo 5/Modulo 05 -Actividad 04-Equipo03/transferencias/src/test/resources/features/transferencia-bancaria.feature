Feature: Transferencia Bancaria
  Scenario Outline: Validar transferencias bancarias
    Given que el usuario ha iniciado sesión
    When transfiere <monto> a la cuenta "<cuenta>"
    Then debería ver el mensaje "<resultado>"
    Examples:
    | monto | cuenta    | resultado                |
    | 100   | 123456789 | Transferencia exitosa    |
    | -50   | 123456789 | Monto inválido           |
    | 100   | 000000000 | Cuenta destino no válida |