package bugivelhot.ticketguru.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bugivelhot.ticketguru.model.Myyntitapahtuma;

import bugivelhot.ticketguru.repository.MyyntitapahtumaRepository;

@RestController
@RequestMapping("/api/myyntitapahtumat/")
public class MyyntitapahtumaRestController {

    @Autowired
    MyyntitapahtumaRepository myyntitapahtumaRepository;

    @GetMapping
    public List<Myyntitapahtuma> haeKaikkiMyyntitapahtumat() {
        return myyntitapahtumaRepository.findAll();
    }


/*         @GetMapping
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
    } */
}
