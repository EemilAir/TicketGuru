package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.Lipunmyyntipiste;
import bugivelhot.ticketguru.model.Osoite;
import bugivelhot.ticketguru.repository.LipunmyyntipisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LipunmyyntipisteService {

    @Autowired
    private LipunmyyntipisteRepository lipunmyyntipisteRepository;

    public Lipunmyyntipiste luoJaTallennaLipunmyyntipiste(String nimi, String katuosoite, Osoite osoite) {
        Lipunmyyntipiste lipunmyyntipiste = new Lipunmyyntipiste(nimi, katuosoite);
        lipunmyyntipiste.setOsoite(osoite);
        return lipunmyyntipisteRepository.save(lipunmyyntipiste);
    }
}