package bugivelhot.ticketguru;

import bugivelhot.ticketguru.model.Tapahtuma;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TapahtumaTest {

    private Tapahtuma tapahtuma;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        tapahtuma = new Tapahtuma(
                "Testi tapahtuma",
                "Tämä on testi tapahtuma.",
                "Testi kategoria",
                LocalDateTime.of(2023, 10, 1, 10, 0),
                LocalDateTime.of(2023, 10, 1, 18, 0),
                "Testi katu 123",
                null, // Oletetaan, että osoite on null tässä testissä
                100);
    }

    // Testataan, että tapahtuman-olio luodaan oikein
    @Test
    public void shouldRetrieveCorrectAttributes() {
        assertEquals("Testi tapahtuma", tapahtuma.getNimi());
        assertEquals("Tämä on testi tapahtuma.", tapahtuma.getKuvaus());
        assertEquals("Testi kategoria", tapahtuma.getKategoria());
        assertEquals(LocalDateTime.of(2023, 10, 1, 10, 0), tapahtuma.getAloituspvm());
        assertEquals(LocalDateTime.of(2023, 10, 1, 18, 0), tapahtuma.getLopetuspvm());
        assertEquals("Testi katu 123", tapahtuma.getKatuosoite());
        assertEquals(100, tapahtuma.getLippujaJaljella());
    }

    // Testataan, että tapahtuman-olio päivittyy oikein
    @Test
    public void shouldUpdateAttributesCorrectly() {
        tapahtuma.setNimi("Päivitetty tapahtuma");
        tapahtuma.setKuvaus("Päivitetty kuvaus.");
        tapahtuma.setKategoria("Päivitetty kategoria");
        tapahtuma.setAloituspvm(LocalDateTime.of(2023, 11, 1, 10, 0));
        tapahtuma.setLopetuspvm(LocalDateTime.of(2023, 11, 1, 18, 0));
        tapahtuma.setKatuosoite("Päivitetty katu 456");
        tapahtuma.setLippujaJaljella(200);

        assertEquals("Päivitetty tapahtuma", tapahtuma.getNimi());
        assertEquals("Päivitetty kuvaus.", tapahtuma.getKuvaus());
        assertEquals("Päivitetty kategoria", tapahtuma.getKategoria());
        assertEquals(LocalDateTime.of(2023, 11, 1, 10, 0), tapahtuma.getAloituspvm());
        assertEquals(LocalDateTime.of(2023, 11, 1, 18, 0), tapahtuma.getLopetuspvm());
        assertEquals("Päivitetty katu 456", tapahtuma.getKatuosoite());
        assertEquals(200, tapahtuma.getLippujaJaljella());
    }

    // Testataan, että tapahtuman-olio ei hyväksy null-arvoa nimelle
    @Test
    public void shouldNotAcceptNullNimi() {
        tapahtuma.setNimi(null);
    
        Set<ConstraintViolation<Tapahtuma>> violations = validator.validate(tapahtuma);
        assertFalse(violations.isEmpty());

        ConstraintViolation<Tapahtuma> violation = violations.iterator().next();
        assertEquals("Tapahtuman nimi ei voi olla tyhjä", violation.getMessage());
    }

}
