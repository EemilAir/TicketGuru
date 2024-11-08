package bugivelhot.ticketguru.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bugivelhot.ticketguru.dto.LippuPatchDTO;
import bugivelhot.ticketguru.dto.LippuResponseDTO;
import bugivelhot.ticketguru.service.MyyntitapahtumaService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/liput")
public class LippuRestController {

    @Autowired
    private MyyntitapahtumaService myyntitapahtumaService;

    @CrossOrigin
    @PatchMapping("/{lippuId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<LippuResponseDTO> paivitaLipunTila(@PathVariable Long lippuId, @RequestBody LippuPatchDTO dto) {
        LippuResponseDTO updatedLippu = myyntitapahtumaService.paivitaLipunTila(lippuId, dto);
        return ResponseEntity.ok(updatedLippu);
    }
}
