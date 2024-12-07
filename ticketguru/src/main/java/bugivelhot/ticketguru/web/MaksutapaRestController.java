package bugivelhot.ticketguru.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bugivelhot.ticketguru.model.Maksutapa;
import bugivelhot.ticketguru.service.MaksutapaService;

@RestController
@RequestMapping("/api/maksutavat")
public class MaksutapaRestController {
    
    private final MaksutapaService maksutapaService;

    public MaksutapaRestController(MaksutapaService maksutapaService) {
        this.maksutapaService = maksutapaService;
    }

    // Tämä metodi palauttaa kaikki maksutavat
    @GetMapping({"/", ""})
    public ResponseEntity<List<Maksutapa>> haeKaikkiMaksutavat() {
        List<Maksutapa> maksutavat = maksutapaService.haeKaikkiMaksutavat();
        maksutavat.forEach(maksutapa -> maksutapa.setMyyntitapahtumat(null));
        return ResponseEntity.ok(maksutavat);
    }
}
