package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.dto.LipputyyppiDTO;
import bugivelhot.ticketguru.dto.LipputyyppiResponseDTO;
import bugivelhot.ticketguru.model.Lipputyyppi;
import bugivelhot.ticketguru.repository.LipputyyppiRepository;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LipputyyppiService {

    private final LipputyyppiRepository lipputyyppiRepository;

    public LipputyyppiService(LipputyyppiRepository lipputyyppiRepository) {
        this.lipputyyppiRepository = lipputyyppiRepository;
    }

    public LipputyyppiResponseDTO luoLipputyyppi(LipputyyppiDTO lipputyyppiDTO) {
        validateLipputyyppi(lipputyyppiDTO);
        
        Lipputyyppi lipputyyppi = new Lipputyyppi();
        lipputyyppi.setLipputyyppiNimi(lipputyyppiDTO.getLipputyyppiNimi());
        lipputyyppi.setKuvaus(lipputyyppiDTO.getKuvaus());

        Lipputyyppi tallennettu = lipputyyppiRepository.save(lipputyyppi);
        return convertToResponse(tallennettu);
    }

    public LipputyyppiResponseDTO paivitaLipputyyppi(Long id, LipputyyppiDTO lipputyyppiDTO) {
        Lipputyyppi lipputyyppi = lipputyyppiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lipputyyppiä ei löydy ID:llä " + id));
        
        if (lipputyyppiDTO.getLipputyyppiNimi() != null) {
            lipputyyppi.setLipputyyppiNimi(lipputyyppiDTO.getLipputyyppiNimi());
        }
        if (lipputyyppiDTO.getKuvaus() != null) {
            lipputyyppi.setKuvaus(lipputyyppiDTO.getKuvaus());
        }

        Lipputyyppi paivitetty = lipputyyppiRepository.save(lipputyyppi);
        return convertToResponse(paivitetty);
    }

    public boolean poistaLipputyyppi(Long id) {
        if (!lipputyyppiRepository.existsById(id)) {
            return false;
        }
        lipputyyppiRepository.deleteById(id);
        return true;
    }

    private void validateLipputyyppi(LipputyyppiDTO lipputyyppiDTO) {
        if (lipputyyppiDTO.getLipputyyppiNimi() == null || lipputyyppiDTO.getLipputyyppiNimi().trim().isEmpty()) {
            throw new IllegalArgumentException("Lipputyypin nimi ei voi olla tyhjä");
        }
        Optional<Lipputyyppi> existing = lipputyyppiRepository
                .findByLipputyyppiNimiContainingIgnoreCase(lipputyyppiDTO.getLipputyyppiNimi());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Lipputyyppi samalla nimellä on jo olemassa");
        }
    }

    public LipputyyppiResponseDTO convertToResponse(Lipputyyppi lipputyyppi) {
        LipputyyppiResponseDTO response = new LipputyyppiResponseDTO();
        response.setId(lipputyyppi.getLipputyyppiId());
        response.setLipputyyppiNimi(lipputyyppi.getLipputyyppiNimi());
        response.setKuvaus(lipputyyppi.getKuvaus());
        return response;
    }

    public Lipputyyppi haeLipputyyppiNimella(String nimi) {
        return lipputyyppiRepository.findByLipputyyppiNimiContainingIgnoreCase(nimi)
                .orElseThrow(() -> new ResourceNotFoundException("Lipputyyppiä ei löydy nimellä: " + nimi));
    }

    public List<LipputyyppiResponseDTO> haeKaikkiLipputyypit() {
        List<Lipputyyppi> lipputyypit = lipputyyppiRepository.findAll();
        return lipputyypit.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public Lipputyyppi luoJaTallennaLipputyyppi(String nimi, String kuvaus) {
        // Tarkistetaan, onko lipputyyppi jo olemassa tietokannassa ja palautetaan se
        Optional<Lipputyyppi> optionalLipputyyppi = lipputyyppiRepository.findByLipputyyppiNimiContainingIgnoreCase(nimi);
        if (optionalLipputyyppi.isPresent()) {
            return optionalLipputyyppi.get();
        }

        // luodaan uusi lipputyyppi ja tallennetaan se tietokantaan
        Lipputyyppi lipputyyppi = new Lipputyyppi(nimi, kuvaus);
        return lipputyyppiRepository.save(lipputyyppi);
    }
}