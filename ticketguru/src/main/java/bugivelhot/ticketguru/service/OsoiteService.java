package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.Osoite;
import bugivelhot.ticketguru.repository.OsoiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OsoiteService {

    @Autowired
    private OsoiteRepository osoiteRepository;

    public Osoite luoJaTallennaOsoite(String postinumero, String kaupunki) {
        Osoite osoite = new Osoite(postinumero, kaupunki);
        return osoiteRepository.save(osoite);
    }
}