package micro.example.gateway.config;

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
    public SecurityFilterChain securityFilterChain(final HttpSecurity http ) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);
        http
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll() //permite crear usuarios
                        .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                        .requestMatchers(HttpMethod.POST, "/monopatines").permitAll()
                        .requestMatchers(HttpMethod.GET, "/estudiantes/").hasAuthority(AuthotityConstant._USER)// de más específica a menos específica
                        .requestMatchers("/administrar/**").hasAuthority(AuthotityConstant._ADMIN)
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(new JwtFilter(this.tokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
