package micro.example.gateway.config;

import jakarta.servlet.http.HttpServletResponse;
import micro.example.gateway.security.AuthotityConstant;
import micro.example.gateway.security.jwt.JwtFilter;
import micro.example.gateway.security.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                        .requestMatchers("/administrar/**").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers("/mantenimiento/**").hasAuthority(AuthotityConstant._MANTENIMIENTO)
                        .requestMatchers("/precios/**").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers("/viajes/**").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers("/pausa/agregar").hasAuthority(AuthotityConstant._USER)
                        .requestMatchers("/pausa/**").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers("/paradas/monopatinesCercanos/**").hasAuthority(AuthotityConstant._USER)
                        .requestMatchers("/paradas/**").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers(HttpMethod.GET, "/users/parada/monopatinesCercanos/").hasAuthority(AuthotityConstant._USER)
                        .requestMatchers("/cuentas/**").hasAuthority(AuthotityConstant._ADMIN)
                        .requestMatchers("/users/**").hasAuthority(AuthotityConstant._USER)
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                // Configura logout
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL para logout
                        .logoutSuccessHandler((request, response, authentication) -> {
                            // Respuesta personalizada tras logout (opcional)
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.getWriter().write("Logout exitoso");
                            response.getWriter().flush();
                        })
                        .invalidateHttpSession(true) // Invalida sesión
                        .deleteCookies("JSESSIONID") // Elimina cookies de sesión
                );

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
