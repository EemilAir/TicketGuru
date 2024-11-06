package bugivelhot.ticketguru.service;

// DTOt
import bugivelhot.ticketguru.dto.LippuDTO;
import bugivelhot.ticketguru.dto.LippuResponseDTO;
import bugivelhot.ticketguru.dto.MyyntitapahtumaJaLiputDTO;
import bugivelhot.ticketguru.dto.MyyntitapahtumaResponseDTO;

// Entiteetit
import bugivelhot.ticketguru.model.Kayttaja;
import bugivelhot.ticketguru.model.Lippu;
import bugivelhot.ticketguru.model.Maksutapa;
import bugivelhot.ticketguru.model.Myyntitapahtuma;
import bugivelhot.ticketguru.model.Tapahtuma;
import bugivelhot.ticketguru.model.TapahtumanLipputyyppi;

// repositoryt
import bugivelhot.ticketguru.repository.KayttajaRepository;
import bugivelhot.ticketguru.repository.LippuRepository;
import bugivelhot.ticketguru.repository.MaksutapaRepository;
import bugivelhot.ticketguru.repository.MyyntitapahtumaRepository;
import bugivelhot.ticketguru.repository.TapahtumaRepository;
import bugivelhot.ticketguru.repository.TapahtumanLipputyyppiRepository;

// Spring
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

// Java util
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class MyyntitapahtumaService {

    private MyyntitapahtumaRepository myyntitapahtumaRepository;
    private LippuRepository lippuRepository;
    private TapahtumaRepository tapahtumaRepository;
    private TapahtumanLipputyyppiRepository tapahtumaLipputyyppiRepository;
    private KayttajaRepository kayttajaRepository;
    private MaksutapaRepository maksutapaRepository;

    public MyyntitapahtumaService(MyyntitapahtumaRepository myyntitapahtumaRepository, LippuRepository lippuRepository,
            TapahtumaRepository tapahtumaRepository, TapahtumanLipputyyppiRepository tapahtumaLipputyyppiRepository,
            KayttajaRepository kayttajaRepository, MaksutapaRepository maksutapaRepository) {
        this.myyntitapahtumaRepository = myyntitapahtumaRepository;
        this.lippuRepository = lippuRepository;
        this.tapahtumaRepository = tapahtumaRepository;
        this.tapahtumaLipputyyppiRepository = tapahtumaLipputyyppiRepository;
        this.kayttajaRepository = kayttajaRepository;
        this.maksutapaRepository = maksutapaRepository;
    }

    public MyyntitapahtumaResponseDTO mapToResponseDTO(Myyntitapahtuma myyntitapahtuma) {
        MyyntitapahtumaResponseDTO responseDTO = new MyyntitapahtumaResponseDTO();
        responseDTO.setMyyntitapahtumaId(myyntitapahtuma.getMyyntitapahtumaId());
        responseDTO.setSumma(myyntitapahtuma.getSumma());
        responseDTO.setMaksupvm(myyntitapahtuma.getMaksupvm());
        responseDTO.setKayttajaId(myyntitapahtuma.getKayttaja().getKayttajaId());
        responseDTO.setMaksutapa(myyntitapahtuma.getMaksutapa().getMaksutapaNimi());

        List<Lippu> lippuLista = lippuRepository.findByMyyntitapahtuma(myyntitapahtuma);
        List<LippuResponseDTO> lippuResponseDTOLista = lippuLista.stream().map(lippu -> {
            LippuResponseDTO lippuResponseDTO = new LippuResponseDTO();
            lippuResponseDTO.setKoodi(lippu.getKoodi());
            lippuResponseDTO.setTapahtumaId(lippu.getTapahtuma().getTapahtumaId());
            lippuResponseDTO.setLipputyyppi(lippu.getLipputyyppi().getLipputyyppiNimi());
            lippuResponseDTO.setTila(lippu.getLipunTila());
            return lippuResponseDTO;
        }).collect(Collectors.toList());

        responseDTO.setLiput(lippuResponseDTOLista);
        return responseDTO;
    }

    @Transactional // Pitää huolen, että kaikki operaatiot suoritetaan yhdessä transaktiossa, jos
                   // jokin niistä epäonnistuu, niin kaikki peruutetaan
    public MyyntitapahtumaResponseDTO luoMyyntitapahtumaJaLiput(@Valid MyyntitapahtumaJaLiputDTO dto) {

        // Hae käyttäjä, joka luo myyntitapahtuman, jos id on null tai käyttäjää ei
        // löydy se heittää virheen
        Optional<Kayttaja> kayttajaOptional = kayttajaRepository.findById(dto.getKayttajaId());
        if (!kayttajaOptional.isPresent()) {
            throw new ResourceNotFoundException("Käyttäjää ei löydy");
        }
        Kayttaja kayttaja = kayttajaOptional.get();

        // Luo uusi myyntitapahtuma ja aseta käyttäjä sille
        Myyntitapahtuma myyntitapahtuma = new Myyntitapahtuma();
        myyntitapahtuma.setKayttaja(kayttaja);

        // Tallenna myyntitapahtuma
        myyntitapahtuma = myyntitapahtumaRepository.save(myyntitapahtuma);

        // Luo ja tallenna liput
        List<Lippu> lippuLista = new ArrayList<>();
        Double yhteissumma = 0.0;

        // Käy läpi kaikki liput, jotka halutaan luoda
        for (@Valid
        LippuDTO lippuDTO : dto.getLiput()) {

            // Heitä virhe jos lipun määrä on pienempi kuin 1
            if (lippuDTO.getMaara() < 1) {
                throw new IllegalArgumentException("Lippujen määrä ei voi olla pienempi kuin 1");
            }

            // Hae tapahtuma tietokannasta ja heitä virhe jos tapahtumaa ei löydy
            Optional<Tapahtuma> tapahtumaOptional = tapahtumaRepository.findById(lippuDTO.getTapahtumaId());
            if (!tapahtumaOptional.isPresent()) {
                throw new ResourceNotFoundException("Tapahtumaa ei löydy");
            }
            Tapahtuma tapahtuma = tapahtumaOptional.get();

            // Hae tapahtuman ja lipputyypin yhdistelmä (TapahtumaLipputyyppi) ja heitä
            // virhe jos ei löydy
            Optional<TapahtumanLipputyyppi> tapahtumaLipputyyppiOptional = tapahtumaLipputyyppiRepository
                    .findById_TapahtumaIdAndId_LipputyyppiId(
                            lippuDTO.getTapahtumaId(), lippuDTO.getLipputyyppiId());
            if (!tapahtumaLipputyyppiOptional.isPresent()) {
                throw new IllegalArgumentException("Lipputyyppi ei kuulu tapahtumaan");
            }
            TapahtumanLipputyyppi tapahtumanLipputyyppi = tapahtumaLipputyyppiOptional.get();

            // Vähentää lippujaJaljella-arvoa lipunmyynnin yhteydessä
            if (tapahtuma.getLippujaJaljella() < lippuDTO.getMaara()) {
                throw new IllegalArgumentException("Ei tarpeeksi lippuja jäljellä");
            }
            tapahtuma.setLippujaJaljella(tapahtuma.getLippujaJaljella() - lippuDTO.getMaara());
            tapahtumaRepository.save(tapahtuma);

            // Luo ja tallenna liput (lippuDTO.getMaara() kertaa)
            for (int i = 0; i < lippuDTO.getMaara(); i++) {
                Lippu lippu = new Lippu();
                lippu.setTapahtuma(tapahtuma);
                lippu.setLipputyyppi(tapahtumanLipputyyppi.getLipputyyppi());
                lippu.setMyyntitapahtuma(myyntitapahtuma);

                lippuLista.add(lippu);
                yhteissumma += tapahtumanLipputyyppi.getHinta(); // Päivitä myyntitapahtuman summa käyttäen
                                                                 // TapahtumaLipputyyppi-hintaa
            }
        }

        // Tallenna luodut liput
        lippuRepository.saveAll(lippuLista);

        // Päivitä myyntitapahtuman summa
        myyntitapahtuma.setSumma(yhteissumma);

        // hae maksutapa ja aseta se myyntitapahtumaan,jos id on null tai maksutapaa ei
        // löydy se heittää virheen
        Optional<Maksutapa> maksutapaOptional = maksutapaRepository.findById(dto.getMaksutapaId());
        if (!maksutapaOptional.isPresent()) {
            throw new ResourceNotFoundException("Maksutapaa ei löydy");
        }
        myyntitapahtuma.setMaksutapa(maksutapaOptional.get());

        // Tallenna myyntitapahtuma
        myyntitapahtumaRepository.save(myyntitapahtuma);

        // Palautetaan myyntitapahtuma ja liput responseDTO-muodossa
        return mapToResponseDTO(myyntitapahtuma);
    }

    public List<MyyntitapahtumaResponseDTO> haeKaikkiMyyntitapahtumat(Double summa, String maksutapa,
            String kayttajanimi) {
        List<Myyntitapahtuma> myyntitapahtumat;

        if (maksutapa != null && summa != null) {
            myyntitapahtumat = myyntitapahtumaRepository
                    .findBySummaAndMaksutapa_MaksutapaNimiContainingIgnoreCase(summa, maksutapa);
        } else if (summa != null) {
            myyntitapahtumat = myyntitapahtumaRepository.findBySumma(summa);
        } else if (maksutapa != null) {
            myyntitapahtumat = myyntitapahtumaRepository.findByMaksutapa_MaksutapaNimiContainingIgnoreCase(maksutapa);
        } else if (kayttajanimi != null) {
            myyntitapahtumat = myyntitapahtumaRepository.findByKayttajaKayttajanimiContainingIgnoreCase(kayttajanimi);
        } else {
            myyntitapahtumat = myyntitapahtumaRepository.findAll();
        }

        if(myyntitapahtumat.isEmpty()){
            throw new ResourceNotFoundException("Myyntitapahtumia ei löytynyt");
        }

        return myyntitapahtumat.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
}