package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.dto.LippuPatchDTO;
import bugivelhot.ticketguru.dto.LippuResponseDTO;
import bugivelhot.ticketguru.dto.LippuTapahtumaResponseDTO;
import bugivelhot.ticketguru.model.Lippu;
import bugivelhot.ticketguru.repository.LippuRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;

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

    @Transactional
    public LippuResponseDTO paivitaLipunTila(String koodi, LippuPatchDTO dto) {
        Lippu lippu = lippuRepository.findByKoodi(koodi)
            .orElseThrow(() -> new ResourceNotFoundException("Lippua ei löytynyt koodilla " + koodi));

        // Tarkista, että tila on kelvollinen (esimerkiksi 0 tai 1)
        if (dto.getTila() == null || (dto.getTila() != 0 && dto.getTila() != 1)) {
            throw new IllegalArgumentException("Virheellinen tila: " + dto.getTila() + ". Tilan tulee olla muodossa 0 tai 1");
        }

        // Päivitä tila ja aseta aikaleima, jos tila muuttuu 1 --> 0
        if (dto.getTila() != null && dto.getTila() == 0 && lippu.getLipunTila() == 1) {
            lippu.setKayttoaika(LocalDateTime.now());
        }
        // Peruuttaa lipun käyttämisen ja asettaa lipun akaisin aktiiviseksi
        if (dto.getTila() != null && dto.getTila() == 1 && lippu.getLipunTila() == 0) {
            lippu.setKayttoaika(null);
        }
        lippu.setLipunTila(dto.getTila());

        lippuRepository.save(lippu);
        // Palauta ResponseDTO
        LippuResponseDTO response = new LippuResponseDTO();
        response.setKoodi(lippu.getKoodi());
        response.setTila(lippu.getLipunTila());
        response.setKayttoaika(lippu.getKayttoaika());
        response.setTapahtumaId(lippu.getTapahtuma().getTapahtumaId());
        response.setLipputyyppi(lippu.getLipputyyppi().getLipputyyppiNimi());
        return response;
    }
}