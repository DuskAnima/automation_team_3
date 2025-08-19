Feature: Registro de fichas clínicas
  @ficha
  Scenario: Carga de ficha clínica
    Given que el médico inició sesión
    When ingresa los datos de la ficha:
      | nombre      | John Doe                     |
      | diagnostico | Sindrome del tunel del carpo |
      | edad        | 33                           |
      | tratamiento | Terapia de rehabilitación    |
    Then se muestra el mensaje "Ficha registrada con éxito."