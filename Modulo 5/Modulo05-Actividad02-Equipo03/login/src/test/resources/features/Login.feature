Feature: Inicio de sesión
  Scenario: Usuario accede con credenciales válidas
    Given que el usuario está en la página de login
    When ingresa usuario "demoUser" y clave "demoPass"
    Then debería ver el mensaje "Acceso concedido"