package bugivelhot.ticketguru.web;

import org.springframework.web.bind.annotation.RestController;

import bugivelhot.ticketguru.model.Tapahtuma;
import bugivelhot.ticketguru.repository.TapahtumaRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class TapahtumaRestController {

    @Autowired
    private TapahtumaRepository tapahtumaRepository;

    @GetMapping("/tapahtumat/{id}")
    public Optional<Tapahtuma> haeTapahtuma(@PathVariable("id") Long id) {
        return tapahtumaRepository.findById(id);
    }
    

}
