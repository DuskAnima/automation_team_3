Feature: Inicio de sesión
  Scenario: Usuario accede con credenciales válidas
    Given que el usuario está en la página de login
    When ingresa usuario "sofia" y clave "academy"
    Then debería ver el mensaje "Bienvenido(a), sofia"