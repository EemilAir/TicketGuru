package bugivelhot.ticketguru.web;

import org.springframework.web.bind.annotation.RestController;
import bugivelhot.ticketguru.model.Tapahtuma;
import bugivelhot.ticketguru.repository.TapahtumaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/tapahtumat/") // Määrittää oletuspolun endpointeille
public class TapahtumaRestController {

    @Autowired
    private TapahtumaRepository tapahtumaRepository;

    // Hakee kaikki tai suodatetut tapahtumat nimen ja kategorian perusteella
    // Esim: http://localhost:8080/api/tapahtumat/?nimi=Tuska&kategoria=Festivaali /TAI/ http://localhost:8080/api/tapahtumat/ /TAI/ http://localhost:8080/api/tapahtumat/?nimi=Tuska
    @GetMapping
    public List<Tapahtuma> haeKaikkiTapahtumat(
            @RequestParam(required = false) String nimi,
            @RequestParam(required = false) String kategoria) {
        if (nimi != null && kategoria != null) {
            return tapahtumaRepository.findByNimiContainingIgnoreCaseAndKategoriaContainingIgnoreCase(nimi, kategoria);
        } else if (nimi != null) {
            return tapahtumaRepository.findByNimiContainingIgnoreCase(nimi);
        } else if (kategoria != null) {
            return tapahtumaRepository.findByKategoriaContainingIgnoreCase(kategoria);
        }
        return tapahtumaRepository.findAll();
    }

    // http://localhost:8080/api/tapahtumat/1
    @GetMapping("{id}")
    public Optional<Tapahtuma> haeTapahtuma(@PathVariable("id") Long id) {
        return tapahtumaRepository.findById(id);
    }

    // POST: http://localhost:8080/api/tapahtumat/
    @PostMapping
    public ResponseEntity<Tapahtuma> lisaaTapahtuma(@RequestBody Tapahtuma tapahtuma) {
        Tapahtuma uusiTapahtuma = tapahtumaRepository.save(tapahtuma);
        return new ResponseEntity<>(uusiTapahtuma, HttpStatus.CREATED);
    }

    // DELETE: http://localhost:8080/api/tapahtumat/1
    @DeleteMapping("{id}")
    public void poistaTapahtuma(@PathVariable("id") Long id) {
        tapahtumaRepository.deleteById(id);
    }
}
