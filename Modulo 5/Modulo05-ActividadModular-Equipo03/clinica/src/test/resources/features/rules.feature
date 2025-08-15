@reglas
Feature: Validación de reglas clínicas
  Scenario Outline: Validación de reglas clínicas
    Given el médico inició sesión
    When ingresa el nombre <name>, el diagnostico <diagnosis>, la edad <age> y el tratamiento <treatment>
    And intenta guardar la ficha
    Then se muestran los mensajes de error <message>

    Examples:
      | name        | diagnosis  | age | treatment   | message                                                        |
      | Juana Perez | Otro       | 10  | Medicamento | Para menores de 12 años, el diagnóstico debe ser "Pediátrico". |
      | Juana Perez | Pediátrico | 10  |             | El tratamiento es obligatorio para guardar la ficha.           |