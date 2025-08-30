package cl.kibernum.apirest.security.config;

import java.time.Duration;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import cl.kibernum.apirest.security.exception.SecurityExceptionHandler;
import cl.kibernum.apirest.security.filter.JwtAuthenticationFilter;
import cl.kibernum.apirest.security.jwt.JwtProperties;
import cl.kibernum.apirest.security.repository.IUsuarioRepository;


/*
 * Configuración principal de Spring Security.
 * 
 * Objetivos clave:
 * - Autenticación stateless con JWT (sin sesiones de servidor).
 * - Reglas de autorización por método HTTP y ruta (rol USER/ADMIN).
 * - CORS configurable de errores 401/403.
 * - Compatibilidad con H2 console en desarrollo.
 */

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

  // Filtro que procesa el header Authorization: Bearer <token>.
  private final JwtAuthenticationFilter jwtFilter;
  // Propiedades (security.jwt.*, security.cors.*, etc.).
  private final JwtProperties props;
  // EntityPoint/AccessDeniedHandler personalizados para 401 y 403.
  private final SecurityExceptionHandler securityExceptionHandler;

  // Inyección por constructor de dependencias necesarias para la configuración.
  public SecurityConfig(JwtAuthenticationFilter jwtFilter, JwtProperties props, SecurityExceptionHandler securityExceptionHandler) {
    this.jwtFilter = jwtFilter;
    this.props = props;
    this.securityExceptionHandler = securityExceptionHandler;
  }

  // Encoder de contraseñas. BCrypt es un estándar seguro y recomendado.
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // Expone el AuthenticationManager que construye Springa a partir de la configuración.
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .cors(cors -> cors.configurationSource(corsConfigurationSource()))
      .exceptionHandling(ex -> ex
        .authenticationEntryPoint(securityExceptionHandler)
        .accessDeniedHandler(securityExceptionHandler)
        )
      // Permite iframes para poder abrir la consola H2 en dev.  
      .headers(headers -> headers.frameOptions(frame -> frame.disable())) // for H2 console
      .authorizeHttpRequests(auth -> auth
      // Endpoints públicos (login/registration/refresh y consola H2).
      .requestMatchers("/api/v1/auth/**", "/h2-console/**").permitAll()
      // Lecturas de Productos permitidas a USER o ADMIN.
      .requestMatchers(HttpMethod.GET, "/api/v1/productos/**").hasAnyRole("USER", "ADMIN")
      // Operaciones de escritura solo para ADMIN.
      .requestMatchers(HttpMethod.POST, "/api/v1/productos/**").hasRole("ADMIN")
      .requestMatchers(HttpMethod.PUT, "/api/v1/productos/**").hasRole("ADMIN")
      .requestMatchers(HttpMethod.DELETE, "/api/v1/productos/**").hasRole("ADMIN")
      .anyRequest().authenticated()
      )
      .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService(IUsuarioRepository repo) { 
    return email -> repo.findByEmail(email)
      .map(ua -> User.withUsername(ua.getEmail())
        .password(ua.getPassword())
        .authorities(ua.getRoles().stream().map(Enum::name).toArray(String[]::new))
        .disabled(!ua.isActive())
        .build())
      .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    List<String> origins = props.getCors().getAllowedOrigins();
    if (origins != null && !origins.isEmpty()) {
      config.setAllowedOrigins(origins);
    } else {
      config.addAllowedOriginPattern("*");
    }
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    config.setAllowCredentials(false);
    config.setMaxAge(Duration.ofHours(1));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
  }
}
