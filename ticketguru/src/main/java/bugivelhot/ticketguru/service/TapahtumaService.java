package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.dto.OsoiteDTO;
import bugivelhot.ticketguru.dto.TapahtumaDTO;
import bugivelhot.ticketguru.dto.TapahtumaResponseDTO;
import bugivelhot.ticketguru.dto.TapahtumanLipputyyppiDTO;
import bugivelhot.ticketguru.exception.ResourceAlreadyExistsException;
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
import java.util.stream.Collectors;

@Service
public class TapahtumaService {

    private TapahtumaRepository tapahtumaRepository;
    private OsoiteRepository osoiteRepository;
    private TapahtumanLipputyyppiRepository tapahtumanLipputyyppiRepository;
    private LipputyyppiRepository lipputyyppiRepository;

    public TapahtumaService(TapahtumaRepository tapahtumaRepository, OsoiteRepository osoiteRepository,
            TapahtumanLipputyyppiRepository tapahtumanLipputyyppiRepository,
            LipputyyppiRepository lipputyyppiRepository) {
        this.tapahtumaRepository = tapahtumaRepository;
        this.osoiteRepository = osoiteRepository;
        this.tapahtumanLipputyyppiRepository = tapahtumanLipputyyppiRepository;
        this.lipputyyppiRepository = lipputyyppiRepository;
    }

    public TapahtumaResponseDTO mapToResponseDTO(Tapahtuma tapahtuma) {
        TapahtumaResponseDTO responseDTO = new TapahtumaResponseDTO();
        responseDTO.setNimi(tapahtuma.getNimi());
        responseDTO.setKuvaus(tapahtuma.getKuvaus());
        responseDTO.setKategoria(tapahtuma.getKategoria());
        responseDTO.setAloituspvm(tapahtuma.getAloituspvm().toString()); // Assuming aloituspvm is LocalDateTime
        responseDTO.setLopetuspvm(tapahtuma.getLopetuspvm().toString()); // Assuming lopetuspvm is LocalDateTime
        responseDTO.setKatuosoite(tapahtuma.getKatuosoite());
        responseDTO.setLippujaJaljella(tapahtuma.getLippujaJaljella());

        // Map Osoite to OsoiteDTO
        OsoiteDTO osoiteDTO = new OsoiteDTO();
        osoiteDTO.setOsoiteId(tapahtuma.getOsoite().getOsoiteId());
        osoiteDTO.setPostinumero(tapahtuma.getOsoite().getPostinumero());
        osoiteDTO.setPostitmp(tapahtuma.getOsoite().getPostitmp());
        responseDTO.setOsoite(osoiteDTO);

        // Mapataan lipputyypit vain, jos niitä on olemassa
        List<TapahtumanLipputyyppiDTO> lipputyyppiDTOLista = tapahtuma.getTapahtumanLipputyypit() != null
                ? tapahtuma.getTapahtumanLipputyypit().stream()
                        .map(lipputyyppi -> {
                            TapahtumanLipputyyppiDTO lipputyyppiDTO = new TapahtumanLipputyyppiDTO();
                            lipputyyppiDTO.setId(lipputyyppi.getId());
                            lipputyyppiDTO.setNimi(lipputyyppi.getLipputyyppi().getLipputyyppiNimi());
                            lipputyyppiDTO.setKuvaus(lipputyyppi.getLipputyyppi().getKuvaus());
                            lipputyyppiDTO.setHinta(lipputyyppi.getHinta());
                            return lipputyyppiDTO;
                        })
                        .collect(Collectors.toList())
                : new ArrayList<>(); // Jos tapahtumanLipputyypit on null, palautetaan tyhjä lista

        responseDTO.setLipputyypit(lipputyyppiDTOLista);
        return responseDTO;
    }

    public List<TapahtumaResponseDTO> haeKaikkiTapahtumat(String nimi, String kategoria) {
        List<Tapahtuma> tapahtumat;

        if (nimi != null && kategoria != null) {
            tapahtumat = tapahtumaRepository
                    .findTapahtumatByNimiContainingIgnoreCaseAndKategoriaContainingIgnoreCase(nimi, kategoria);
        } else if (nimi != null) {
            tapahtumat = tapahtumaRepository.findTapahtumatByNimiContainingIgnoreCase(nimi);
        } else if (kategoria != null) {
            tapahtumat = tapahtumaRepository.findByKategoriaContainingIgnoreCase(kategoria);
        } else {
            tapahtumat = tapahtumaRepository.findAll();
        }

        if (tapahtumat.isEmpty()) {
            throw new ResourceNotFoundException("Tapahtumia ei löytynyt");
        }

        return tapahtumat.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public TapahtumaResponseDTO lisaaTapahtuma(@Valid TapahtumaDTO tapahtumaDTO) {
        Tapahtuma tapahtuma = new Tapahtuma();

        // Aseta perustiedot DTO:sta tapahtuma-olioon
        tapahtuma.setNimi(tapahtumaDTO.getNimi());
        tapahtuma.setKuvaus(tapahtumaDTO.getKuvaus());
        tapahtuma.setKategoria(tapahtumaDTO.getKategoria());
        tapahtuma.setAloituspvm(tapahtumaDTO.getAloituspvm());
        tapahtuma.setLopetuspvm(tapahtumaDTO.getLopetuspvm());
        tapahtuma.setKatuosoite(tapahtumaDTO.getKatuosoite());
        tapahtuma.setLippujaJaljella(tapahtumaDTO.getLippujaJaljella());

        // Aseta osoite
        if (tapahtumaDTO.getOsoiteId() != null) {
            Osoite osoite = osoiteRepository.findById(tapahtumaDTO.getOsoiteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Osoite ei löydy"));
            tapahtuma.setOsoite(osoite);
        }

        // Tallennetaan tapahtuma
        Tapahtuma savedTapahtuma = tapahtumaRepository.save(tapahtuma);

        // Käsittele lipputyypit
        List<TapahtumanLipputyyppi> lipputyypit = tapahtumaDTO.getLipputyypit().stream().map(dto -> {

            Lipputyyppi lipputyyppi = lipputyyppiRepository.findById(dto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Lipputyyppi ei löydy"));

            TapahtumanLipputyyppiId tapahtumanLipputyyppiId = new TapahtumanLipputyyppiId(
                    savedTapahtuma.getTapahtumaId(), lipputyyppi.getLipputyyppiId());
            // Luo uusi TapahtumanLipputyyppi
            TapahtumanLipputyyppi tapahtumanLipputyyppi = new TapahtumanLipputyyppi();
            tapahtumanLipputyyppi.setId(tapahtumanLipputyyppiId);
            tapahtumanLipputyyppi.setLipputyyppi(lipputyyppi);
            tapahtumanLipputyyppi.setTapahtuma(savedTapahtuma); // linkitä tapahtuma
            tapahtumanLipputyyppi.setHinta(dto.getHinta()); // Aseta hinta DTO:sta

            return tapahtumanLipputyyppi;
        }).collect(Collectors.toList());

        // Tallennetaan lipputyypit
        // Oletetaan, että sinulla on TapahtumanLipputyyppiRepository, johon voit
        // tallentaa

        tapahtumanLipputyyppiRepository.saveAll(lipputyypit);
        savedTapahtuma.setTapahtumanLipputyypit(lipputyypit);
        // Mapataan takaisin DTO:ksi
        return mapToResponseDTO(savedTapahtuma);
    }

    public Optional<TapahtumaResponseDTO> muokkaaTapahtuma(Long id, TapahtumaDTO muokattuTapahtumaDTO) {
        Optional<Tapahtuma> tapahtumaOptional = tapahtumaRepository.findById(id);

        if (tapahtumaOptional.isPresent()) {
            Tapahtuma tapahtuma = tapahtumaOptional.get();

            // Update basic fields
            if (muokattuTapahtumaDTO.getNimi() != null) {
                tapahtuma.setNimi(muokattuTapahtumaDTO.getNimi());
            }
            if (muokattuTapahtumaDTO.getKuvaus() != null) {
                tapahtuma.setKuvaus(muokattuTapahtumaDTO.getKuvaus());
            }
            if (muokattuTapahtumaDTO.getKategoria() != null) {
                tapahtuma.setKategoria(muokattuTapahtumaDTO.getKategoria());
            }
            if (muokattuTapahtumaDTO.getAloituspvm() != null) {
                tapahtuma.setAloituspvm(muokattuTapahtumaDTO.getAloituspvm());
            }
            if (muokattuTapahtumaDTO.getLopetuspvm() != null) {
                tapahtuma.setLopetuspvm(muokattuTapahtumaDTO.getLopetuspvm());
            }
            if (muokattuTapahtumaDTO.getKatuosoite() != null) {
                tapahtuma.setKatuosoite(muokattuTapahtumaDTO.getKatuosoite());
            }
            if (muokattuTapahtumaDTO.getLippujaJaljella() != null) {
                tapahtuma.setLippujaJaljella(muokattuTapahtumaDTO.getLippujaJaljella());
            }
            if (muokattuTapahtumaDTO.getOsoiteId() != null) {
                tapahtuma.setOsoite(osoiteRepository.findById(muokattuTapahtumaDTO.getOsoiteId())
                        .orElseThrow(() -> new ResourceNotFoundException("Osoite ei löydy")));
            }

            // Päivitä tai lisää lipputyypit tapahtumalle vain, jos DTO:ssa on lipputyyppejä
            List<TapahtumanLipputyyppi> updatedTapahtumanLipputyypit = muokattuTapahtumaDTO.getLipputyypit() != null
                    ? muokattuTapahtumaDTO.getLipputyypit().stream().map(dto -> {
                        // Hae lipputyyppi tai luo uusi
                        Lipputyyppi lipputyyppi = lipputyyppiRepository.findById(dto.getId()).orElse(new Lipputyyppi());
                        lipputyyppi.setLipputyyppiNimi(dto.getLipputyyppiNimi());
                        lipputyyppi.setKuvaus(dto.getKuvaus());

                        // Luo tai päivitä TapahtumanLipputyyppi
                        TapahtumanLipputyyppi tapahtumanLipputyyppi = new TapahtumanLipputyyppi();

                        TapahtumanLipputyyppiId tapahtumanLipputyyppiId = new TapahtumanLipputyyppiId();
                        tapahtumanLipputyyppiId.setLipputyyppiId(dto.getId()); // DTO:n lipputyyppiId
                        tapahtumanLipputyyppiId.setTapahtumaId(tapahtuma.getTapahtumaId()); // Tapahtuman id

                        tapahtumanLipputyyppi.setId(tapahtumanLipputyyppiId);
                        tapahtumanLipputyyppi.setLipputyyppi(lipputyyppi);
                        tapahtumanLipputyyppi.setTapahtuma(tapahtuma);
                        tapahtumanLipputyyppi.setHinta(dto.getHinta());

                        return tapahtumanLipputyyppi;
                    }).collect(Collectors.toList())
                    : new ArrayList<>(); // Jos lipputyypit on null, käytetään tyhjää listaa

            // Päivitä tapahtuman lipputyypit
            tapahtuma.setTapahtumanLipputyypit(updatedTapahtumanLipputyypit);

            // Save changes and convert back to DTO
            Tapahtuma savedTapahtuma = tapahtumaRepository.save(tapahtuma);
            return Optional.of(mapToResponseDTO(savedTapahtuma));
        }

        throw new ResourceNotFoundException("Tapahtumaa ei löydy");
    }

    public Tapahtuma luoJaTallennaTapahtuma(String nimi, String kuvaus, String kategoria, LocalDateTime aloitusPvm,
            LocalDateTime lopetusPvm, String katuosoite, Osoite osoite, int lippujaJaljella) {
        // Tarkista, että tapahtumaa ei ole jo olemassa
        if (tapahtumaRepository.findTapahtumaByNimiContainingIgnoreCase(nimi).isPresent()) {
            throw new ResourceAlreadyExistsException("Tapahtuma on jo olemassa nimellä: " + nimi);
        }
        Tapahtuma tapahtuma = new Tapahtuma(nimi, kuvaus, kategoria, aloitusPvm, lopetusPvm, katuosoite, osoite, lippujaJaljella);
        return tapahtumaRepository.save(tapahtuma);
    }

    public TapahtumanLipputyyppi luoJaTallennaTapahtumanLipputyyppi(Tapahtuma tapahtuma, Lipputyyppi lipputyyppi,
            double hinta) {
        TapahtumanLipputyyppiId tapahtumanLipputyyppiId = new TapahtumanLipputyyppiId(tapahtuma.getTapahtumaId(),
                lipputyyppi.getLipputyyppiId());
        TapahtumanLipputyyppi tapahtumanLipputyyppi = new TapahtumanLipputyyppi(tapahtumanLipputyyppiId, hinta);
        tapahtumanLipputyyppi.setTapahtuma(tapahtuma);
        tapahtumanLipputyyppi.setLipputyyppi(lipputyyppi);
        return tapahtumanLipputyyppiRepository.save(tapahtumanLipputyyppi);
    }

    public Tapahtuma haeTapahtumaNimella(String nimi) {
        return tapahtumaRepository.findTapahtumaByNimiContainingIgnoreCase(nimi).get();
    }

    /*
     * public Optional<Tapahtuma> muokkaaTapahtuma(Long id, Tapahtuma
     * muokattuTapahtuma) {
     * Optional<Tapahtuma> tapahtumaOptional = tapahtumaRepository.findById(id);
     * 
     * if (tapahtumaOptional.isPresent()) {
     * Tapahtuma tapahtuma = tapahtumaOptional.get();
     * 
     * if (muokattuTapahtuma.getNimi() != null) {
     * tapahtuma.setNimi(muokattuTapahtuma.getNimi());
     * }
     * if (muokattuTapahtuma.getKuvaus() != null) {
     * tapahtuma.setKuvaus(muokattuTapahtuma.getKuvaus());
     * }
     * if (muokattuTapahtuma.getKategoria() != null) {
     * tapahtuma.setKategoria(muokattuTapahtuma.getKategoria());
     * }
     * if (muokattuTapahtuma.getAloituspvm() != null) {
     * tapahtuma.setAloituspvm(muokattuTapahtuma.getAloituspvm());
     * }
     * if (muokattuTapahtuma.getLopetuspvm() != null) {
     * tapahtuma.setLopetuspvm(muokattuTapahtuma.getLopetuspvm());
     * }
     * if (muokattuTapahtuma.getKatuosoite() != null) {
     * tapahtuma.setKatuosoite(muokattuTapahtuma.getKatuosoite());
     * }
     * if (muokattuTapahtuma.getLippujaJaljella() > 0) {
     * tapahtuma.setLippujaJaljella(muokattuTapahtuma.getLippujaJaljella());
     * }
     * if (muokattuTapahtuma.getOsoite() != null) {
     * tapahtuma.setOsoite(muokattuTapahtuma.getOsoite());
     * }
     * 
     * return Optional.of(tapahtumaRepository.save(tapahtuma));
     * }
     * 
     * throw new ResourceNotFoundException("Tapahtumaa ei löydy");
     * }
     * 
     * public List<Tapahtuma> haeKaikkiTapahtumat(String nimi, String kategoria) {
     * List<Tapahtuma> tapahtumat;
     * 
     * if (nimi != null && kategoria != null) {
     * tapahtumat = tapahtumaRepository.
     * findTapahtumatByNimiContainingIgnoreCaseAndKategoriaContainingIgnoreCase(
     * nimi, kategoria);
     * } else if (nimi != null) {
     * tapahtumat =
     * tapahtumaRepository.findTapahtumatByNimiContainingIgnoreCase(nimi);
     * } else if (kategoria != null) {
     * tapahtumat =
     * tapahtumaRepository.findByKategoriaContainingIgnoreCase(kategoria);
     * } else {
     * tapahtumat = tapahtumaRepository.findAll();
     * }
     * 
     * return tapahtumat;
     * }
     * 
     * //TODO: TDO + Lipputyypit + Osoite
     * public Tapahtuma lisaaTapahtuma(@Valid Tapahtuma tapahtuma) {
     * // Tarkista, että kaikki tarvittavat tiedot ovat mukana
     * if (tapahtuma.getNimi() == null || tapahtuma.getKategoria() == null ||
     * tapahtuma.getAloituspvm() == null) {
     * throw new
     * IllegalArgumentException("Tapahtuman nimi, kategoria ja aloituspäivämäärä ovat pakollisia"
     * );
     * }
     * // Tallenna tapahtuma ilman osoitetta ja lipputyyppiä
     * return tapahtumaRepository.save(tapahtuma);
     * }
     */
}