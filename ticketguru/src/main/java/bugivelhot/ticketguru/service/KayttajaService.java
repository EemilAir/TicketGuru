package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.Kayttaja;
import bugivelhot.ticketguru.model.Lipunmyyntipiste;
import bugivelhot.ticketguru.model.Kayttaja.Rooli;
import bugivelhot.ticketguru.repository.KayttajaRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class KayttajaService {

    private KayttajaRepository kayttajaRepository;

    public KayttajaService(KayttajaRepository kayttajaRepository) {
        this.kayttajaRepository = kayttajaRepository;
    }

    public Kayttaja luoJaTallennaKayttaja(String kayttajanimi, String sposti, String salasanaHash, Rooli rooli, Lipunmyyntipiste lipunmyyntipiste) {
        // Tarkistetaan onko käyttäjänimi jo käytössä ja palautetaan käyttäjä jos on
        Optional<Kayttaja> optionalKayttaja = kayttajaRepository.findByKayttajanimiContainingIgnoreCase(kayttajanimi);
        if (optionalKayttaja.isPresent()) {
            return optionalKayttaja.get();
        }

        // luodaan uusi käyttäjä ja tallennetaan se
        Kayttaja kayttaja = new Kayttaja(kayttajanimi, sposti, salasanaHash);
        kayttaja.setKayttajarooli(rooli);
        kayttaja.setLipunmyyntipiste(lipunmyyntipiste);
        return kayttajaRepository.save(kayttaja);
    }

    public Kayttaja haeKayttajaNimella(String kayttajanimi) {
        return kayttajaRepository.findByKayttajanimiContainingIgnoreCase(kayttajanimi).get();
    }
}