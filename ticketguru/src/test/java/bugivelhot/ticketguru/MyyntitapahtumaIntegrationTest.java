package bugivelhot.ticketguru;

import bugivelhot.ticketguru.dto.MyyntitapahtumaJaLiputDTO;
import bugivelhot.ticketguru.dto.LippuDTO;
import bugivelhot.ticketguru.model.Myyntitapahtuma;
import bugivelhot.ticketguru.repository.MyyntitapahtumaRepository;
import bugivelhot.ticketguru.repository.LippuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class MyyntitapahtumaIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MyyntitapahtumaRepository myyntitapahtumaRepository;

    @Autowired
    private LippuRepository lippuRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldCreateMyyntitapahtuma() throws Exception {
        // Luodaan MyyntitapahtumaJaLiputDTO-olio, ei käytetä mock-oliota
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
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"kayttajaId\": 1, \"maksutapaId\": 1, \"liput\": [ { \"tapahtumaId\": 1, \"lipputyyppiId\": 1, \"maara\": 3 } ] }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.myyntitapahtumaId").exists())
                .andExpect(jsonPath("$.summa").value(75.0))
                .andExpect(jsonPath("$.maksupvm").exists())
                .andExpect(jsonPath("$.maksutapa").value("Käteinen"))
                .andExpect(jsonPath("$.kayttajaId").value(1L));
    }
}