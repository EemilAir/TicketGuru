package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.Maksutapa;
import bugivelhot.ticketguru.repository.MaksutapaRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MaksutapaService {

    private final MaksutapaRepository maksutapaRepository;

    public MaksutapaService(MaksutapaRepository maksutapaRepository) {
        this.maksutapaRepository = maksutapaRepository;
    }

    public Maksutapa luoJaTallennaMaksutapa(String maksutapaNimi) {
        // tarkistetaan löytyykö maksutapa jo tietokannasta ja palautetaan se jos löytyy
        Optional<Maksutapa> maksutapaOptional = maksutapaRepository.findByMaksutapaNimiContainingIgnoreCase(maksutapaNimi);
        if(maksutapaOptional.isPresent()) {
            return maksutapaOptional.get();
        }

        // luodaan uusi maksutapa ja tallennetaan se tietokantaan
        Maksutapa maksutapa = new Maksutapa(maksutapaNimi);
        return maksutapaRepository.save(maksutapa);
    }

    public Maksutapa haeMaksutapaNimella(String maksutapaNimi) {
        return maksutapaRepository.findByMaksutapaNimiContainingIgnoreCase(maksutapaNimi).get();
    }
}