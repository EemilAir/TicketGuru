package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.Kayttaja;
import bugivelhot.ticketguru.model.Lipunmyyntipiste;
import bugivelhot.ticketguru.model.Kayttaja.Rooli;
import bugivelhot.ticketguru.repository.KayttajaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KayttajaService {

    @Autowired
    private KayttajaRepository kayttajaRepository;

    public Kayttaja luoJaTallennaKayttaja(String kayttajanimi, String sposti, String salasanaHash, Rooli rooli, Lipunmyyntipiste lipunmyyntipiste) {
        Kayttaja kayttaja = new Kayttaja(kayttajanimi, sposti, salasanaHash);
        kayttaja.setKayttajarooli(rooli);
        kayttaja.setLipunmyyntipiste(lipunmyyntipiste);
        return kayttajaRepository.save(kayttaja);
    }
}