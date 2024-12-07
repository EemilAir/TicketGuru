package bugivelhot.ticketguru.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bugivelhot.ticketguru.dto.LippuPatchDTO;
import bugivelhot.ticketguru.dto.LippuResponseDTO;
import bugivelhot.ticketguru.dto.LippuTapahtumaResponseDTO;
import bugivelhot.ticketguru.service.LippuService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/liput")
public class LippuRestController {

    private final LippuService lippuService;

    public LippuRestController(LippuService lippuService) {
        this.lippuService = lippuService;
    }

    @CrossOrigin
    @GetMapping({"/", ""})
    public ResponseEntity<LippuTapahtumaResponseDTO> haeLippuKoodilla(@RequestParam("koodi") String koodi) {
        LippuTapahtumaResponseDTO responseDTO = lippuService.haeLippuKoodilla(koodi);
        return ResponseEntity.ok(responseDTO);
    }

    @CrossOrigin
    @PatchMapping({"/{koodi}", "/{koodi}/"})
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<LippuResponseDTO> paivitaLipunTila(@PathVariable("koodi") String koodi, @RequestBody LippuPatchDTO dto) {
        LippuResponseDTO updatedLippu = lippuService.paivitaLipunTila(koodi, dto);
        return ResponseEntity.ok(updatedLippu);
    }
}
