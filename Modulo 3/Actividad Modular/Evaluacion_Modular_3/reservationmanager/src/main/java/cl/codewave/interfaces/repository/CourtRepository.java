package cl.codewave.interfaces.repository;

import java.util.List;
import java.util.Optional;

import cl.codewave.interfaces.model.Court;

// Interfaz de la base de datos de canchas
public interface CourtRepository {
    void save(Court court);
    Optional<Court> findByName(String name);
    List<Court> findAll();
}
