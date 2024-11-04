package bugivelhot.ticketguru.initializer;

import org.springframework.stereotype.Component;

import bugivelhot.ticketguru.model.Osoite;
import bugivelhot.ticketguru.service.LipunmyyntipisteService;
import bugivelhot.ticketguru.service.OsoiteService;

@Component
public class LipunmyyntipisteInitializer {

    private final LipunmyyntipisteService lipunmyyntipisteService;
    private final OsoiteService osoiteService;

    public LipunmyyntipisteInitializer(LipunmyyntipisteService lipunmyyntipisteService, OsoiteService osoiteService) {
        this.lipunmyyntipisteService = lipunmyyntipisteService;
        this.osoiteService = osoiteService;
    }

    public void luoLipunmyyntipisteet() {

        // haetaan osoitteet
        Osoite osoite1 = osoiteService.haeOsoitePostinumerolla("00100").get();
        Osoite osoite2 = osoiteService.haeOsoitePostinumerolla("00200").get();
        Osoite osoite3 = osoiteService.haeOsoitePostinumerolla("00540").get();
        Osoite osoite4 = osoiteService.haeOsoitePostinumerolla("20100").get();
        Osoite osoite5 = osoiteService.haeOsoitePostinumerolla("33100").get();
        Osoite osoite6 = osoiteService.haeOsoitePostinumerolla("90100").get();

        // Lipunmyyntipisteet luodaan tässä
        lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 1",
                "Esimerkkikatu 1", osoite1);
        lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 2",
                "Esimerkkikatu 2", osoite2);
        lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 3",
                "Keskustori 5", osoite3);
        lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 4",
                "Kauppatori 3", osoite4);
        lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 5",
                "Asemakatu 10", osoite5);
        lipunmyyntipisteService.luoJaTallennaLipunmyyntipiste("Lippupiste 6", "Tori 8",
                osoite6);

    }

}
