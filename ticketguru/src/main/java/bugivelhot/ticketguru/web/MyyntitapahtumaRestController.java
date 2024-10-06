package bugivelhot.ticketguru.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bugivelhot.ticketguru.model.Myyntitapahtuma;
import bugivelhot.ticketguru.repository.MyyntitapahtumaRepository;
import bugivelhot.ticketguru.dto.MyyntitapahtumaDTO;
import bugivelhot.ticketguru.service.MyyntitapahtumaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/myyntitapahtumat/")
public class MyyntitapahtumaRestController {

    @Autowired
    private MyyntitapahtumaService myyntitapahtumaService;

    @Autowired
    MyyntitapahtumaRepository myyntitapahtumaRepository;

    @PostMapping
    public ResponseEntity<Myyntitapahtuma> luoMyyntitapahtuma(@RequestBody MyyntitapahtumaDTO myyntitapahtumaDTO) {
        Myyntitapahtuma myyntitapahtuma = myyntitapahtumaService.luoJaTallennaMyyntitapahtuma(myyntitapahtumaDTO);
        return ResponseEntity.ok(myyntitapahtuma);
    }

    @GetMapping("{id}")
    public ResponseEntity<Myyntitapahtuma> haeMyyntitapahtuma(@PathVariable("id") Long id) {
        Optional<Myyntitapahtuma> myyntitapahtuma = myyntitapahtumaRepository.findById(id);
        if (myyntitapahtuma.isPresent()) {
            return ResponseEntity.ok(myyntitapahtuma.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping
    public List<Myyntitapahtuma> haeKaikkiMyyntitapahtumat() {
        return myyntitapahtumaRepository.findAll();
    }

}
