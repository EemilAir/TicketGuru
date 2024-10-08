package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.*;
import bugivelhot.ticketguru.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TapahtumaService {

    @Autowired
    private TapahtumaRepository tapahtumaRepository;

    @Autowired
    private TapahtumanLipputyyppiRepository tapahtumanLipputyyppiRepository;

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
}