package bugivelhot.ticketguru.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bugivelhot.ticketguru.model.Lippu;
import bugivelhot.ticketguru.repository.LippuRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

@RestController
@RequestMapping("/api/liput")
public class LippuRestController {

    @Autowired
    private LippuRepository lippuRepository;

    @GetMapping
    public ResponseEntity<Lippu> haeLippuKoodilla(@RequestParam("koodi") String koodi) {
        Lippu lippu = lippuRepository.findByKoodi(koodi)
                .orElseThrow(() -> new ResourceNotFoundException("Lippua ei löytynyt koodilla: " + koodi));
        return ResponseEntity.ok(lippu);
    }
}