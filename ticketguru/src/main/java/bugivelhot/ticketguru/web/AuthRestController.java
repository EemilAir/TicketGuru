package bugivelhot.ticketguru.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import bugivelhot.ticketguru.dto.LoginRequest;
import bugivelhot.ticketguru.service.SessionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    private final SessionService sessionService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthRestController(SessionService sessionService, AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.sessionService = sessionService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(HttpServletResponse response, @RequestBody LoginRequest loginRequest) {
        try {
            // Authenticate the user
            System.out.println(loginRequest.getUsername());
            System.out.println(loginRequest.getPassword());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Create a session and set the cookie
            String sessionId = sessionService.createSession(loginRequest.getUsername());
            Cookie sessionCookie = new Cookie("SESSIONID", sessionId);
            sessionCookie.setHttpOnly(true);
            sessionCookie.setSecure(true);
            sessionCookie.setPath("/");
            sessionCookie.setMaxAge(60 * 60 * 24); // 1 day
            response.addCookie(sessionCookie);

            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Logged in successfully");
            responseBody.put("username", loginRequest.getUsername());

            return ResponseEntity.ok(responseBody);
        } catch (AuthenticationException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
        }
    }

    @GetMapping("/check-auth")
    public ResponseEntity<Map<String, String>> checkAuth(
            @CookieValue(name = "SESSIONID", defaultValue = "") String sessionId) {
        String username = sessionService.getSession(sessionId);
        if (username != null) {
            Map<String, String> response = new HashMap<>();
            response.put("username", username);
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Not authenticated");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletResponse response,
            @CookieValue(name = "SESSIONID", defaultValue = "") String sessionId) {
        sessionService.removeSession(sessionId); // Remove session from store

        // Expire the session cookie
        Cookie sessionCookie = new Cookie("SESSIONID", null);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setSecure(true);
        sessionCookie.setPath("/");
        sessionCookie.setMaxAge(0); // Expire the cookie
        response.addCookie(sessionCookie);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "Logged out successfully");

        return ResponseEntity.ok(responseBody);
    }
}
