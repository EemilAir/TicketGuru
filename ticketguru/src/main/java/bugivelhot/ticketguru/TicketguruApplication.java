package bugivelhot.ticketguru;

import bugivelhot.ticketguru.dto.LippuDTO;
import bugivelhot.ticketguru.dto.MyyntitapahtumaJaLiputDTO;
import bugivelhot.ticketguru.model.*;
import bugivelhot.ticketguru.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

            // Tallenna lipputyypit tapahtuma1:lle
            TapahtumanLipputyyppi tapahtuman1LipputyyppiNormaali = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma1, normaaliLippu, 25.0);
            TapahtumanLipputyyppi tapahtuman1LipputyyppiVIP = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma1, vipLippu, 50.0);
        
            // Tallenna lipputyypit tapahtuma2:lle
            TapahtumanLipputyyppi tapahtuman2LipputyyppiNormaali = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma2, normaaliLippu, 30.0);
            TapahtumanLipputyyppi tapahtuman2LipputyyppiVIP = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma2, vipLippu, 60.0);
        
            // Tallenna lipputyypit tapahtuma3:lle
            TapahtumanLipputyyppi tapahtuman3LipputyyppiNormaali = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma3, normaaliLippu, 35.0);
            TapahtumanLipputyyppi tapahtuman3LipputyyppiVIP = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma3, vipLippu, 70.0);

            // Käyttäjäroolit
            Kayttaja.Rooli adminRooli = Kayttaja.Rooli.ADMIN;
            Kayttaja.Rooli myyjaRooli = Kayttaja.Rooli.MYYJA;

            // Käyttäjät
            Kayttaja admin = kayttajaService.luoJaTallennaKayttaja("admin", "admin@ticketguru.fi", "salasana", adminRooli, null);
            Kayttaja myyja1 = kayttajaService.luoJaTallennaKayttaja("myyja1", "myyja1@ticketguru.fi", "salasana", myyjaRooli, myyntipiste1);
            Kayttaja myyja2 = kayttajaService.luoJaTallennaKayttaja("myyja2", "myyja2@ticketguru.fi", "salasana", myyjaRooli, myyntipiste2);

            // Luodaan Lippu1
            LippuDTO lippu1 = new LippuDTO();
            lippu1.setLipputyyppiId(normaaliLippu.getLipputyyppiId()); // lisätään lipputyypiksi normaali lippu
            lippu1.setTapahtumaId(tapahtuma1.getTapahtumaId()); // lisätään tapahtuman id
            lippu1.setMaara(2);  // 2 normaalia lippua

            // Luodaan Lippu2
            LippuDTO lippu2 = new LippuDTO();
            lippu2.setLipputyyppiId(vipLippu.getLipputyyppiId()); // lisätään lipputyypiksi normaali lippu
            lippu2.setTapahtumaId(tapahtuma1.getTapahtumaId()); // lisätään tapahtuman id
            lippu2.setMaara(1);  // 1 VIP-lippu

            // Lisää liput listaan
            List<LippuDTO> liput = new ArrayList<>();
            liput.add(lippu1);
            liput.add(lippu2);

            // Luodaan MyyntitapahtumaJaLiputDTO
            MyyntitapahtumaJaLiputDTO myyntitapahtumaJaLiputDTO = new MyyntitapahtumaJaLiputDTO();
            myyntitapahtumaJaLiputDTO.setKayttajaId(myyja1.getKayttajaId());  // Asetetaan myyjän ID
            myyntitapahtumaJaLiputDTO.setMaksutapaId(kateinen.getMaksutapaId()); // Asetetaan maksutavan "käteinen" ID
            myyntitapahtumaJaLiputDTO.setLiput(liput);  // Asetetaan liput myyntitapahtumaan

            // Luo uusi myyntitapahtuma, joka sisältää liput
            myyntitapahtumaService.luoMyyntitapahtumaJaLiput(myyntitapahtumaJaLiputDTO);

            System.out.println("Myyntitapahtuma 1 luotu ja liput lisätty!");
            
             // Luodaan Lippu3
             LippuDTO lippu3 = new LippuDTO();
             lippu3.setLipputyyppiId(normaaliLippu.getLipputyyppiId()); // lisätään lipputyypiksi normaali lippu
             lippu3.setTapahtumaId(tapahtuma2.getTapahtumaId()); // lisätään tapahtuman id
             lippu3.setMaara(3);  // 3 normaalia lippua
 
             // Luodaan Lippu4
             LippuDTO lippu4 = new LippuDTO();
             lippu4.setLipputyyppiId(vipLippu.getLipputyyppiId()); // lisätään lipputyypiksi VIP lippu
             lippu4.setTapahtumaId(tapahtuma2.getTapahtumaId()); // lisätään tapahtuman id
             lippu4.setMaara(2);  // 2 VIP-lippua
 
             // Lisää liput listaan
             List<LippuDTO> liput2 = new ArrayList<>();
             liput2.add(lippu3);
             liput2.add(lippu4);
 
             // Luodaan MyyntitapahtumaJaLiputDTO
             MyyntitapahtumaJaLiputDTO myyntitapahtumaJaLiputDTO2 = new MyyntitapahtumaJaLiputDTO();
             myyntitapahtumaJaLiputDTO2.setKayttajaId(myyja2.getKayttajaId());  // Asetetaan myyjän ID
             myyntitapahtumaJaLiputDTO2.setMaksutapaId(debit.getMaksutapaId()); // Asetetaan maksutavan "debit" ID
             myyntitapahtumaJaLiputDTO2.setLiput(liput2);  // Asetetaan liput myyntitapahtumaan
 
             // Luo uusi myyntitapahtuma, joka sisältää liput
             myyntitapahtumaService.luoMyyntitapahtumaJaLiput(myyntitapahtumaJaLiputDTO2);
 
             System.out.println("Myyntitapahtuma 2 luotu ja liput lisätty!");

            
             // Luodaan Lippu5
            LippuDTO lippu5 = new LippuDTO();
            lippu5.setLipputyyppiId(normaaliLippu.getLipputyyppiId()); // lisätään lipputyypiksi normaali lippu
            lippu5.setTapahtumaId(tapahtuma3.getTapahtumaId()); // lisätään tapahtuman id
            lippu5.setMaara(4);  // 4 normaalia lippua

            // Luodaan Lippu6
            LippuDTO lippu6 = new LippuDTO();
            lippu6.setLipputyyppiId(vipLippu.getLipputyyppiId()); // lisätään lipputyypiksi VIP lippu
            lippu6.setTapahtumaId(tapahtuma3.getTapahtumaId()); // lisätään tapahtuman id
            lippu6.setMaara(1);  // 1 VIP-lippu

            // Lisää liput listaan
            List<LippuDTO> liput3 = new ArrayList<>();
            liput3.add(lippu5);
            liput3.add(lippu6);

            // Luodaan MyyntitapahtumaJaLiputDTO
            MyyntitapahtumaJaLiputDTO myyntitapahtumaJaLiputDTO3 = new MyyntitapahtumaJaLiputDTO();
            myyntitapahtumaJaLiputDTO3.setKayttajaId(myyja1.getKayttajaId());  // Asetetaan myyjän ID
            myyntitapahtumaJaLiputDTO3.setMaksutapaId(credit.getMaksutapaId()); // Asetetaan maksutavan "credit" ID
            myyntitapahtumaJaLiputDTO3.setLiput(liput3);  // Asetetaan liput myyntitapahtumaan

            // Luo uusi myyntitapahtuma, joka sisältää liput
            myyntitapahtumaService.luoMyyntitapahtumaJaLiput(myyntitapahtumaJaLiputDTO3);

            System.out.println("Myyntitapahtuma 3 luotu ja liput lisätty!");
        };
    }
}