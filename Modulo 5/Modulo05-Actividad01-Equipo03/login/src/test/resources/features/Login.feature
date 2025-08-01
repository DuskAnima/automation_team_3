Feature: Inicio de sesión
  Scenario: Usuario accede con credenciales válidas
    Given que el usuario está en la página de login
    When ingresa usuario "admin" y clave "admin123"
    Then debería ver el mensaje "Bienvenido, admin"