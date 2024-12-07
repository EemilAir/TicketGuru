package bugivelhot.ticketguru.web;

import bugivelhot.ticketguru.dto.TapahtumaDTO;
import bugivelhot.ticketguru.dto.TapahtumaResponseDTO;
import bugivelhot.ticketguru.model.Tapahtuma;
import bugivelhot.ticketguru.repository.TapahtumaRepository;
import bugivelhot.ticketguru.service.LipputyyppiService;
import bugivelhot.ticketguru.service.OsoiteService;
import bugivelhot.ticketguru.service.TapahtumaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;
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
@RequestMapping("/api/tapahtumat") // Määrittää oletuspolun endpointeille
public class TapahtumaRestController {

    private final TapahtumaRepository tapahtumaRepository;
    private final TapahtumaService tapahtumaService;
    private final OsoiteService osoiteService;
    private final LipputyyppiService lipputyyppiService;

    public TapahtumaRestController(TapahtumaRepository tapahtumaRepository, TapahtumaService tapahtumaService,
            OsoiteService osoiteService, LipputyyppiService lipputyyppiService) {
        this.tapahtumaRepository = tapahtumaRepository;
        this.tapahtumaService = tapahtumaService;
        this.osoiteService = osoiteService;
        this.lipputyyppiService = lipputyyppiService;
    }

    @GetMapping({ "", "/" })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<TapahtumaResponseDTO> haeKaikkiTapahtumat(
            @RequestParam(required = false) String nimi,
            @RequestParam(required = false) String kategoria) {

        return tapahtumaService.haeKaikkiTapahtumat(nimi, kategoria); // palauttaa kaikki suodatetut tapahtumat
    }

    @GetMapping({"/{id}", "/{id}/"})
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<TapahtumaResponseDTO> haetapahtuma(@PathVariable("id") Long id) {
        Tapahtuma tapahtuma = tapahtumaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tapahtumaa ei löytynyt ID:llä " + id)); // 404 Not
                                                                                                          // Found
        return ResponseEntity.ok(tapahtumaService.mapToResponseDTO(tapahtuma)); // 200 OK
    }

    @PostMapping({ "", "/" })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> lisaaTapahtuma(@Valid @RequestBody TapahtumaDTO dto) {
        TapahtumaResponseDTO responseDTO = tapahtumaService.lisaaTapahtuma(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // TODO:virheiden hallinta!
    @PatchMapping({ "/{id}", "/{id}/" })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TapahtumaResponseDTO> muokkaaTapahtuma(@PathVariable("id") Long id,
            @RequestBody TapahtumaDTO muokattuTapahtuma) {
        Optional<TapahtumaResponseDTO> updatedTapahtuma = tapahtumaService.muokkaaTapahtuma(id, muokattuTapahtuma);
        return updatedTapahtuma.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping({"/{id}", "/{id}/"})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> poistaTapahtuma(@PathVariable("id") Long id) {
        if (!tapahtumaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tapahtumaa ei löydy ID:llä " + id); // 404 Not Found
        } else {
            tapahtumaRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        }
    }

    @GetMapping({"/form-data", "/form-data/"})
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Object> haeTapahtumaFormData() {
        Map<String, Object> formData = new HashMap<>();
        formData.put("osoitteet", osoiteService.haeKaikkiOsoitteet());
        formData.put("lipputyypit", lipputyyppiService.haeKaikkiLipputyypit());
        return ResponseEntity.ok(formData);
    }

    // TODO: haetaan myyntitapahtumat tapahtuman ID:llä
    /* @GetMapping({"/{id}/myyntitapahtumat/", "/{id}/myyntitapahtumat"})
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Object> haeTapahtumanMyyntitapahtumat(@PathVariable("id") Long id) {
        Tapahtuma tapahtuma = tapahtumaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tapahtumaa ei löytynyt ID:llä " + id)); // 404 Not
                                                                                                          // Found
        return ResponseEntity.ok(tapahtumaService.haeTapahtumanMyyntitapahtumat(tapahtuma));
    } */
}