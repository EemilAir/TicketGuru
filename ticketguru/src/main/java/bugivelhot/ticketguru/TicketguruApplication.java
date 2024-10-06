package bugivelhot.ticketguru;

import bugivelhot.ticketguru.dto.MyyntitapahtumaDTO;
import bugivelhot.ticketguru.model.*;
import bugivelhot.ticketguru.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class TicketguruApplication {

    @Autowired
    private TapahtumaService tapahtumaService;

    @Autowired
    private KayttajaService kayttajaService;

    @Autowired
    private OsoiteService osoiteService;

    @Autowired
    private LipunmyyntipisteService lipunmyyntipisteService;

    @Autowired
    private LipputyyppiService lipputyyppiService;

	@Autowired
	private MaksutapaService maksutapaService;

    @Autowired
    private MyyntitapahtumaService myyntitapahtumaService;

    public static void main(String[] args) {
        SpringApplication.run(TicketguruApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {

			// Maksutavat
			Maksutapa kateinen = maksutapaService.luoJaTallennaMaksutapa("Käteinen");
			Maksutapa debit = maksutapaService.luoJaTallennaMaksutapa("Debit");
			Maksutapa credit = maksutapaService.luoJaTallennaMaksutapa("Credit");

            // Osoitteet
            Osoite osoite1 = osoiteService.luoJaTallennaOsoite("00100", "Helsinki");
            Osoite osoite2 = osoiteService.luoJaTallennaOsoite("00200", "Helsinki");
            Osoite osoite3 = osoiteService.luoJaTallennaOsoite("00540", "Helsinki");
            Osoite osoite4 = osoiteService.luoJaTallennaOsoite("20100", "Turku");

            // Lipunmyyntipisteet
            Lipunmyyntipiste myyntipiste1 = lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 1", "Esimerkkikatu 1", osoite1);
            Lipunmyyntipiste myyntipiste2 = lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 2", "Esimerkkikatu 2", osoite2);

            // Lipputyypit
            Lipputyyppi normaaliLippu = lipputyyppiService.luoJaTallennaLipputyyppi("Normaali", "Normaali lippu");
            Lipputyyppi vipLippu = lipputyyppiService.luoJaTallennaLipputyyppi("VIP", "VIP lippu");

            // Tapahtumat
            Tapahtuma tapahtuma1 = tapahtumaService.luoJaTallennaTapahtuma("Tuska Festival 2025", 
                "Tuska on Helsingin Suvilahdessa järjestettävä metallimusiikkiin keskittynyt festivaali.", 
                "Festivaali", 
                LocalDateTime.of(2025, 6, 27, 12, 0), 
                LocalDateTime.of(2025, 6, 29, 23, 0), 
                "Kaasutehtaankatu 1", osoite3, 1000);

            Tapahtuma tapahtuma2 = tapahtumaService.luoJaTallennaTapahtuma("Ruisrock 2025", 
                "Ruisrock on Turun Ruissalossa järjestettävä musiikkifestivaali.",
                "Festivaali",
                LocalDateTime.of(2025, 7, 4, 12, 0),
                LocalDateTime.of(2025, 7, 6, 23, 0),
                "Ruissalon puistotie 1", osoite4, 2000);

            Tapahtuma tapahtuma3 = tapahtumaService.luoJaTallennaTapahtuma("Flow Festival 2025",
                "Flow Festival on Helsingin Suvilahdessa järjestettävä kaupunkikulttuuri- ja musiikkifestivaali.",
                "Festivaali",
                LocalDateTime.of(2025, 8, 8, 12, 0),
                LocalDateTime.of(2025, 8, 10, 23, 0),
                "Kaasutehtaankatu 1", osoite3, 1500);

            // Tallenna lipputyypit tapahtumille
            tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma1, normaaliLippu, 25.0);
            tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma1, vipLippu, 50.0);

			tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma2, normaaliLippu, 30.0);
			tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma2, vipLippu, 60.0);

			tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma3, normaaliLippu, 35.0);
			tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma3, vipLippu, 70.0);

            // Käyttäjäroolit
            Kayttaja.Rooli adminRooli = Kayttaja.Rooli.ADMIN;
            Kayttaja.Rooli myyjaRooli = Kayttaja.Rooli.MYYJA;

            // Käyttäjät
            Kayttaja admin = kayttajaService.luoJaTallennaKayttaja("admin", "admin@ticketguru.fi", "salasana", adminRooli, null);
            Kayttaja myyja1 = kayttajaService.luoJaTallennaKayttaja("myyja1", "myyja1@ticketguru.fi", "salasana", myyjaRooli, myyntipiste1);
            Kayttaja myyja2 = kayttajaService.luoJaTallennaKayttaja("myyja2", "myyja2@ticketguru.fi", "salasana", myyjaRooli, myyntipiste2);

            // Myyntitapahtumat - testidataa, koska POST:ia ei ole vielä toteutettu, poistetaan myöhemmin
            myyntitapahtumaService.luoJaTallennaMyyntitapahtuma(new MyyntitapahtumaDTO(1L, 25.0, LocalDateTime.now(), 1L, 1L)); // admin
            myyntitapahtumaService.luoJaTallennaMyyntitapahtuma(new MyyntitapahtumaDTO(2L, 30.0, LocalDateTime.now(), 2L, 2L)); // myyja1
            myyntitapahtumaService.luoJaTallennaMyyntitapahtuma(new MyyntitapahtumaDTO(3L, 30.0, LocalDateTime.now(), 3L, 3L)); // myyja2
            myyntitapahtumaService.luoJaTallennaMyyntitapahtuma(new MyyntitapahtumaDTO(4L, 35.0, LocalDateTime.now(), 1L, 1L)); // admin

        };
    }
}