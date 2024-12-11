package bugivelhot.ticketguru.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import bugivelhot.ticketguru.service.LipputyyppiService;
import jakarta.validation.Valid;
import bugivelhot.ticketguru.dto.LipputyyppiDTO;
import bugivelhot.ticketguru.dto.LipputyyppiResponseDTO;
import bugivelhot.ticketguru.dto.TapahtumaDTO;
import bugivelhot.ticketguru.dto.TapahtumaResponseDTO;
import bugivelhot.ticketguru.model.Lipputyyppi;
import bugivelhot.ticketguru.repository.LipputyyppiRepository;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;

@RestController
@RequestMapping("/api/lipputyypit")
public class LipputyyppiRestController {

    private final LipputyyppiService lipputyyppiService;

    private final LipputyyppiRepository lipputyyppiRepository;

    public LipputyyppiRestController(LipputyyppiService lipputyyppiService, LipputyyppiRepository lipputyyppiRepository) {
        this.lipputyyppiService = lipputyyppiService;
        this.lipputyyppiRepository = lipputyyppiRepository;
    }

    // GET: http://localhost:8080/api/lipputyypit
    @GetMapping({ "/", "" })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<LipputyyppiResponseDTO>> haeKaikkiLipputyypit() {
        List<LipputyyppiResponseDTO> lipputyypit = lipputyyppiService.haeKaikkiLipputyypit();
        return ResponseEntity.ok(lipputyypit);
    }

    // GET http://localhost:8080/api/lipputyypit/{id}
    @GetMapping({ "/{id}", "/{id}/" })
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<LipputyyppiResponseDTO> haeLipputyyppi(@PathVariable("id") Long id) {
        Lipputyyppi lipputyyppi = lipputyyppiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lipputyyppiä ei löytynyt ID:llä " + id)); // 404 Not
                                                                                                        // Found
    return ResponseEntity.ok(lipputyyppiService.convertToResponse(lipputyyppi)); // 200 OK
    }

    // POST: http://localhost:8080/api/lipputyypit
    @PostMapping({ "/", "" })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LipputyyppiResponseDTO> luoLipputyyppi(@RequestBody LipputyyppiDTO lipputyyppiDTO) {
        LipputyyppiResponseDTO luotuLipputyyppi = lipputyyppiService.luoLipputyyppi(lipputyyppiDTO);
        return ResponseEntity.ok(luotuLipputyyppi); // 200 OK
    }

    // PATCH: http://localhost:8080/api/lipputyypit/{id}
    @PatchMapping({ "/{id}", "/{id}/" })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LipputyyppiResponseDTO> paivitaLipputyyppi(
            @PathVariable("id") Long id,
            @RequestBody LipputyyppiDTO lipputyyppiDTO) {

        LipputyyppiResponseDTO paivitettyLipputyyppi = lipputyyppiService.paivitaLipputyyppi(id, lipputyyppiDTO);
        if (paivitettyLipputyyppi == null) {
            throw new ResourceNotFoundException("Lipputyyppiä ei löydy ID:llä " + id);
        }
        return ResponseEntity.ok(paivitettyLipputyyppi); // 200 OK
    }

    // DELETE: http://localhost:8080/api/lipputyypit/{id}
    @DeleteMapping({ "/{id}", "/{id}/" })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> poistaLipputyyppi(@PathVariable("id") Long id) {
        boolean poistettu = lipputyyppiService.poistaLipputyyppi(id);
        if (!poistettu) {
            throw new ResourceNotFoundException("Lipputyyppiä ei löydy ID:llä " + id);
        }
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}