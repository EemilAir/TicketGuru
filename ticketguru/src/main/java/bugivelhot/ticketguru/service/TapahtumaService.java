package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.*;
import bugivelhot.ticketguru.repository.*;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TapahtumaService {

    @Autowired
    private TapahtumaRepository tapahtumaRepository;

    @Autowired
    private OsoiteRepository osoiteRepository;

    @Autowired
    private TapahtumanLipputyyppiRepository tapahtumanLipputyyppiRepository;

    @Autowired
    private LipputyyppiRepository lipputyyppiRepository;

    public Tapahtuma luoJaTallennaTapahtuma(String nimi, String kuvaus, String kategoria, LocalDateTime aloitusPvm, LocalDateTime lopetusPvm, String katuosoite, Osoite osoite, int lippujaJaljella) {
        Tapahtuma tapahtuma = new Tapahtuma(nimi, kuvaus, kategoria, aloitusPvm, lopetusPvm, katuosoite, osoite, lippujaJaljella);
        return tapahtumaRepository.save(tapahtuma);
    }

    public TapahtumanLipputyyppi luoJaTallennaTapahtumanLipputyyppi(Tapahtuma tapahtuma, Lipputyyppi lipputyyppi, double hinta) {
        TapahtumanLipputyyppiId tapahtumanLipputyyppiId = new TapahtumanLipputyyppiId(tapahtuma.getTapahtumaId(), lipputyyppi.getLipputyyppiId());
        TapahtumanLipputyyppi tapahtumanLipputyyppi = new TapahtumanLipputyyppi(tapahtumanLipputyyppiId, hinta);
        tapahtumanLipputyyppi.setTapahtuma(tapahtuma);
        tapahtumanLipputyyppi.setLipputyyppi(lipputyyppi);
        return tapahtumanLipputyyppiRepository.save(tapahtumanLipputyyppi);
    }

    public Optional<Tapahtuma> muokkaaTapahtuma(Long id, Tapahtuma muokattuTapahtuma) {
        Optional<Tapahtuma> tapahtumaOptional = tapahtumaRepository.findById(id);

        if (tapahtumaOptional.isPresent()) {
            Tapahtuma tapahtuma = tapahtumaOptional.get();

            if (muokattuTapahtuma.getNimi() != null) {
                tapahtuma.setNimi(muokattuTapahtuma.getNimi());
            }
            if (muokattuTapahtuma.getKuvaus() != null) {
                tapahtuma.setKuvaus(muokattuTapahtuma.getKuvaus());
            }
            if (muokattuTapahtuma.getKategoria() != null) {
                tapahtuma.setKategoria(muokattuTapahtuma.getKategoria());
            }
            if (muokattuTapahtuma.getAloituspvm() != null) {
                tapahtuma.setAloituspvm(muokattuTapahtuma.getAloituspvm());
            }
            if (muokattuTapahtuma.getLopetuspvm() != null) {
                tapahtuma.setLopetuspvm(muokattuTapahtuma.getLopetuspvm());
            }
            if (muokattuTapahtuma.getKatuosoite() != null) {
                tapahtuma.setKatuosoite(muokattuTapahtuma.getKatuosoite());
            }
            if (muokattuTapahtuma.getLippujaJaljella() > 0) {
                tapahtuma.setLippujaJaljella(muokattuTapahtuma.getLippujaJaljella());
            }
            if (muokattuTapahtuma.getOsoite() != null) {
                tapahtuma.setOsoite(muokattuTapahtuma.getOsoite());
            }

            return Optional.of(tapahtumaRepository.save(tapahtuma));
        }

        throw new ResourceNotFoundException("Tapahtumaa ei löydy");
    }

    public List<Tapahtuma> haeKaikkiTapahtumat(String nimi, String kategoria) {
        List<Tapahtuma> tapahtumat;
    
        if (nimi != null && kategoria != null) {
            tapahtumat = tapahtumaRepository.findByNimiContainingIgnoreCaseAndKategoriaContainingIgnoreCase(nimi, kategoria);
        } else if (nimi != null) {
            tapahtumat = tapahtumaRepository.findByNimiContainingIgnoreCase(nimi);
        } else if (kategoria != null) {
            tapahtumat = tapahtumaRepository.findByKategoriaContainingIgnoreCase(kategoria);
        } else {
            tapahtumat = tapahtumaRepository.findAll();
        }
    
        return tapahtumat;
    }

    //TODO: TDO + Lipputyypit + Osoite
    public Tapahtuma lisaaTapahtuma(@Valid Tapahtuma tapahtuma) {
        // Tarkista, että kaikki tarvittavat tiedot ovat mukana
        if (tapahtuma.getNimi() == null || tapahtuma.getKategoria() == null || tapahtuma.getAloituspvm() == null) {
            throw new IllegalArgumentException("Tapahtuman nimi, kategoria ja aloituspäivämäärä ovat pakollisia");
        }
        // Tallenna tapahtuma ilman osoitetta ja lipputyyppiä
        return tapahtumaRepository.save(tapahtuma);
    }
}