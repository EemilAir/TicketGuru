package bugivelhot.ticketguru.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable());  
   
        return http.build();
    }

    @Bean
    // Tämä metodi määrittää, miten käyttäjät autentikoidaan, kovakoodattu käyttäjä
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder() // Ei turvallinen tapa tallentaa salasanoja, ei tuotantoon
            // Käyttäjänimi ja salasana
            .username("user")
            .password("password")
            .roles("USER")
            .build();
            // Luodaan lista käyttäjistä
            List<UserDetails> users = new ArrayList<>();
            users.add(user);
        // Palautetaan käyttäjät
        return new InMemoryUserDetailsManager(users);
    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
