package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.Osoite;
import bugivelhot.ticketguru.repository.OsoiteRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class OsoiteService {

    private OsoiteRepository osoiteRepository;

    public OsoiteService(OsoiteRepository osoiteRepository) {
        this.osoiteRepository = osoiteRepository;
    }

    public Osoite luoJaTallennaOsoite(String postinumero, String kaupunki) {
        Optional<Osoite> optionalOsoite = haeOsoitePostinumerolla(postinumero);
        if (optionalOsoite.isPresent()) {
            return optionalOsoite.get();
        }
        Osoite osoite = new Osoite(postinumero, kaupunki);
        return osoiteRepository.save(osoite);
    }

    public void tallennaOsoitteet(List<Osoite> osoitteet) {
        for (Osoite osoite : osoitteet) {
            try {
                osoiteRepository.save(osoite);
            } catch (DataIntegrityViolationException e) {
                // Handle the exception when a duplicate entry is encountered
                Optional<Osoite> optinalOsoite = haeOsoitePostinumerolla(osoite.getPostinumero());
                if (optinalOsoite.isPresent()) {
                    System.out.println("Duplicate entry found for postinumero: " + osoite.getPostinumero());
                } else {
                    System.out.println("DataIntegrityViolationException: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Osoite> haeKaikkiOsoitteet() {
        return osoiteRepository.findAll();
    }

    public Optional<Osoite> haeOsoitePostinumerolla(String postinumero) {
        return osoiteRepository.findByPostinumero(postinumero);
    }
}