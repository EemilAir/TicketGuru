package bugivelhot.ticketguru.web;

import org.springframework.web.bind.annotation.RestController;
import bugivelhot.ticketguru.model.Tapahtuma;
import bugivelhot.ticketguru.repository.TapahtumaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/tapahtumat/") // Määrittää oletuspolun endpointeille
public class TapahtumaRestController {

    @Autowired
    private TapahtumaRepository tapahtumaRepository;

    // http://localhost:8080/api/tapahtumat/
    @GetMapping
    public List<Tapahtuma> haeKaikkiTapahtumat() {
        return tapahtumaRepository.findAll();
    }

    // http://localhost:8080/api/tapahtumat/1
    @GetMapping("{id}")
    public Optional<Tapahtuma> haeTapahtuma(@PathVariable("id") Long id) {
        return tapahtumaRepository.findById(id);
    }

    // DELETE: http://localhost:8080/api/tapahtumat/1
    @DeleteMapping("{id}")
    public void poistaTapahtuma(@PathVariable("id") Long id) {
        tapahtumaRepository.deleteById(id);
    }
}
