package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.*;
import bugivelhot.ticketguru.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

        return Optional.empty();
    }
}