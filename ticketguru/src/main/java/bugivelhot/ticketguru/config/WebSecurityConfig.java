package bugivelhot.ticketguru.config;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
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
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build();
        // Luodaan lista käyttäjistä
        List<UserDetails> users = new ArrayList<>();
        users.add(user);
        // Palautetaan käyttäjät
        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
