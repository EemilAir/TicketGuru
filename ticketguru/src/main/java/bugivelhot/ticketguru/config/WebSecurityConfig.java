package bugivelhot.ticketguru.config;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    // Tämä metodi määrittää, miten käyttäjät autentikoidaan
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Kaikki pyynnöt vaativat autentikaation
                .authorizeHttpRequests(authorize -> authorize
                        // Vain ADMIN-roolilla voi lisätä tapahtumia
                        .requestMatchers(HttpMethod.POST,"/api/tapahtumat/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/tapahtumat/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/tapahtumat/**").hasRole("ADMIN")
                        // Sekä ADMIN- että USER-roolilla voi nähdä tapahtumat (GET)-pyynnöllä
                        .requestMatchers(HttpMethod.GET,"/api/tapahtumat/**").hasAnyRole("ADMIN", "USER")
                        // Sekä ADMIN- että USER-roolilla voi käyttää myyntitapahtuman kaikkia endpointteja
                        .requestMatchers("/api/myyntitapahtumat/**").hasAnyRole("ADMIN", "USER")
                    // Muut pyynnöt vaativat autentikaation
                    .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console()).disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin())); // H2-konsolin
                                                                                                      // käyttöön

        return http.build();
    }

    // Tämä metodi määrittää, miten käyttäjät autentikoidaan, kovakoodattu käyttäjä
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder() // Ei turvallinen tapa tallentaa salasanoja, ei tuotantoon
                // Käyttäjänimi ja salasana
                .username("user")
                .password(passwordEncoder.encode("user"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();
        // Luodaan lista käyttäjistä
        List<UserDetails> users = new ArrayList<>();
        users.add(user);
        users.add(admin);
        // Palautetaan käyttäjät
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
