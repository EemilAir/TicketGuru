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

import bugivelhot.ticketguru.model.Lippu;
import bugivelhot.ticketguru.model.Myyntitapahtuma;
import bugivelhot.ticketguru.repository.MyyntitapahtumaRepository;
import bugivelhot.ticketguru.repository.LippuRepository;
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

    @Autowired
    LippuRepository lippuRepository;

    /* ESIMERKKI POST /api/myyntitapahtumat/
     * {
     *   "kayttajaId": 1,
     *   "maksutapaId": 1,
     *   "liput": [
     *      {    
     *          "tapahtumaId": 1,
     *          "lipputyyppiId": 1,
     *          "maara": 3
     *      },  
     * }
     */

    // http://localhost:8080/api/myyntitapahtumat/
    @PostMapping
    public ResponseEntity<Object> luoMyyntitapahtuma(@RequestBody MyyntitapahtumaJaLiputDTO dto) {
        // luodaan responseDTO, joka sisältää vain oleelliset maksutapahtuman tiedot
        MyyntitapahtumaResponseDTO responseDTO = myyntitapahtumaService.luoMyyntitapahtumaJaLiput(dto);

        // palautetaan 201 CREATED status ja responseDTO, joka sisältää myyntitapahtuman ja lippujen olennaiset tiedot
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Haku kaikille myyntitapahtuman lipuille ID:llä
    // http://localhost:8080/api/myyntitapahtumat/1/liput
    @GetMapping("/{myyntitapahtumaId}/liput")
    public List<Lippu> getLiputByMyyntitapahtumaId(@PathVariable Long myyntitapahtumaId) {
        Myyntitapahtuma myyntitapahtuma = myyntitapahtumaRepository.findById(myyntitapahtumaId)
                .orElseThrow(() -> new RuntimeException("Myyntitapahtuma ei löytynyt"));
        return lippuRepository.findByMyyntitapahtuma(myyntitapahtuma); // palauttaa kaikki myyntitapahtuman liput
    }


    // Haetaan myyntitapahtuma ID:llä
    // http://localhost:8080/api/myyntitapahtumat/1
    @GetMapping("{id}")
    public ResponseEntity<MyyntitapahtumaResponseDTO> haeMyyntitapahtuma(@PathVariable("id") Long id) {
        Optional<Myyntitapahtuma> myyntitapahtuma = myyntitapahtumaRepository.findById(id);
        if (myyntitapahtuma.isPresent()) {
            return ResponseEntity.ok(myyntitapahtumaService.mapToResponseDTO(myyntitapahtuma.get())); // 200 OK
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }

    }

    // Haetaan kaikki myyntitapahtumat
    // http://localhost:8080/api/myyntitapahtumat/
    // http://localhost:8080/api/myyntitapahtumat/?summa=100.0
    // http://localhost:8080/api/myyntitapahtumat/?maksutapa=käteinen
    // http://localhost:8080/api/myyntitapahtumat/?kayttajanimi=myyja1
    @GetMapping
    public List<MyyntitapahtumaResponseDTO> haeKaikkiMyyntitapahtumat(
            @RequestParam(required = false) Double summa,
            @RequestParam(required = false) String maksutapa,
            @RequestParam(required = false) String kayttajanimi) {

        return myyntitapahtumaService.haeKaikkiMyyntitapahtumat(summa, maksutapa, kayttajanimi); // palauttaa kaikki myyntitapahtumat
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> poistaMyyntiTapahtuma(@PathVariable("id") Long id) {

        if (!myyntitapahtumaRepository.existsById(id)) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        } else {
            myyntitapahtumaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        }
    }
}
