package bugivelhot.ticketguru.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bugivelhot.ticketguru.dto.LippuPatchDTO;
import bugivelhot.ticketguru.dto.LippuResponseDTO;
import bugivelhot.ticketguru.model.Lippu;
import bugivelhot.ticketguru.repository.LippuRepository;
import bugivelhot.ticketguru.service.MyyntitapahtumaService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/liput")
public class LippuRestController {

    @Autowired
    private LippuRepository lippuRepository;

    @Autowired
    private MyyntitapahtumaService myyntitapahtumaService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Lippu> haeLippuKoodilla(@RequestParam("koodi") String koodi) {
        Lippu lippu = lippuRepository.findByKoodi(koodi)
                .orElseThrow(() -> new ResourceNotFoundException("Lippua ei löytynyt koodilla: " + koodi));
        return ResponseEntity.ok(lippu);
    }

    @CrossOrigin
    @PatchMapping("/{lippuId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<LippuResponseDTO> paivitaLipunTila(@PathVariable Long lippuId, @RequestBody LippuPatchDTO dto) {
        LippuResponseDTO updatedLippu = myyntitapahtumaService.paivitaLipunTila(lippuId, dto);
        return ResponseEntity.ok(updatedLippu);
    }
}
