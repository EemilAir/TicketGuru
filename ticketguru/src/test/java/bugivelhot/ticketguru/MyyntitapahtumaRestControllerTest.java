package bugivelhot.ticketguru;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import bugivelhot.ticketguru.dto.MyyntitapahtumaResponseDTO;
import bugivelhot.ticketguru.service.MyyntitapahtumaService;
import bugivelhot.ticketguru.web.MyyntitapahtumaRestController;

@SpringBootTest
@AutoConfigureMockMvc
public class MyyntitapahtumaRestControllerTest {

    // Mock-olio MyyntitapahtumaService-luokasta
    @Autowired
    private MockMvc mockMvc;

    // Mock-olio MyyntitapahtumaService-luokasta
    @MockBean
    private MyyntitapahtumaService myyntitapahtumaService;

    // Testattava olio MyyntitapahtumaResponseDTO-luokasta
    private MyyntitapahtumaResponseDTO myyntitapahtumaResponseDTO;

    // Testidata, ennen jokaista testiä
    @BeforeEach
    public void setUp() {
        myyntitapahtumaResponseDTO = new MyyntitapahtumaResponseDTO();
        myyntitapahtumaResponseDTO.setMyyntitapahtumaId(1L);
        myyntitapahtumaResponseDTO.setSumma(100.0);
        myyntitapahtumaResponseDTO.setMaksupvm(LocalDateTime.of(2024, 10, 1, 10, 0));
        myyntitapahtumaResponseDTO.setMaksutapa("Käteinen");
        myyntitapahtumaResponseDTO.setKayttajaId(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldCreateMyyntitapahtuma() throws Exception {
        when(myyntitapahtumaService.luoMyyntitapahtumaJaLiput(any())).thenReturn(myyntitapahtumaResponseDTO);

        // Testataan POST /api/myyntitapahtumat/
        mockMvc.perform(post("/api/myyntitapahtumat/") // POST /api/myyntitapahtumat/
                .contentType("application/json") // Content-Type: application/json
                .content("{ \"kayttajaId\": 1, \"maksutapaId\": 1, \"liput\": [ { \"tapahtumaId\": 1, \"lipputyyppiId\": 1, \"maara\": 3 } ] }")) // Request body
                .andExpect(status().isCreated()) // Statuskoodi 201 CREATED
                .andExpect(jsonPath("$.myyntitapahtumaId").value(1L)) // Varmistetaan, että vastauksessa on myyntitapahtumaId
                .andExpect(jsonPath("$.summa").value(100.0)) // Varmistetaan, että vastauksessa on summa
                .andExpect(jsonPath("$.maksupvm").value("2024-10-01T10:00:00")) // Varmistetaan, että vastauksessa on maksupvm
                .andExpect(jsonPath("$.maksutapa").value("Käteinen")) // Varmistetaan, että vastauksessa on maksutapa
                .andExpect(jsonPath("$.kayttajaId").value(1L)); // Varmistetaan, että vastauksessa on kayttajaId

        MyyntitapahtumaResponseDTO result = myyntitapahtumaService.luoMyyntitapahtumaJaLiput(any());
        assertEquals(1L, result.getMyyntitapahtumaId()); // Varmistetaan, että myyntitapahtumaId on oikein
        assertEquals(100, result.getSumma()); // Varmistetaan, että summa on oikein
        //assertEquals("2024-10-01T10:00", result.getMaksupvm()); // Varmistetaan, että maksupvm on oikein
        assertEquals("Käteinen", result.getMaksutapa()); // Varmistetaan, että maksutapa on oikein
        assertEquals(1L, result.getKayttajaId()); // Varmistetaan, että kayttajaId on oikein
    }
}
