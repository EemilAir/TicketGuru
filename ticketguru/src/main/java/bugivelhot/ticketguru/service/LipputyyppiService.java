package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.Lipputyyppi;
import bugivelhot.ticketguru.repository.LipputyyppiRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class LipputyyppiService {

    private LipputyyppiRepository lipputyyppiRepository;

    public LipputyyppiService(LipputyyppiRepository lipputyyppiRepository) {
        this.lipputyyppiRepository = lipputyyppiRepository;
    }

    public Lipputyyppi luoJaTallennaLipputyyppi(String nimi, String kuvaus) {
        // Tarkistetaan, onko lipputyyppi jo olemassa tietokannassa ja palautetaan se
        Optional<Lipputyyppi> optionalLipputyyppi = lipputyyppiRepository.findByLipputyyppiNimiContainingIgnoreCase(nimi);
        if (optionalLipputyyppi.isPresent()) {
            return optionalLipputyyppi.get();
        }

        // luodaan uusi lipputyyppi ja tallennetaan se tietokantaan
        Lipputyyppi lipputyyppi = new Lipputyyppi(nimi, kuvaus);
        return lipputyyppiRepository.save(lipputyyppi);
    }

    public Lipputyyppi haeLipputyyppiNimella(String nimi) {
        return lipputyyppiRepository.findByLipputyyppiNimiContainingIgnoreCase(nimi).get();
    }
}