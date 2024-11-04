package bugivelhot.ticketguru;

import bugivelhot.ticketguru.dto.LippuDTO;
import bugivelhot.ticketguru.dto.MyyntitapahtumaJaLiputDTO;
import bugivelhot.ticketguru.initializer.DataInitializer;
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

    /* private TapahtumaService tapahtumaService;
    private KayttajaService kayttajaService;
    private OsoiteService osoiteService;
    private LipunmyyntipisteService lipunmyyntipisteService;
    private LipputyyppiService lipputyyppiService;
	private MaksutapaService maksutapaService;
    private MyyntitapahtumaService myyntitapahtumaService;

    public TicketguruApplication(TapahtumaService tapahtumaService, KayttajaService kayttajaService, OsoiteService osoiteService, 
                                 LipunmyyntipisteService lipunmyyntipisteService, LipputyyppiService lipputyyppiService, 
                                 MaksutapaService maksutapaService, MyyntitapahtumaService myyntitapahtumaService) {
        this.tapahtumaService = tapahtumaService;
        this.kayttajaService = kayttajaService;
        this.osoiteService = osoiteService;
        this.lipunmyyntipisteService = lipunmyyntipisteService;
        this.lipputyyppiService = lipputyyppiService;
        this.maksutapaService = maksutapaService;
        this.myyntitapahtumaService = myyntitapahtumaService;
    } */

    private final DataInitializer dataInitializer;

    public TicketguruApplication(DataInitializer dataInitializer) {
        this.dataInitializer = dataInitializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(TicketguruApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {

            dataInitializer.initialize();

			/* // Maksutavat
			Maksutapa kateinen = maksutapaService.luoJaTallennaMaksutapa("Käteinen");
			Maksutapa debit = maksutapaService.luoJaTallennaMaksutapa("Debit");
			Maksutapa credit = maksutapaService.luoJaTallennaMaksutapa("Credit");
            Maksutapa mobilepay = maksutapaService.luoJaTallennaMaksutapa("MobilePay");
            Maksutapa paypal = maksutapaService.luoJaTallennaMaksutapa("PayPal");

            // Osoitteet
            osoiteService.luoOsoitteet();
            Osoite osoite1 = osoiteService.haeOsoitePostinumerolla("00100").get();
            Osoite osoite2 = osoiteService.haeOsoitePostinumerolla("00200").get();
            Osoite osoite3 = osoiteService.haeOsoitePostinumerolla("00540").get();
            Osoite osoite4 = osoiteService.haeOsoitePostinumerolla("20100").get();
            Osoite osoite5 = osoiteService.haeOsoitePostinumerolla("33100").get();
            Osoite osoite6 = osoiteService.haeOsoitePostinumerolla("90100").get();
            Osoite osoite7 = osoiteService.haeOsoitePostinumerolla("40100").get();
            Osoite osoite8 = osoiteService.haeOsoitePostinumerolla("80100").get();

            // Lipunmyyntipisteet
            Lipunmyyntipiste myyntipiste1 = lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 1", "Esimerkkikatu 1", osoite1);
            Lipunmyyntipiste myyntipiste2 = lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 2", "Esimerkkikatu 2", osoite2);
            Lipunmyyntipiste myyntipiste3 = lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 3", "Keskustori 5", osoite5);
            Lipunmyyntipiste myyntipiste4 = lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 4", "Kauppatori 3", osoite6);
            Lipunmyyntipiste myyntipiste5 = lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 5", "Asemakatu 10", osoite7);
            Lipunmyyntipiste myyntipiste6 = lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 6", "Tori 8", osoite8);

            // Lipputyypit
            Lipputyyppi normaaliLippu = lipputyyppiService.luoJaTallennaLipputyyppi("Normaali", "Normaali lippu");
            Lipputyyppi vipLippu = lipputyyppiService.luoJaTallennaLipputyyppi("VIP", "VIP lippu");
            Lipputyyppi opiskelijaLippu = lipputyyppiService.luoJaTallennaLipputyyppi("Opiskelija", "Alennettu opiskelijalippu");
            Lipputyyppi perheLippu = lipputyyppiService.luoJaTallennaLipputyyppi("Perhelippu", "Perhelippu 2 aikuista ja 2 lasta");
            Lipputyyppi elakelaisLippu = lipputyyppiService.luoJaTallennaLipputyyppi("Eläkeläinen", "Alennettu eläkeläislippu");


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
            
            Tapahtuma tapahtuma4 = tapahtumaService.luoJaTallennaTapahtuma("Blockfest 2025",
                "Blockfest on Tampereella järjestettävä hiphop-musiikkiin keskittynyt festivaali.",
                "Festivaali",
                LocalDateTime.of(2025, 8, 20, 12, 0),
                LocalDateTime.of(2025, 8, 22, 23, 0),
                "Ratinan Stadion", osoite5, 1800);

            Tapahtuma tapahtuma5 = tapahtumaService.luoJaTallennaTapahtuma("Qstock 2025",
                "Qstock on Oulussa järjestettävä monipuolinen musiikkifestivaali.",
                "Festivaali",
                LocalDateTime.of(2025, 7, 24, 12, 0),
                LocalDateTime.of(2025, 7, 25, 23, 0),
                "Kuusisaaren puistotie 1", osoite6, 1200);

            Tapahtuma tapahtuma6 = tapahtumaService.luoJaTallennaTapahtuma("Jyrock 2025",
                "Jyrock on Jyväskylässä järjestettävä indie-musiikkifestivaali.",
                "Festivaali",
                LocalDateTime.of(2025, 5, 15, 14, 0),
                LocalDateTime.of(2025, 5, 16, 22, 0),
                "Ilokivi", osoite7, 800);

            // Lisää tapahtuma
            Tapahtuma tapahtuma7 = tapahtumaService.luoJaTallennaTapahtuma("Ilosaarirock 2025",
                "Ilosaarirock on Joensuussa järjestettävä perinteikäs festivaali.",
                "Festivaali",
                LocalDateTime.of(2025, 7, 17, 12, 0),
                LocalDateTime.of(2025, 7, 19, 23, 0),
                "Laulurinne", osoite8, 1500);

            // Lipputyyppien tallennukset tapahtumille
                // Tapahtuma1
                TapahtumanLipputyyppi tapahtuman1LipputyyppiNormaali = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma1, normaaliLippu, 25.0);
                TapahtumanLipputyyppi tapahtuman1LipputyyppiVIP = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma1, vipLippu, 50.0);
            
                // Tapahtuma2
                TapahtumanLipputyyppi tapahtuman2LipputyyppiNormaali = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma2, normaaliLippu, 30.0);
                TapahtumanLipputyyppi tapahtuman2LipputyyppiVIP = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma2, vipLippu, 60.0);
            
                // Tapahtuma3
                TapahtumanLipputyyppi tapahtuman3LipputyyppiNormaali = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma3, normaaliLippu, 35.0);
                TapahtumanLipputyyppi tapahtuman3LipputyyppiVIP = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma3, vipLippu, 70.0);

                // Tapahtuma4
                TapahtumanLipputyyppi tapahtuman4LipputyyppiNormaali = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma4, normaaliLippu, 30.0);
                TapahtumanLipputyyppi tapahtuman4LipputyyppiOpiskelija = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma4, opiskelijaLippu, 20.0);
                TapahtumanLipputyyppi tapahtuman4LipputyyppiVIP = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma4, vipLippu, 65.0);

                // Tapahtuma5
                TapahtumanLipputyyppi tapahtuman5LipputyyppiNormaali = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma5, normaaliLippu, 25.0);
                TapahtumanLipputyyppi tapahtuman5LipputyyppiOpiskelija = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma5, opiskelijaLippu, 20.0);
                TapahtumanLipputyyppi tapahtuman5LipputyyppiVIP = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma5, vipLippu, 45.0);
                TapahtumanLipputyyppi tapahtuman5LipputyyppiElake = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma5, elakelaisLippu, 15.0);

                // Tapahtuma6
                TapahtumanLipputyyppi tapahtuman6LipputyyppiNormaali = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma6, normaaliLippu, 20.0);
                TapahtumanLipputyyppi tapahtuman6LipputyyppiOpiskelija = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma6, opiskelijaLippu, 15.0);
                TapahtumanLipputyyppi tapahtuman6LipputyyppiPerhe = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma6, perheLippu, 60.0);

                // Tapahtuma7
                TapahtumanLipputyyppi tapahtuman7LipputyyppiNormaali = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma7, normaaliLippu, 30.0);
                TapahtumanLipputyyppi tapahtuman7LipputyyppiPerhe = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma7, perheLippu, 75.0);
                TapahtumanLipputyyppi tapahtuman7LipputyyppiVIP = tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma7, vipLippu, 65.0);

            // Käyttäjäroolit
            Kayttaja.Rooli adminRooli = Kayttaja.Rooli.ADMIN;
            Kayttaja.Rooli myyjaRooli = Kayttaja.Rooli.MYYJA;

            // Käyttäjät
            Kayttaja admin = kayttajaService.luoJaTallennaKayttaja("admin", "admin@ticketguru.fi", "salasana", adminRooli, null);
            Kayttaja myyja1 = kayttajaService.luoJaTallennaKayttaja("myyja1", "myyja1@ticketguru.fi", "salasana1", myyjaRooli, myyntipiste1);
            Kayttaja myyja2 = kayttajaService.luoJaTallennaKayttaja("myyja2", "myyja2@ticketguru.fi", "salasana2", myyjaRooli, myyntipiste2);
            Kayttaja myyja3 = kayttajaService.luoJaTallennaKayttaja("myyja3", "myyja3@ticketguru.fi", "salasana3", myyjaRooli, myyntipiste3);
            Kayttaja myyja4 = kayttajaService.luoJaTallennaKayttaja("myyja4", "myyja4@ticketguru.fi", "salasana4", myyjaRooli, myyntipiste4);
            Kayttaja myyja5 = kayttajaService.luoJaTallennaKayttaja("myyja5", "myyja5@ticketguru.fi", "salasana5", myyjaRooli, myyntipiste5);
            Kayttaja myyja6 = kayttajaService.luoJaTallennaKayttaja("myyja6", "myyja6@ticketguru.fi", "salasana6", myyjaRooli, myyntipiste6);

            // Luodaan ensimmäinen myyntitapahtuma ja liput
                List<LippuDTO> lippuLista1 = new ArrayList<>(); // Luodaan lista lippuja varten
                
                LippuDTO lippu1 = new LippuDTO(); // Luodaan lippu
                lippu1.setLipputyyppiId(normaaliLippu.getLipputyyppiId());	// Lisätään lipputyypiksi normaali lippu
                lippu1.setTapahtumaId(tapahtuma1.getTapahtumaId()); // Lisätään tapahtumaan id
                lippu1.setMaara(2);  // 2 normaalia lippua
                lippuLista1.add(lippu1); //Lisätään liput listaan

                LippuDTO lippu2 = new LippuDTO(); // Luodaan lippu
                lippu2.setLipputyyppiId(vipLippu.getLipputyyppiId()); // Lisätään lipputyypiksi VIP-lippu
                lippu2.setTapahtumaId(tapahtuma1.getTapahtumaId()); // Lisätään tapahtumaan id
                lippu2.setMaara(1);  // 1 VIP-lippu
                lippuLista1.add(lippu2); // Lisätään liput listaan

                MyyntitapahtumaJaLiputDTO myyntitapahtuma1 = new MyyntitapahtumaJaLiputDTO(); //Luodaan MyyntitapahtumaJaLiputDTO
                myyntitapahtuma1.setKayttajaId(myyja1.getKayttajaId()); // Asetetaan myyjän id
                myyntitapahtuma1.setMaksutapaId(kateinen.getMaksutapaId()); // Asetetaan maksutavaksi "käteinen" ID
                myyntitapahtuma1.setLiput(lippuLista1); // Asetetaan liput myyntitapahtumaan
				
				// Tallennetaan myyntitapahtuma 1
				myyntitapahtumaService.luoMyyntitapahtumaJaLiput(myyntitapahtuma1);
                System.out.println("Myyntitapahtuma 1 luotu ja liput lisätty!");

            // Luodaan toinen myyntitapahtuma ja liput
                List<LippuDTO> lippuLista2 = new ArrayList<>();

                LippuDTO lippu3 = new LippuDTO();
                lippu3.setLipputyyppiId(normaaliLippu.getLipputyyppiId());
                lippu3.setTapahtumaId(tapahtuma2.getTapahtumaId());
                lippu3.setMaara(3);  // 3 normaalia lippua
                lippuLista2.add(lippu3);

                LippuDTO lippu4 = new LippuDTO();
                lippu4.setLipputyyppiId(vipLippu.getLipputyyppiId());
                lippu4.setTapahtumaId(tapahtuma2.getTapahtumaId());
                lippu4.setMaara(2);  // 2 VIP-lippua
                lippuLista2.add(lippu4);

                MyyntitapahtumaJaLiputDTO myyntitapahtuma2 = new MyyntitapahtumaJaLiputDTO();
                myyntitapahtuma2.setKayttajaId(myyja2.getKayttajaId());
                myyntitapahtuma2.setMaksutapaId(debit.getMaksutapaId());
                myyntitapahtuma2.setLiput(lippuLista2);
				
				// Tallennetaan myyntitapahtuma 2
				myyntitapahtumaService.luoMyyntitapahtumaJaLiput(myyntitapahtuma2);
                System.out.println("Myyntitapahtuma 2 luotu ja liput lisätty!");
            
            // Luodaan kolmas myyntitapahtuma ja liput
                List<LippuDTO> lippuLista3 = new ArrayList<>();

                LippuDTO lippu5 = new LippuDTO();
                lippu5.setLipputyyppiId(normaaliLippu.getLipputyyppiId());
                lippu5.setTapahtumaId(tapahtuma3.getTapahtumaId());
                lippu5.setMaara(2);  // 2 normaalia lippua
                lippuLista3.add(lippu5);

                LippuDTO lippu6 = new LippuDTO();
                lippu6.setLipputyyppiId(vipLippu.getLipputyyppiId());
                lippu6.setTapahtumaId(tapahtuma3.getTapahtumaId());
                lippu6.setMaara(1);  // 1 VIP-lippu
                lippuLista3.add(lippu6);

                MyyntitapahtumaJaLiputDTO myyntitapahtuma3 = new MyyntitapahtumaJaLiputDTO();
                myyntitapahtuma3.setKayttajaId(myyja3.getKayttajaId());
                myyntitapahtuma3.setMaksutapaId(kateinen.getMaksutapaId());
                myyntitapahtuma3.setLiput(lippuLista3);    
            
				// Tallennetaan myyntitapahtuma 3
				myyntitapahtumaService.luoMyyntitapahtumaJaLiput(myyntitapahtuma3);
                System.out.println("Myyntitapahtuma 3 luotu ja liput lisätty!");
				
            // Luodaan neljäs myyntitapahtuma ja liput
                List<LippuDTO> lippuLista4 = new ArrayList<>();

                LippuDTO lippu7 = new LippuDTO();
                lippu7.setLipputyyppiId(normaaliLippu.getLipputyyppiId());
                lippu7.setTapahtumaId(tapahtuma4.getTapahtumaId());
                lippu7.setMaara(1);  // 1 Normaali-lippu
                lippuLista4.add(lippu7);

                LippuDTO lippu8 = new LippuDTO();
                lippu8.setLipputyyppiId(opiskelijaLippu.getLipputyyppiId());
                lippu8.setTapahtumaId(tapahtuma4.getTapahtumaId());
                lippu8.setMaara(1);  // 1 Opiskelija-lippu
                lippuLista4.add(lippu8);

                LippuDTO lippu9 = new LippuDTO();
                lippu9.setLipputyyppiId(vipLippu.getLipputyyppiId());
                lippu9.setTapahtumaId(tapahtuma4.getTapahtumaId());
                lippu9.setMaara(1);  // 1 VIP-lippu
                lippuLista4.add(lippu9);

                MyyntitapahtumaJaLiputDTO myyntitapahtuma4 = new MyyntitapahtumaJaLiputDTO();
                myyntitapahtuma4.setKayttajaId(myyja4.getKayttajaId());
                myyntitapahtuma4.setMaksutapaId(mobilepay.getMaksutapaId());
                myyntitapahtuma4.setLiput(lippuLista4);
				
				// Tallennetaan myyntitapahtuma 4
				myyntitapahtumaService.luoMyyntitapahtumaJaLiput(myyntitapahtuma4);
                System.out.println("Myyntitapahtuma 4 luotu ja liput lisätty!");

            // Luodaan viides myyntitapahtuma ja liput
                List<LippuDTO> lippuLista5 = new ArrayList<>();

                LippuDTO lippu10 = new LippuDTO();
                lippu10.setLipputyyppiId(opiskelijaLippu.getLipputyyppiId());
                lippu10.setTapahtumaId(tapahtuma5.getTapahtumaId());
                lippu10.setMaara(2);  // 2 VIP-lippua
                lippuLista5.add(lippu10);

                LippuDTO lippu11 = new LippuDTO();
                lippu11.setLipputyyppiId(vipLippu.getLipputyyppiId());
                lippu11.setTapahtumaId(tapahtuma5.getTapahtumaId());
                lippu11.setMaara(2);  // 2 VIP-lippua
                lippuLista5.add(lippu11);

                MyyntitapahtumaJaLiputDTO myyntitapahtuma5 = new MyyntitapahtumaJaLiputDTO();
                myyntitapahtuma5.setKayttajaId(myyja5.getKayttajaId());
                myyntitapahtuma5.setMaksutapaId(paypal.getMaksutapaId());
                myyntitapahtuma5.setLiput(lippuLista5);
				
				// Tallennetaan myyntitapahtuma 5
				myyntitapahtumaService.luoMyyntitapahtumaJaLiput(myyntitapahtuma5);
                System.out.println("Myyntitapahtuma 5 luotu ja liput lisätty!");

            // Luodaan kuudes myyntitapahtuma ja liput
                List<LippuDTO> lippuLista6 = new ArrayList<>();

                LippuDTO lippu12 = new LippuDTO();
                lippu12.setLipputyyppiId(perheLippu.getLipputyyppiId());
                lippu12.setTapahtumaId(tapahtuma6.getTapahtumaId());
                lippu12.setMaara(1);  // 1 Perhe-lippu
                lippuLista6.add(lippu12);

                LippuDTO lippu13 = new LippuDTO();
                lippu13.setLipputyyppiId(opiskelijaLippu.getLipputyyppiId());
                lippu13.setTapahtumaId(tapahtuma6.getTapahtumaId());
                lippu13.setMaara(1);  // 1 Opiskelija-lippu
                lippuLista6.add(lippu13);

                MyyntitapahtumaJaLiputDTO myyntitapahtuma6 = new MyyntitapahtumaJaLiputDTO();
                myyntitapahtuma6.setKayttajaId(myyja6.getKayttajaId());
                myyntitapahtuma6.setMaksutapaId(debit.getMaksutapaId());
                myyntitapahtuma6.setLiput(lippuLista6);
				
				// Tallennetaan myyntitapahtuma 6
				myyntitapahtumaService.luoMyyntitapahtumaJaLiput(myyntitapahtuma6);
                System.out.println("Myyntitapahtuma 6 luotu ja liput lisätty!");

            // Luodaan seitsemäs myyntitapahtuma ja liput
                List<LippuDTO> lippuLista7 = new ArrayList<>();

                LippuDTO lippu14 = new LippuDTO();
                lippu14.setLipputyyppiId(normaaliLippu.getLipputyyppiId());
                lippu14.setTapahtumaId(tapahtuma7.getTapahtumaId());
                lippu14.setMaara(5);  // 5 normaalia lippua
                lippuLista7.add(lippu14);

                LippuDTO lippu15 = new LippuDTO();
                lippu15.setLipputyyppiId(vipLippu.getLipputyyppiId());
                lippu15.setTapahtumaId(tapahtuma7.getTapahtumaId());
                lippu15.setMaara(2);  // 2 VIP-lippua
                lippuLista7.add(lippu15);

                MyyntitapahtumaJaLiputDTO myyntitapahtuma7 = new MyyntitapahtumaJaLiputDTO();
                myyntitapahtuma7.setKayttajaId(myyja6.getKayttajaId());
                myyntitapahtuma7.setMaksutapaId(mobilepay.getMaksutapaId());
                myyntitapahtuma7.setLiput(lippuLista7);

				// Tallennetaan myyntitapahtuma 7
				myyntitapahtumaService.luoMyyntitapahtumaJaLiput(myyntitapahtuma7);
                System.out.println("Myyntitapahtuma 7 luotu ja liput lisätty!"); */
        };
    }
}