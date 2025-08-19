Feature: Inicio de sesión médico

  @login
  Scenario: Inicio de sesión exitoso
    Given que el médico abre la página de inicio de sesión
    When ingresa el usuario "doctor" y la contraseña "password"
    Then el médico accede a la web "https://clinica-modular.netlify.app/ficha.html"

  @login
  Scenario: Inicio de sesión con credenciales incorrectas
    Given que el médico abre la página de inicio de sesión
    When ingresa el usuario "wrong user" y la contraseña "wrong password"
    Then se ve el mensaje de error "Credenciales inválidas. Intenta de nuevo."