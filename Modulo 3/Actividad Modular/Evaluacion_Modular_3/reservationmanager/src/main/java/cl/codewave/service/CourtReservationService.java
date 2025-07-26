package cl.codewave.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import cl.codewave.interfaces.model.Court;
import cl.codewave.interfaces.model.Reservation;
import cl.codewave.interfaces.repository.CourtRepository;
import cl.codewave.interfaces.repository.ReservationRepository;
import cl.codewave.interfaces.service.ReservationService;
import cl.codewave.model.CourtReservation;
import cl.codewave.model.TimeSlot;

 
// Este proyecto posee principios SOLID porque se puede observar bajo acoplamiento, ya que las clases no dependen de 
// implementaciones concretas, sino de interfaces. Esto permite que las clases puedan ser fácilmente reemplazadas o modificadas 
// sin afectar al resto. Gracias a este enfoque, las responsabilidades están bien separadas, lo que facilita el mantenimiento
// y la extensibilidad del código.
// Por otro lado, podemos ver el uso de YAGNI (You Aren't Gonna Need It) debido a la abstención de implementar una "in-memory database"
// debido a que no era parte de los requerimientos iniciales del proyecto y no era necesario para el funcionamiento básico del sistema, 
// pero a la vez se crearon las respectivas interfaces con el fin de establecer extensibilidad y poder definir los mocks necesarios 
// para las pruebas unitarias.

public class CourtReservationService implements ReservationService {
    private final CourtRepository courtRepository;
    private final ReservationRepository reservationRepository;

    // Constructor que recibe las dependencias de repositorios
    // Esto permite la inyección de dependencias y facilita el testing
    public CourtReservationService(CourtRepository courtRepository, ReservationRepository reservationRepository) {
        this.courtRepository = courtRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override // Crea una nueva reserva, verifica que la cancha exista y que el horario no se superponga con otras reservas
    public void createReservation(String courtName, LocalDate date, TimeSlot slot) {
        Court court = courtRepository.findByName(courtName)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la cancha"));

        List<Reservation> existing = reservationRepository.findByCourtAndDate(court, date);
        for (Reservation r : existing) {
            if (r.getTimeSlot().overlaps(slot)) {
                throw new IllegalStateException("Horario ya reservado para la cancha");
            }
        }

        String id = UUID.randomUUID().toString(); // Crea un ID único de valor 128 bits
        reservationRepository.save(new CourtReservation(id, court, date, slot));
    }

    @Override // Modifica una reserva existente y verifica que el nuevo horario no se superponga con otro
    public void modifyReservation(String reservationId, LocalDate newDate, TimeSlot newSlot) {
        Reservation existing = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró la reservación"));

        Court court = existing.getCourt();

        List<Reservation> reservations = reservationRepository.findByCourtAndDate(court, newDate).stream()
                .filter(r -> !r.getId().equals(reservationId))
                .collect(Collectors.toList());

        for (Reservation r : reservations) { // Verifica si el nuevo horario se superpone con alguna reservación existente
            if (r.getTimeSlot().overlaps(newSlot)) {
                throw new IllegalStateException("Horario ya reservado para la cancha");
            }
        }
        // Elimina la reserva existente
        reservationRepository.delete(reservationId); 
        // Crea una nueva reserva con el nuevo horario y fecha
        reservationRepository.save(new CourtReservation(reservationId, court, newDate, newSlot));
    }

    @Override // Elimina una reserva por ID
    public void cancelReservation(String reservationId) {
        reservationRepository.delete(reservationId);
    }

    @Override // Calcula la cantidad de reservas para una fecha específica
    public int countReservationsByDate(LocalDate date) {
        return reservationRepository.findByDate(date).size();
    }
}
