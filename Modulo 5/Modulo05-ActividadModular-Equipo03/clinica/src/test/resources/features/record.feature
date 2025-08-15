  @ficha
  Scenario: Carga de ficha clínica
    Given el médico inició sesión
    When ingresa los datos de la ficha:
      | nombre      | John Doe                     |
      | diagnostico | Sindrome del tunel del carpo |
      | edad        | 33                           |
      | tratamiento | Terapia de rehabilitación    |
    And guarda la ficha
    Then se muestra el mensaje de confirmación "Ficha registrada con éxito."