package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.Lipunmyyntipiste;
import bugivelhot.ticketguru.model.Osoite;
import bugivelhot.ticketguru.repository.LipunmyyntipisteRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class LipunmyyntipisteService {

    private LipunmyyntipisteRepository lipunmyyntipisteRepository;

    public LipunmyyntipisteService(LipunmyyntipisteRepository lipunmyyntipisteRepository) {
        this.lipunmyyntipisteRepository = lipunmyyntipisteRepository;
    }

    public Lipunmyyntipiste luoJaTallennaLipunmyyntipiste(String nimi, String katuosoite, Osoite osoite) {

        // Tarkistetaan onko lipunmyyntipiste jo olemassa ja palautetaan se
        Optional<Lipunmyyntipiste> lipunmyyntipisteOptional = lipunmyyntipisteRepository.findByMyyntipisteNimiContainingIgnoreCase(nimi);
        if (lipunmyyntipisteOptional.isPresent()) {
            return lipunmyyntipisteOptional.get();
        }

        // Luodaan uusi lipunmyyntipiste ja tallennetaan se tietokantaan
        Lipunmyyntipiste lipunmyyntipiste = new Lipunmyyntipiste(nimi, katuosoite);
        lipunmyyntipiste.setOsoite(osoite);
        return lipunmyyntipisteRepository.save(lipunmyyntipiste);
    }

    public Lipunmyyntipiste haeMyyntipisteNimella(String nimi) {
        return lipunmyyntipisteRepository.findByMyyntipisteNimiContainingIgnoreCase(nimi).get();
    }
}