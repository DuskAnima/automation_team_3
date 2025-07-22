Testing Ágil en Proyectos de Software

Objetivo:
        Evaluar la capacidad del estudiante para aplicar de forma integrada técnicas de TDD, ATDD,
        diseño basado en principios SOLID, pruebas unitarias, uso de mocks, medición de cobertura y
        automatización de tests, desarrollando un módulo funcional de software con pruebas asociadas
        y buenas prácticas de la industria.
Contexto:
        Has sido contratado por la startup CodeWave, que está desarrollando una plataforma web para
        el manejo de reservas en centros deportivos comunitarios. Tu equipo necesita construir un
        módulo que permita:
            • Registrar canchas con nombre, tipo de deporte, y horarios disponibles.
            • Crear, modificar y cancelar reservas.
            • Verificar que una cancha no pueda ser reservada si ya está ocupada.
            • Calcular el número de reservas por día.
Tu rol como desarrollador/a es implementar este módulo aplicando TDD desde cero, utilizando
JUnit o TestNG, integrando Mockito para simular dependencias, midiendo la cobertura con
JaCoCo y asegurando un diseño limpio con principios SOLID.



Requerimientos:
Actividad 1: TDD y pruebas unitarias
        Desarrolla el módulo solicitado aplicando el ciclo TDD: Red, Green y Refactor. Comienza por
        escribir una prueba que falle, implementa el código mínimo para que pase y luego mejora el diseño
        manteniendo el test en verde. Adjunta al menos cinco pruebas unitarias relevantes que validen
        distintos comportamientos del sistema. Aplica refactorización donde sea necesario para asegurar un
        diseño limpio y mantener la legibilidad del código.
Actividad 2: Principios de diseño
        Justifica en tu código la implementación de al menos tres principios SOLID. Puedes incluir
        comentarios o explicaciones adicionales como evidencia. Además, identifica dónde aplicaste
        principios como YAGNI, KISS o DRY. La explicación debe ser concreta y basada en decisiones reales
        tomadas durante el desarrollo del módulo.
Actividad 3: Uso de mocks
        Utiliza Mockito para simular al menos una dependencia externa, como un repositorio o servicio
        auxiliar. Desarrolla pruebas unitarias que validen el comportamiento del sistema bajo prueba
        utilizando verificaciones (como verify()) y captura de argumentos (ArgumentCaptor). Asegúrate de
        reflejar cómo los mocks ayudan a aislar el comportamiento de las unidades de código.
Actividad 4: Medición de cobertura
        Integra JaCoCo a tu proyecto para medir la cobertura de código. Genera el reporte correspondiente
        y revisa los porcentajes obtenidos. Explica qué tan adecuada es la cobertura alcanzada en relación
        con los estándares comunes de calidad (por ejemplo, mínimo 80%), e identifica áreas de mejora si
        existen.
Actividad 5: ATDD y aceptación
        Redacta una historia de usuario utilizando el modelo INVEST. Define tres criterios de aceptación
        claros y medibles. Finalmente, formula un escenario de prueba usando el formato BDD: Given,
        When, Then.
Actividad 6: Comparación entre frameworks de testing
        Crea una tabla comparativa entre JUnit y TestNG, considerando aspectos como anotaciones,
        parametrización, facilidad de integración, reportes y flexibilidad. Indica cuál utilizarías en un
        proyecto real y justifica tu elección.