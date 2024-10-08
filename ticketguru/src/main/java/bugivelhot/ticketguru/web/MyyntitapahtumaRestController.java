package bugivelhot.ticketguru.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bugivelhot.ticketguru.model.Myyntitapahtuma;
import bugivelhot.ticketguru.repository.MyyntitapahtumaRepository;
import bugivelhot.ticketguru.dto.MyyntitapahtumaJaLiputDTO;
import bugivelhot.ticketguru.dto.MyyntitapahtumaResponseDTO;
import bugivelhot.ticketguru.service.MyyntitapahtumaService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Object> luoMyyntitapahtuma(@RequestBody MyyntitapahtumaJaLiputDTO dto) {
        // luodaan responseDTO, joka sisältää vain oleelliset maksutapahtuman tiedot
        MyyntitapahtumaResponseDTO responseDTO = myyntitapahtumaService.luoMyyntitapahtumaJaLiput(dto);

        // palautetaan 201 CREATED status ja responseDTO, joka sisältää myyntitapahtuman ja lippujen olennaiset tiedot
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
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
    public List<Myyntitapahtuma> haeKaikkiMyyntitapahtumat(
            @RequestParam(required = false) Double summa,
            @RequestParam(required = false) String maksutapa,
            @RequestParam(required = false) String kayttajanimi) {

        List<Myyntitapahtuma> myyntitapahtumat;

        if (maksutapa != null && summa != null) {
            myyntitapahtumat = myyntitapahtumaRepository.findBySummaAndMaksutapa_MaksutapaNimiContainingIgnoreCase(summa,
                    maksutapa);
        } else if (summa != null) {
            return myyntitapahtumaRepository.findBySumma(summa);
        } else if (maksutapa != null) {
            myyntitapahtumat = myyntitapahtumaRepository.findByMaksutapa_MaksutapaNimiContainingIgnoreCase(maksutapa);
        } else if (kayttajanimi != null) {
            myyntitapahtumat = myyntitapahtumaRepository.findByKayttajaKayttajanimiContainingIgnoreCase(kayttajanimi);
        } else {
            myyntitapahtumat = myyntitapahtumaRepository.findAll();
        }

        // Jos summa on null, asetetaan se nollaksi
        for (Myyntitapahtuma myyntitapahtuma : myyntitapahtumat) {
            if (myyntitapahtuma.getSumma() == null) {
                myyntitapahtuma.setSumma(0.0);
            }
        }
        return myyntitapahtumat;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> poistaMyyntiTapahtuma(@PathVariable("id") Long id) {

        if (!myyntitapahtumaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        } else {
            myyntitapahtumaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
    }
}
