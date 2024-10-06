package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.dto.MyyntitapahtumaDTO;
import bugivelhot.ticketguru.model.Maksutapa;
import bugivelhot.ticketguru.model.Myyntikanava;
import bugivelhot.ticketguru.model.Kayttaja;
import bugivelhot.ticketguru.model.Myyntitapahtuma;
import bugivelhot.ticketguru.repository.MaksutapaRepository;
import bugivelhot.ticketguru.repository.MyyntikanavaRepository;
import bugivelhot.ticketguru.repository.KayttajaRepository;
import bugivelhot.ticketguru.repository.MyyntitapahtumaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyyntitapahtumaService {

    @Autowired
    private MyyntitapahtumaRepository myyntitapahtumaRepository;

    @Autowired
    private MaksutapaRepository maksutapaRepository;

    /* @Autowired
    private MyyntikanavaRepository myyntikanavaRepository; */

    @Autowired
    private KayttajaRepository kayttajaRepository;

    public Myyntitapahtuma luoJaTallennaMyyntitapahtuma(MyyntitapahtumaDTO myyntitapahtumaDTO) {
        Myyntitapahtuma myyntitapahtuma = new Myyntitapahtuma();
        myyntitapahtuma.setSumma(myyntitapahtumaDTO.getSumma());
        myyntitapahtuma.setMaksupvm(myyntitapahtumaDTO.getMaksupvm());

    
        Optional<Maksutapa> maksutapa = maksutapaRepository.findById(myyntitapahtumaDTO.getMaksutapaId());
        // Optional<Myyntikanava> myyntikanava = myyntikanavaRepository.findById(myyntitapahtumaDTO.getMyyntikanavaId());
        Optional<Kayttaja> kayttaja = kayttajaRepository.findById(myyntitapahtumaDTO.getKayttajaId());

        if (maksutapa.isPresent() /* && myyntikanava.isPresent() */ && kayttaja.isPresent()) {
            myyntitapahtuma.setMaksutapa(maksutapa.get());
            // myyntitapahtuma.setMyyntikanava(myyntikanava.get());
            myyntitapahtuma.setKayttaja(kayttaja.get());

            return myyntitapahtumaRepository.save(myyntitapahtuma);
        } else {
            throw new IllegalArgumentException("Invalid IDs provided");
        }
    }
}
