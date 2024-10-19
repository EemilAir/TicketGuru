package bugivelhot.ticketguru.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    // Tämä metodi käsittelee yleisesti poikkeuksia. Voi käyttää esim. 401:lle, koska sille ei ole omaa metodia
    // Alla olevat erilliset metodit käsittelevät tarkemmin eri poikkeuksia
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        // Luodaan HashMap-olio, johon tallennetaan virheilmoituksen tiedot
        // Tätä varten voisi luoda myos oman ErrorResponse-luokan, joka sisältäisi virheilmoituksen tiedot
        Map<String, Object> body = new HashMap<>();
        body.put("aikaleima", LocalDateTime.now()); // Virheilmoituksen aikaleima (nykyhetki)
        body.put("tila", ex.getStatusCode().value()); // Virheilmoituksen HTTP-tilakoodi  
        body.put("virhe", ex.getReason()); // Virheilmoituksen otsikko
        body.put("viesti", ex.getMessage()); // Virheilmoituksen yksityiskohtainen viesti
        body.put("polku", request.getDescription(false)); // Virheen polku

        // Palautetaan virheilmoitus vastauksena
        return new ResponseEntity<>(body, ex.getStatusCode());
    }

    // Käsittelee IllegalArgumentException-poikkeuksia (400 Bad Request, esim. virheelliset syöteparametrit)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleBadRequestException(IllegalArgumentException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("aikaleima", LocalDateTime.now());
        body.put("tila", HttpStatus.BAD_REQUEST.value());
        body.put("virhe", "Bad Request");
        body.put("viesti", ex.getMessage());
        body.put("polku", request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Käsittelee ResourceNotFoundException-poikkeuksia (404 Not Found esim. resurssia ei löydy)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("aikaleima", LocalDateTime.now());
        body.put("tila", HttpStatus.NOT_FOUND.value());
        body.put("virhe", "Not Found");
        body.put("viesti", ex.getMessage());
        body.put("polku", request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    // Käsittelee kaikki muut poikkeukset (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("aikaleima", LocalDateTime.now());
        body.put("tila", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("virhe", "Internal Server Error");
        body.put("viesti", ex.getMessage());
        body.put("polku", request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Käsittelee AccessDeniedException-poikkeuksia (403 Forbidden)
    @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) { 
        Map<String, Object> body = new HashMap<>();
        body.put("aikaleima", LocalDateTime.now());
        body.put("tila", HttpStatus.FORBIDDEN.value());
        body.put("virhe", "Forbidden");
        body.put("viesti", ex.getMessage());
        body.put("polku", request.getDescription(false));

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }
}