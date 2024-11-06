package bugivelhot.ticketguru.web;

import bugivelhot.ticketguru.dto.MyyntitapahtumaJaLiputDTO;
import bugivelhot.ticketguru.dto.MyyntitapahtumaResponseDTO;
import bugivelhot.ticketguru.dto.TapahtumaDTO;
import bugivelhot.ticketguru.dto.TapahtumaResponseDTO;
import bugivelhot.ticketguru.model.Myyntitapahtuma;
import bugivelhot.ticketguru.model.Tapahtuma;
import bugivelhot.ticketguru.repository.TapahtumaRepository;
import bugivelhot.ticketguru.service.TapahtumaService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tapahtumat/") // Määrittää oletuspolun endpointeille
public class TapahtumaRestController {

    @Autowired
    private TapahtumaRepository tapahtumaRepository;

    @Autowired
    private TapahtumaService tapahtumaService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<TapahtumaResponseDTO> haeKaikkiTapahtumat(
        @RequestParam(required = false) String nimi,
        @RequestParam(required = false) String kategoria) {

        return tapahtumaService.haeKaikkiTapahtumat(nimi, kategoria);  // palauttaa kaikki suodatetut tapahtumat
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TapahtumaResponseDTO> haetapahtuma(@PathVariable("id") Long id) {
        Tapahtuma tapahtuma = tapahtumaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tapahtumaa ei löytynyt ID:llä " + id)); // 404 Not Found
        return ResponseEntity.ok(tapahtumaService.mapToResponseDTO(tapahtuma)); // 200 OK
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> lisaaTapahtuma(@Valid @RequestBody TapahtumaDTO dto) {
        TapahtumaResponseDTO responseDTO = tapahtumaService.lisaaTapahtuma(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    //TODO:virheiden hallinta!
    @PatchMapping(value = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TapahtumaResponseDTO> muokkaaTapahtuma(@PathVariable("id") Long id, @RequestBody TapahtumaDTO muokattuTapahtuma) {
        Optional<TapahtumaResponseDTO> updatedTapahtuma = tapahtumaService.muokkaaTapahtuma(id, muokattuTapahtuma);
        return updatedTapahtuma.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> poistaTapahtuma(@PathVariable("id") Long id) {
        if (!tapahtumaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tapahtumaa ei löydy ID:llä " + id); // 404 Not Found
        } else {
        tapahtumaRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
        }
    }

    // Hakee kaikki tai suodatetut tapahtumat nimen ja kategorian perusteella
    // Esim: http://localhost:8080/api/tapahtumat/?nimi=Tuska&kategoria=Festivaali
    // /TAI/ http://localhost:8080/api/tapahtumat/
    // /TAI/ http://localhost:8080/api/tapahtumat/?nimi=Tuska
    // Statuskoodit: 200 OK, 404 Not Found, 400 Bad Request (jos ei ole nimeä tai kategoriaa)
    /*
    @GetMapping
    public List<Tapahtuma> haeKaikkiTapahtumat(
        @RequestParam(required = false) String nimi,
        @RequestParam(required = false) String kategoria) {
        List<Tapahtuma> tapahtumat = tapahtumaService.haeKaikkiTapahtumat(nimi, kategoria);
        if (tapahtumat.isEmpty()) {
            throw new ResourceNotFoundException("Tapahtumia ei löytynyt");
        }
        return tapahtumat; // palauttaa kaikki tapahtumat
    }

    // http://localhost:8080/api/tapahtumat/1
    // Statuskoodit: 200 OK, 400 Bad Request (ID väärässä muodossa), 404 Not Found
    @GetMapping("{id}")
    public ResponseEntity<Tapahtuma> haeTapahtuma(@PathVariable("id") Long id) {
        // Etsitään tapahtuma ID:n perusteella, ja jos ei löydy, heitetään virhe
        Tapahtuma tapahtuma = tapahtumaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tapahtumaa ei löytynyt ID:llä " + id)); // 404 Not Found
        return ResponseEntity.ok(tapahtuma); // 200 OK
    }

    /*
     * 
     * {
     * "nimi": "Uusi tapahtuma",
     * "kuvaus": "Tämä on uusi tapahtuma",
     * "kategoria": "Uusi Kategoria",
     * "aloituspvm": "{{aloituspvm}}",
     * "lopetuspvm": "{{lopetuspvm}}",
     * "katuosoite": "Uusiosoite 5",
     * "lippujaJaljella": 500,
     * "osoite": {
     * "osoiteId": 1,
     * "postinumero": "00100",
     * "postitmp": "Helsinki"
     * },
     * "liput": [],
     * "tapahtumanLipputyypit": []
     * }
     * 
     */
    /*
    // POST: http://localhost:8080/api/tapahtumat/
    // Statuskoodit: 201 CREATED, 400 Bad Request (jos jokin kenttä puuttuu tai on väärässä muodossa), 401/403 (ei oikeuksia)
    // TODO: DTO
    @PostMapping
    public ResponseEntity<Object> lisaaTapahtuma(@Valid @RequestBody Tapahtuma tapahtuma) {
        Tapahtuma uusiTapahtuma = tapahtumaService.lisaaTapahtuma(tapahtuma);
        // Palautetaan 201 CREATED status ja tallennettu tapahtuma
        return ResponseEntity.status(HttpStatus.CREATED).body(uusiTapahtuma);
    }

    // PATCH: http://localhost:8080/api/tapahtumat/id
    // Statuskoodit: 200 OK (päivitys onnistui), 
    // 400 Bad Request (jos jokin kenttä on väärässä muodossa), 
    // 401/403 (ei oikeuksia), 
    // 404 Not Found (jos tapahtumaa ei löydy)
    @PatchMapping(value = "{id}")
    public ResponseEntity<Tapahtuma> muokkaaTapahtuma(@PathVariable("id") Long id, @RequestBody Tapahtuma muokattuTapahtuma) {
        Optional<Tapahtuma> updatedTapahtuma = tapahtumaService.muokkaaTapahtuma(id, muokattuTapahtuma);
        return updatedTapahtuma.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: http://localhost:8080/api/tapahtumat/1
    // Statuskoodit: 204 No Content (poisto onnistui) 
    // 401/403 (ei oikeuksia) 
    // 404 Not Found (jos tapahtumaa ei löydy)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> poistaTapahtuma(@PathVariable("id") Long id) {
        if (!tapahtumaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tapahtumaa ei löydy ID:llä " + id); // 404 Not Found
        }

        tapahtumaRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
    */
}