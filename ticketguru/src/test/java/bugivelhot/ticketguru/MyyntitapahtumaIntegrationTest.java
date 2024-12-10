package bugivelhot.ticketguru;

import bugivelhot.ticketguru.dto.MyyntitapahtumaJaLiputDTO;
import bugivelhot.ticketguru.dto.LippuDTO;
import bugivelhot.ticketguru.model.Kayttaja;
import bugivelhot.ticketguru.model.Kayttaja.Rooli;
import bugivelhot.ticketguru.model.Lippu;
import bugivelhot.ticketguru.model.Lipputyyppi;
import bugivelhot.ticketguru.model.Maksutapa;
import bugivelhot.ticketguru.model.Myyntitapahtuma;
import bugivelhot.ticketguru.model.Tapahtuma;
import bugivelhot.ticketguru.repository.MyyntitapahtumaRepository;
import bugivelhot.ticketguru.repository.TapahtumaRepository;
import bugivelhot.ticketguru.repository.LippuRepository;
import bugivelhot.ticketguru.repository.LipputyyppiRepository;
import bugivelhot.ticketguru.repository.MaksutapaRepository;
import bugivelhot.ticketguru.repository.KayttajaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
public class MyyntitapahtumaIntegrationTest {

    @Autowired
    // WebApplicationContext-olio, joka auttaa luomaan MockMvc-olion
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MyyntitapahtumaRepository myyntitapahtumaRepository;

    @Autowired
    private LippuRepository lippuRepository;

    @Autowired
    private TapahtumaRepository tapahtumaRepository;

    @Autowired
    private LipputyyppiRepository lipputyyppiRepository;
    @Autowired
    private KayttajaRepository kayttajaRepository;

    @Autowired
    private MaksutapaRepository maksutapaRepository;

    // MockMvc-olio, joka auttaa testaamaan REST-rajapintoja
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    // Testin tarkoitus on simuloida ulkoista HTTP-kutsua 
    // ja varmistaa, että myyntitapahtuma luodaan oikein.
    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldCreateMyyntitapahtuma() throws Exception {
        // Luodaan MyyntitapahtumaJaLiputDTO-olio, ei käytetä mock-oliota
        // koska halutaan testata, että myyntitapahtuma luodaan oikein.
        MyyntitapahtumaJaLiputDTO dto = new MyyntitapahtumaJaLiputDTO();
        dto.setKayttajaId(1L);
        dto.setMaksutapaId(1L);
        LippuDTO lippuDTO = new LippuDTO();
        lippuDTO.setTapahtumaId(1L);
        lippuDTO.setLipputyyppiId(1L);
        lippuDTO.setMaara(3);
        dto.setLiput(Arrays.asList(lippuDTO));

        // Testataan, että myyntitapahtuma luodaan oikein
        // ja että arvot tallentuvat oikein
        mockMvc.perform(post("/api/myyntitapahtumat/")
                // Lähetetään POST-pyyntö, jossa on JSON-muotoinen MyyntitapahtumaJaLiputDTO-olio
                // ja tarkistetaan, että vastaus on HTTP 201 Created ja että vastauksessa on oikeat arvot
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"kayttajaId\": 1, \"maksutapaId\": 1, \"liput\": [ { \"tapahtumaId\": 1, \"lipputyyppiId\": 1, \"maara\": 3 } ] }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.myyntitapahtumaId").exists())
                .andExpect(jsonPath("$.summa").value(75.0))
                .andExpect(jsonPath("$.maksupvm").exists())
                .andExpect(jsonPath("$.maksutapa").value("Käteinen"))
                .andExpect(jsonPath("$.kayttajaId").value(1L))
                .andExpect(jsonPath("$.liput").isArray())
                .andExpect(jsonPath("$.liput", hasSize(3)));
    }

    // Testin tarkoitus on testata, että myyntitapahtuman luonti epäonnistuu, 
    // jos kayttajaId on tyhjä.
    // Validoinnin vuoksi ei välttämättä tarpeen.
    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldReturnBadRequestForMissingKayttajaId() throws Exception {
        mockMvc.perform(post("/api/myyntitapahtumat/")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{ \"maksutapaId\": 1, \"liput\": [ { \"tapahtumaId\": 1, \"lipputyyppiId\": 1, \"maara\": 3 } ] }"))
            .andExpect(status().isBadRequest());
        }

    // Testin tarkoitus on testata GET-pyyntöä ja varmistaa, että myyntitapahtuma haetaan oikein
    // ja että vastaus sisältää oikeat arvot
    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldGetSingleMyyntitapahtuma() throws Exception {
        // Luodaan ja tallennetaan tapahtuma, lipputyyppi, käyttäjä ja maksutapa tietokantaan
        Tapahtuma tapahtuma = new Tapahtuma();
        tapahtuma.setNimi("Testitapahtuma");
        tapahtuma.setAloituspvm(LocalDateTime.of(2024, 10, 1, 10, 0));
        tapahtuma.setKategoria("Testikategoria");
        tapahtuma.setLippujaJaljella(500);
        tapahtuma.setKatuosoite("Testikatu 1");
        tapahtuma.setLopetuspvm(LocalDateTime.of(2024, 10, 1, 12, 0));
        tapahtumaRepository.save(tapahtuma);

        Lipputyyppi lipputyyppi = new Lipputyyppi();
        lipputyyppi.setLipputyyppiNimi("Testilipputyyppi");
        lipputyyppi.setKuvaus("Testikuvaus");
        lipputyyppiRepository.save(lipputyyppi);

        Kayttaja kayttaja = new Kayttaja();
        kayttaja.setKayttajanimi("testikäyttäjä");
        kayttaja.setSposti("testi@testi.fi");
        kayttaja.setSalasanaHash("testihash");
        kayttaja.setKayttajarooli(Rooli.MYYJA);

        kayttajaRepository.save(kayttaja);

        Maksutapa maksutapa = maksutapaRepository.findByMaksutapaNimiContainingIgnoreCase("Käteinen")
        .orElseGet(() -> {
            Maksutapa newMaksutapa = new Maksutapa();
            newMaksutapa.setMaksutapaNimi("Käteinen");
            return maksutapaRepository.save(newMaksutapa);
        });

        // Luodaan ja tallennetaan myyntitapahtuma ja siihen liittyvät liput tietokantaan
        Myyntitapahtuma myyntitapahtuma = new Myyntitapahtuma();
        myyntitapahtuma.setKayttaja(kayttaja);
        myyntitapahtuma.setMaksutapa(maksutapa);
        myyntitapahtuma.setSumma(75.0);
        myyntitapahtuma.setMaksupvm(LocalDateTime.of(2024, 10, 1, 10, 0));

        Lippu lippu = new Lippu();
        lippu.setKoodi("TESTI123");
        lippu.setTapahtuma(tapahtuma);
        lippu.setLipputyyppi(lipputyyppi);
        lippu.setLipunTila(1);
        lippu.setMyyntitapahtuma(myyntitapahtuma);

        myyntitapahtuma.setLiput(Arrays.asList(lippu));
        myyntitapahtumaRepository.save(myyntitapahtuma);

        // Lähetetään GET-pyyntö, jossa haetaan myyntitapahtuma,
        // ja tarkistetaan, että vastaus on HTTP 200 OK ja että vastauksessa on oikeat arvot
        mockMvc.perform(get("/api/myyntitapahtumat/" + myyntitapahtuma.getMyyntitapahtumaId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.myyntitapahtumaId").value(myyntitapahtuma.getMyyntitapahtumaId()))
                .andExpect(jsonPath("$.summa").value(75.0))
                .andExpect(jsonPath("$.maksutapa").value("Käteinen"))
                .andExpect(jsonPath("$.kayttajaId").value(kayttaja.getKayttajaId()))
                .andExpect(jsonPath("$.liput[0].koodi").value(lippu.getKoodi()))
                .andExpect(jsonPath("$.liput[0].tapahtumaId").value(tapahtuma.getTapahtumaId()))
                .andExpect(jsonPath("$.liput[0].lipputyyppi").value(lipputyyppi.getLipputyyppiNimi()))
                .andExpect(jsonPath("$.liput[0].tila").value(1));
            }
}