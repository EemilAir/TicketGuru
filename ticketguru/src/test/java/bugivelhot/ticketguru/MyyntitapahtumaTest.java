package bugivelhot.ticketguru;

import bugivelhot.ticketguru.model.Myyntitapahtuma;
import bugivelhot.ticketguru.model.Tapahtuma;
import bugivelhot.ticketguru.model.Maksutapa;
import bugivelhot.ticketguru.model.Kayttaja;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class MyyntitapahtumaTest {

    private Myyntitapahtuma myyntitapahtuma;
    private Maksutapa maksutapa;
    private Kayttaja kayttaja;
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        maksutapa = new Maksutapa();
        maksutapa.setMaksutapaNimi("Käteinen");

        kayttaja = new Kayttaja();
        kayttaja.setKayttajanimi("myyja1");

        myyntitapahtuma = new Myyntitapahtuma();
        myyntitapahtuma.setSumma(100.0);
        myyntitapahtuma.setMaksupvm(LocalDateTime.of(2023, 10, 1, 10, 0));
        myyntitapahtuma.setMaksutapa(maksutapa);
        myyntitapahtuma.setKayttaja(kayttaja);
    }

    // Testataan, että myyntitapahtuman-olio luodaan oikein
    @Test
    public void shouldRetrieveCorrectAttributes() {
        assertEquals(100.0, myyntitapahtuma.getSumma());
        assertEquals(LocalDateTime.of(2023, 10, 1, 10, 0), myyntitapahtuma.getMaksupvm());
        assertEquals("Käteinen", myyntitapahtuma.getMaksutapa().getMaksutapaNimi());
        assertEquals("myyja1", myyntitapahtuma.getKayttaja().getKayttajanimi());
    }

    // Testataan, että myyntitapahtuman-olio päivittyy oikein
    @Test
    public void shouldUpdateAttributesCorrectly() {
        myyntitapahtuma.setSumma(200.0);
        myyntitapahtuma.setMaksupvm(LocalDateTime.of(2023, 11, 1, 10, 0));
        maksutapa.setMaksutapaNimi("Debit");
        myyntitapahtuma.setMaksutapa(maksutapa);
        kayttaja.setKayttajanimi("myyja2");
        myyntitapahtuma.setKayttaja(kayttaja);

        assertEquals(200.0, myyntitapahtuma.getSumma());
        assertEquals(LocalDateTime.of(2023, 11, 1, 10, 0), myyntitapahtuma.getMaksupvm());
        assertEquals("Debit", myyntitapahtuma.getMaksutapa().getMaksutapaNimi());
        assertEquals("myyja2", myyntitapahtuma.getKayttaja().getKayttajanimi());
    }

    // Testataan, että myyntitapahtuman summa ei voi olla negatiivinen
    @Test
    public void shouldNotAcceptNegativeSumma() {
        myyntitapahtuma.setSumma(-100.0);

        Set<ConstraintViolation<Myyntitapahtuma>> violations = validator.validate(myyntitapahtuma);
        assertFalse(violations.isEmpty());

        ConstraintViolation<Myyntitapahtuma> violation = violations.iterator().next();
        assertEquals("Summan pitää olla positiivinen luku", violation.getMessage());
    }

    // Testataan, että myyntitapahtuman-olio ei hyväksy null-arvoa maksupvm:lle
    @Test
    public void shouldNotAcceptNullMaksupvm() {
        myyntitapahtuma.setMaksupvm(null);
    
        Set<ConstraintViolation<Myyntitapahtuma>> violations = validator.validate(myyntitapahtuma);
        assertFalse(violations.isEmpty());

        ConstraintViolation<Myyntitapahtuma> violation = violations.iterator().next();
        assertEquals("Maksupvm ei voi olla tyhjä", violation.getMessage());
    }

}
