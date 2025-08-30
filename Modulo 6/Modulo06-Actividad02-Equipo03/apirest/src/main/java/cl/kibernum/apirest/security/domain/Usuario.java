package cl.kibernum.apirest.security.domain;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String lastname;

  @Column(unique = true, nullable = false)
  private String rut;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private boolean active = true;

  @Column(nullable = false)
  private int tokenVersion = 0;

  // Colección de roles del usuario. Se persiste en tabla separada user_roles (user_id, roles).

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  @Column(name = "roles", nullable = false, length = 30)
  private Set<Role> roles = new HashSet<>();

  // Getters y setters de usuario

  public Long getId() {  return id;  }
  public void setId(Long id) {  this.id = id;  }

  public String getName() {  return name;  }
  public void setName(String name) {  this.name = name;  }

  public String getLastname() {  return lastname;  }
  public void setLastname(String lastname) {  this.lastname = lastname;  }

  public String getRut() {  return rut;  }
  public void setRut(String rut) {  this.rut = rut;  }

  public String getEmail() {  return email;  }
  public void setEmail(String email) {  this.email = email;  }

  public String getPassword() {  return password;  }
  public void setPassword(String password) {  this.password = password;  }
  
  public boolean isActive() {  return active;  }
  public void setActive(boolean active) {  this.active = active;  }
  
  public Set<Role> getRoles() {  return roles;  }
  public void setRoles(Set<Role> roles) {  this.roles = roles;  }

  public int getTokenVersion() {  return tokenVersion;  }
  public void incrementTokenVersion() {  this.tokenVersion++;  }
}
