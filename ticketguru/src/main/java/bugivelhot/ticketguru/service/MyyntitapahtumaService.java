package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.dto.MyyntitapahtumaDTO;
import bugivelhot.ticketguru.dto.MyyntitapahtumaResponseDTO;
import bugivelhot.ticketguru.model.Maksutapa;
import bugivelhot.ticketguru.model.Kayttaja;
import bugivelhot.ticketguru.model.Myyntitapahtuma;
import bugivelhot.ticketguru.repository.MaksutapaRepository;
import bugivelhot.ticketguru.repository.KayttajaRepository;
import bugivelhot.ticketguru.repository.MyyntitapahtumaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MyyntitapahtumaService {

    @Autowired
    private MyyntitapahtumaRepository myyntitapahtumaRepository;

    @Autowired
    private MaksutapaRepository maksutapaRepository;

    @Autowired
    private KayttajaRepository kayttajaRepository;

    // Luo ja tallenna myyntitapahtuma
    // Vain käyttäjä tarvitaan, sillä muut tiedot on lisätään oletusarvioina
    public MyyntitapahtumaResponseDTO luoJaTallennaMyyntitapahtuma(MyyntitapahtumaDTO myyntitapahtumaDTO) {
        if (myyntitapahtumaDTO.getKayttajaId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Käyttäjä ID vaaditaan");
        }

        Optional<Kayttaja> kayttajaOptional = kayttajaRepository.findById(myyntitapahtumaDTO.getKayttajaId());
        if (!kayttajaOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Käyttäjää ei löydy tietokannasta");
        }

        // haetaan tietokannan ensimmäinen maksutapa, joka asetetaan oletusmaksutavaksi
        Optional<Maksutapa> maksutapaOptional = maksutapaRepository.findAll().stream().findFirst();
        if (!maksutapaOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Maksutapaa ei löydy tietokannasta");
        }

        Myyntitapahtuma myyntitapahtuma = new Myyntitapahtuma();

        myyntitapahtuma.setKayttaja(kayttajaOptional.get());

        // asetetaan oletussumma
        myyntitapahtuma.setSumma(0.0);

        // asetetaan oletuspäivämääräksi nykyhetki
        myyntitapahtuma.setMaksupvm(LocalDateTime.now());

        // asetetaan oletusmaksutavaksi tietokannasta haettu maksutapa (ensimmäinen)
        myyntitapahtuma.setMaksutapa(maksutapaOptional.get());

        myyntitapahtuma = myyntitapahtumaRepository.save(myyntitapahtuma);

        // Luodaan responseDTO ja asetetaan sille myyntitapahtuman tiedot, jotka halutaan lähettää vastauksena käyttäjälle
        MyyntitapahtumaResponseDTO responseDTO = new MyyntitapahtumaResponseDTO();
        responseDTO.setId(myyntitapahtuma.getMyyntitapahtumaId());
        responseDTO.setSumma(myyntitapahtuma.getSumma());
        responseDTO.setMaksupvm(myyntitapahtuma.getMaksupvm());
        responseDTO.setMaksutapaId(myyntitapahtuma.getMaksutapa().getMaksutapaId());
        responseDTO.setKayttajaId(myyntitapahtuma.getKayttaja().getKayttajaId());

        return responseDTO;

    }
}
