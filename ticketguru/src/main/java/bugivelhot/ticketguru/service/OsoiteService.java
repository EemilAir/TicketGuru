package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.Osoite;
import bugivelhot.ticketguru.repository.OsoiteRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import bugivelhot.ticketguru.utils.PostinumeroLukija;

@Service
public class OsoiteService {

    private OsoiteRepository osoiteRepository;

    public OsoiteService(OsoiteRepository osoiteRepository) {
        this.osoiteRepository = osoiteRepository;
    }

    public Osoite luoJaTallennaOsoite(String postinumero, String kaupunki) {
        Osoite osoite = new Osoite(postinumero, kaupunki);
        return osoiteRepository.save(osoite);
    }

    /*
     * public void luoOsoitteet() {
     * PostinumeroLukija postinumeroLukija = new PostinumeroLukija();
     * try {
     * ClassPathResource resource = new
     * ClassPathResource("postinumerot_ja_postitoimipaikat.csv");
     * InputStream inputStream = resource.getInputStream();
     * osoiteRepository.saveAll(postinumeroLukija.luePostitiedot(inputStream));
     * System.out.println("Osoitteet luotu ja tallennettu");
     * } catch (IOException e) {
     * e.printStackTrace();
     * System.out.println("Osoitteiden luonti ja tallennus epäonnistui");
     * }
     * }
     */

    public void luoOsoitteet() {
        // tarkistetaan onko osoitteet jo luotu
        if (osoiteRepository.count() > 0) {
            System.out.println("Osoitteet on jo luotu");
            return;
        }

        PostinumeroLukija postinumeroLukija = new PostinumeroLukija();
        try {
            ClassPathResource resource = new ClassPathResource("postinumerot_ja_postitoimipaikat.csv");
            InputStream inputStream = resource.getInputStream();
            List<Osoite> osoitteet = postinumeroLukija.luePostitiedot(inputStream);
            tallennaOsoitteet(osoitteet);
            System.out.println("\n \n \n"); 
            System.out.println("Osoitteet luotu ja tallennettu");
            System.out.println("\n \n \n"); 
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Osoitteiden luonti ja tallennus epäonnistui");
        }
    }

    public void tallennaOsoitteet(List<Osoite> osoitteet){
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

    public Optional<Osoite> haeOsoitePostinumerolla(String postinumero) {
        return osoiteRepository.findByPostinumero(postinumero);
    }
}