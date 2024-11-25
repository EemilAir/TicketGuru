
package bugivelhot.ticketguru.controller;

import bugivelhot.ticketguru.dto.TapahtumaResponseDTO;
import bugivelhot.ticketguru.service.TapahtumaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tapahtumat")
public class TapahtumaController {

    @Autowired
    private TapahtumaService tapahtumaService;

    // ...existing code...

    //@PatchMapping("/{id}")
    //public Optional<TapahtumaResponseDTO> paivitaTapahtuma(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
    //    return tapahtumaService.paivitaTapahtuma(id, updates);
    //}

    // ...existing code...
}