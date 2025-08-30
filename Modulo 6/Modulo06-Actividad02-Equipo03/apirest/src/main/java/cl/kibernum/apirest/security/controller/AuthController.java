package cl.kibernum.apirest.security.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.kibernum.apirest.security.domain.Role;
import cl.kibernum.apirest.security.domain.Usuario;
import cl.kibernum.apirest.security.dto.AuthResponse;
import cl.kibernum.apirest.security.dto.LoginRequest;
import cl.kibernum.apirest.security.dto.RegisterRequest;
import cl.kibernum.apirest.security.jwt.JwtProperties;
import cl.kibernum.apirest.security.jwt.JwtService;
import cl.kibernum.apirest.security.repository.IUsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  
  private final AuthenticationManager authManager;
  private final JwtService jwtService;
  private final IUsuarioRepository userRepo;
  private final PasswordEncoder passwordEncoder;
  private final JwtProperties props;

  // Injección por constructor de todos los colaboradores.
  public AuthController(AuthenticationManager authManager, JwtService jwtService, 
                        IUsuarioRepository userRepo, PasswordEncoder passwordEncoder, 
                        JwtProperties props) {
    this.authManager = authManager;
    this.jwtService = jwtService;
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
    this.props = props;
  }

  /*
   * Autentica a un usuario mediante username/password y retorna tokens.  
   * Entrada: {@Link LoginRequest} validado {@Valid}.  
   * Salida: {@Link AuthResponse} con accessToken, expiresIn y (opcional) refreshToken.  
   * Errores: 401 (BadCredentialsException) si credenciales inválidas.
   */

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login (@Valid @RequestBody LoginRequest request) {
    try {
      authManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
      );
      Usuario user = userRepo.findByEmail(request.getEmail()).orElseThrow();
      String access = jwtService.generateAccessToken(user);
      String refresh = props.getJwt().isRefreshEnabled() ? jwtService.generateRefreshTokens(user) : null;
      long expiresIn = props.getJwt().getAccessTtl().toSeconds();
      return ResponseEntity.ok(new AuthResponse(access, expiresIn, refresh));
    } catch (BadCredentialsException ex) {
      throw ex;
    }
  }

  @PostMapping("/register")
  public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
    if (!props.getAuth().isRegistrationEnabled()) {
      return ResponseEntity.notFound().build();
    }
    if (userRepo.existsByEmail(request.getEmail())) {
      throw new IllegalArgumentException("El email ingresado ya existe");
    }
    if (userRepo.existsByRut(request.getRut())) {
      throw new IllegalArgumentException("El RUT ingresado ya existe");
    }
    // Construcción y persistencia de un nuevo usuario con contraseña cifrada y rol USER.
    Usuario ua = new Usuario();
    ua.setName(request.getName());
    ua.setLastname(request.getLastname());
    ua.setRut(request.getRut());
    ua.setEmail(request.getEmail());
    ua.setPassword(passwordEncoder.encode(request.getPassword()));
    ua.setActive(true);
    ua.setRoles(Set.of(Role.ROLE_USER));
    userRepo.save(ua);
    return ResponseEntity.ok().build();
  }

  /*
   * Emite nuevos tokens a partir de un refresh token válido.
   * Importante: este endpoint espera el refresh token en el campo como texto plano (no JSON).
   * Si refresh está deshabilitado via propiedades, responde 404 Not Found.
   * Valida la versión del token (tokenVerson) para soportar invalidación global por usuario.
   */

  @PostMapping("/refresh")
  public ResponseEntity<AuthResponse> refresh(@RequestBody String refreshToken) {
    if (!props.getJwt().isRefreshEnabled()) {
      return ResponseEntity.notFound().build();
    }
    // Valida firma, expiración, issuer y extrae claims.
    var payload = jwtService.parseAndValidate(refreshToken);
    Usuario user = userRepo.findByEmail(payload.getSubject()).orElseThrow();
    // Compara la versión de token embebida con la versión actual del usuario.  
    if (payload.getVersion() != user.getTokenVersion()) {
      throw new BadCredentialsException("Invalid refresh token version");
    }
    // Genera nuevo access token y, por conveniencia, un nuevo refresh token rotado.
    String access = jwtService.generateAccessToken(user);
    String refresh = jwtService.generateRefreshTokens(user);
    long expiresIn = props.getJwt().getAccessTtl().toSeconds();
    return ResponseEntity.ok(new AuthResponse(access, expiresIn, refresh));
  }

}