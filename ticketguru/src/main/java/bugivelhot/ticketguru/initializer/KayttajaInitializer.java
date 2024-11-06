package bugivelhot.ticketguru.initializer;

import org.springframework.stereotype.Component;

import bugivelhot.ticketguru.model.Kayttaja;
import bugivelhot.ticketguru.model.Lipunmyyntipiste;
import bugivelhot.ticketguru.service.KayttajaService;
import bugivelhot.ticketguru.service.LipunmyyntipisteService;

@Component
public class KayttajaInitializer {

    private final KayttajaService kayttajaService;
    private final LipunmyyntipisteService lipunmyyntipisteService;

    public KayttajaInitializer(KayttajaService kayttajaService, LipunmyyntipisteService lipunmyyntipisteService) {
        this.kayttajaService = kayttajaService;
        this.lipunmyyntipisteService = lipunmyyntipisteService;
    }

    public void luoKayttajat() {

        // haetaan myyntipisteet
        Lipunmyyntipiste myyntipiste1 = lipunmyyntipisteService.haeMyyntipisteNimella("Lippupiste 1");
        Lipunmyyntipiste myyntipiste2 = lipunmyyntipisteService.haeMyyntipisteNimella("Lippupiste 2");
        Lipunmyyntipiste myyntipiste3 = lipunmyyntipisteService.haeMyyntipisteNimella("Lippupiste 3");
        Lipunmyyntipiste myyntipiste4 = lipunmyyntipisteService.haeMyyntipisteNimella("Lippupiste 4");
        Lipunmyyntipiste myyntipiste5 = lipunmyyntipisteService.haeMyyntipisteNimella("Lippupiste 5");
        Lipunmyyntipiste myyntipiste6 = lipunmyyntipisteService.haeMyyntipisteNimella("Lippupiste 6");

        // Käyttäjäroolit
        Kayttaja.Rooli adminRooli = Kayttaja.Rooli.ADMIN;
        Kayttaja.Rooli myyjaRooli = Kayttaja.Rooli.MYYJA;

        // Käyttäjät
        kayttajaService.luoJaTallennaKayttaja("admin", "admin@ticketguru.fi", "salasana", adminRooli,
                null);
        kayttajaService.luoJaTallennaKayttaja("myyja1", "myyja1@ticketguru.fi", "salasana1",
                myyjaRooli, myyntipiste1);
        kayttajaService.luoJaTallennaKayttaja("myyja2", "myyja2@ticketguru.fi", "salasana2",
                myyjaRooli, myyntipiste2);
        kayttajaService.luoJaTallennaKayttaja("myyja3", "myyja3@ticketguru.fi", "salasana3",
                myyjaRooli, myyntipiste3);
        kayttajaService.luoJaTallennaKayttaja("myyja4", "myyja4@ticketguru.fi", "salasana4",
                myyjaRooli, myyntipiste4);
        kayttajaService.luoJaTallennaKayttaja("myyja5", "myyja5@ticketguru.fi", "salasana5",
                myyjaRooli, myyntipiste5);
        kayttajaService.luoJaTallennaKayttaja("myyja6", "myyja6@ticketguru.fi", "salasana6",
                myyjaRooli, myyntipiste6);
    }

}
