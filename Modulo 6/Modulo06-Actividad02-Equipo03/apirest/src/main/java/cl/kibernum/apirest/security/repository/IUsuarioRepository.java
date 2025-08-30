package cl.kibernum.apirest.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.kibernum.apirest.security.domain.Usuario;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
  
  // Método para encontrar un usuario por su correo.
  Optional<Usuario> findByEmail(String email);

  // Método para verificar si un usuario existe por su correo.
  boolean existsByEmail(String email);
  
  // Método para verificar si un usuario existe por su RUT.
  boolean existsByRut(String rut);

  // Método para eliminar un usuario por su correo.
  void deleteByEmail(String email);

}
