package bugivelhot.ticketguru;

import bugivelhot.ticketguru.repository.TapahtumaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TapahtumaRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TapahtumaRepository tapahtumaRepository;

    // Testataan, että USER-roolilla näkee kaikki tapahtumat
    @Test
    @WithMockUser(roles = "USER")
    public void testGetTapahtumat() throws Exception {
        mockMvc.perform(get("/api/tapahtumat/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].nimi").exists())
                .andExpect(jsonPath("$[*].kuvaus").exists())
                .andExpect(jsonPath("$[*].kategoria").exists())
                .andExpect(jsonPath("$[*].aloituspvm").exists())
                .andExpect(jsonPath("$[*].lopetuspvm").exists())
                .andExpect(jsonPath("$[*].katuosoite").exists())
                .andExpect(jsonPath("$[*].lippujaJaljella").exists());
    }

    // Testataan, että ilman roolia ei näe tapahtumia
    @Test
    public void testGetTapahtumatUnauthorized() throws Exception {
        mockMvc.perform(get("/api/tapahtumat/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    // Testataan, että ADMIN-roolilla pystyy poistamaan tapahtuman
    @Test
    @WithMockUser(roles = "ADMIN")
    public void testAdminCanDeleteTapahtuma() throws Exception {
        mockMvc.perform(delete("/api/tapahtumat/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    // Testataan, että USER-roolilla ei pysty poistamaan tapahtumaa
    @Test
    @WithMockUser(roles = "USER")
    public void testUserCannotDeleteTapahtuma() throws Exception {
        mockMvc.perform(delete("/api/tapahtumat/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
