package bugivelhot.ticketguru.exception;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
@ControllerAdvice
public class GlobalExceptionHandler {

    private ErrorResponse createErrorResponse(Exception ex, WebRequest request, HttpStatus status) {
        return new ErrorResponse(
                ex.getMessage(),
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                request.getDescription(false));
    }

    // Tämä metodi käsittelee yleisesti poikkeuksia. Voi käyttää esim. 401:lle,
    // koska sille ei ole omaa metodia
    // Alla olevat erilliset metodit käsittelevät tarkemmin eri poikkeuksia
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        ErrorResponse errorResponse = createErrorResponse(ex, request, status);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Käsittelee IllegalArgumentException-poikkeuksia (400 Bad Request, esim. virheelliset syöteparametrit)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleBadRequestException(IllegalArgumentException ex, WebRequest request) {
        ErrorResponse errorResponse = createErrorResponse(ex, request, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Käsittelee MethodArgumentTypeMismatchException-poikkeuksia (400 Bad Request, esim. ID on väärässä muodossa)
    // Eroaa IllegalArgumentExceptionista siinä, että tämä käsittelee spring bootin validointivirheitä
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {

        String paramNimi = ex.getName();
        String vaadittuTyyppi = ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "tuntematon";
        String viesti = String.format("Virheellinen arvo parametrille '%s'. Odotettu tyyppi on '%s'", paramNimi, vaadittuTyyppi);

        ErrorResponse errorResponse = new ErrorResponse(
            viesti,
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            request.getDescription(false)
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Käsittelee ResourceNotFoundException-poikkeuksia (404 Not Found esim. resurssia ei löydy)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = createErrorResponse(ex, request, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Käsittelee AccessDeniedException-poikkeuksia (403 Forbidden)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ErrorResponse errorResponse = createErrorResponse(ex, request, HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    // Käsittelee kaikki muut poikkeukset (500 Internal Server Error)
    // Jostain syystä tämä nappaa virheet ennen muita metodeja, jonka takia
    // vastaukseen tulee aina 500, eikä esim 400, vaikka olisi IllegalArgumentException
    /*
     * @ExceptionHandler(Exception.class)
     * public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest
     * request) {
     * Map<String, Object> body = new HashMap<>();
     * body.put("aikaleima", LocalDateTime.now());
     * body.put("tila", HttpStatus.INTERNAL_SERVER_ERROR.value());
     * body.put("virhe", "Internal Server Error");
     * body.put("viesti", ex.getMessage());
     * body.put("polku", request.getDescription(false));
     * 
     * return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
     * }
     */
}