Actividad 5: ATDD y aceptación

# Historia #
- Como usuario del sistema de reservas,
- quiero poder reservar una cancha para un horario específico,
- para asegurarme de que la cancha esté disponible y nadie más pueda reservar ese mismo horario.

# Criterios de aceptación #
- Validación de disponibilidad: El sistema debe impedir crear una reserva si el horario se superpone con otra reserva ya existente para la misma cancha y fecha.

- Confirmación de reserva: Cuando una reserva se crea exitosamente, el sistema debe devolver un identificador único y guardar la reserva.

- Notificación de conflicto: Si la reserva no puede realizarse por conflicto de horario, el sistema debe informar un mensaje claro indicando el motivo.

# Escenario de prueba (BDD) #
- Escenario: Crear una reserva para una cancha en un horario disponible

- Dado que existe una cancha llamada "Cancha 1" con horarios libres para el día 2025-07-30
- Y no hay reservas existentes para esa cancha en el horario de 10:00 a 11:00
- Cuando el usuario intenta reservar la cancha "Cancha 1" para el día 2025-07-30 de 10:00 a 11:00
- Entonces la reserva se crea exitosamente
- Y el sistema devuelve un identificador único para la reserva

# Escenario: Intentar reservar una cancha en un horario ya ocupado #

- Dado que existe una cancha llamada "Cancha 1"
- Y hay una reserva para esa cancha el día 2025-07-30 de 10:00 a 11:00
- Cuando el usuario intenta reservar la cancha "Cancha 1" para el mismo día y horario
- Entonces el sistema rechaza la reserva
- Y muestra un mensaje que indica que el horario ya está reservado
