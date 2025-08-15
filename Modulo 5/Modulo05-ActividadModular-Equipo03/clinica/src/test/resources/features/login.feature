@login
Feature: Inicio de sesión médico

  Scenario Outline: Casos de inicio de sesión
    Given que el médico abre la página de inicio de sesión
    When ingresa el usuario <user> y la contraseña <password>
    Then el médico ve el resultado <result>

    Examples:
      | user       | password       | result                                    |
      | doctor     | password       | Ficha Clínica                             |
      | wrong user | wrong password | Credenciales inválidas. Intenta de nuevo. |
