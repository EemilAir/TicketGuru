package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.dto.LippuTapahtumaResponseDTO;
import bugivelhot.ticketguru.model.Lippu;
import bugivelhot.ticketguru.repository.LippuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LippuService {

    @Autowired
    private LippuRepository lippuRepository;

    public LippuTapahtumaResponseDTO haeLippuKoodilla(String koodi) {
        Lippu lippu = lippuRepository.findByKoodi(koodi)
                .orElseThrow(() -> new ResourceNotFoundException("Lippua ei löytynyt koodilla: " + koodi));

        LippuTapahtumaResponseDTO responseDTO = new LippuTapahtumaResponseDTO();
        responseDTO.setLippuId(lippu.getLippuId());
        responseDTO.setKoodi(lippu.getKoodi());
        responseDTO.setTila(lippu.getLipunTila());
        responseDTO.setKayttoaika(lippu.getKayttoaika());
        responseDTO.setLipputyyppiId(lippu.getLipputyyppi().getLipputyyppiId());
        responseDTO.setLipputyyppiNimi(lippu.getLipputyyppi().getLipputyyppiNimi());

        if (lippu.getTapahtuma() != null) {
            responseDTO.setTapahtumaId(lippu.getTapahtuma().getTapahtumaId());
            responseDTO.setTapahtumaNimi(lippu.getTapahtuma().getNimi());
        }

        return responseDTO;
    }
}