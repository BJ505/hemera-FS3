package com.hemera.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod; // Importar HttpMethod
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint; // Inyectar el manejador de 401

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para facilitar pruebas
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/velas/**").hasAnyRole("USER", "ADMIN") // GET permitido
                        .requestMatchers(HttpMethod.POST, "/api/velas/**").hasRole("ADMIN") // POST permitido solo para
                                                                                             // ADMIN
                        .requestMatchers(HttpMethod.PUT, "/api/velas/**").hasRole("ADMIN") // PUT permitido solo para
                                                                                            // ADMIN
                        .requestMatchers(HttpMethod.DELETE, "/api/velas/**").hasRole("ADMIN") // DELETE permitido solo
                                                                                               // para ADMIN
                        .requestMatchers(HttpMethod.GET, "/api/carrito/**").hasAnyRole("USER", "ADMIN") // GET permitido
                        .requestMatchers(HttpMethod.POST, "/api/carrito/**").hasAnyRole("USER", "ADMIN") // POST permitido
                        .requestMatchers(HttpMethod.PUT, "/api/carrito/**").hasAnyRole("USER", "ADMIN") // PUT permitido 
                        .requestMatchers(HttpMethod.DELETE, "/api/carrito/**").hasAnyRole("USER", "ADMIN") // DELETE permitido 
                        .anyRequest().authenticated() // Cualquier otra ruta requiere autenticación
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customAuthenticationEntryPoint) // Usar manejador personalizado para
                                                                                  // 401 Unauthorized
                        .accessDeniedHandler(accessDeniedHandler()) // Usar manejador personalizado para 403 Forbidden
                )
                .httpBasic(withDefaults()) // Habilitar autenticación básica
                .build(); // Construir la cadena de seguridad
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Acceso denegado: No tienes permisos para realizar esta operación.");
        };
    }

    // Definir usuarios en memoria con sus respectivos roles
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}user123") // No se codifica la contraseña para facilitar pruebas
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123") // No se codifica la contraseña para facilitar pruebas
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
