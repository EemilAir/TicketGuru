package bugivelhot.ticketguru.initializer;

import org.springframework.stereotype.Component;

import bugivelhot.ticketguru.service.OsoiteService;

@Component
public class OsoiteInitializer {

    private final OsoiteService osoiteService;
    public OsoiteInitializer(OsoiteService osoiteService) {
        this.osoiteService = osoiteService;
    }

    public void luoOsoitteet(){
        osoiteService.luoJaTallennaOsoite("00540", "Helsinki");
        osoiteService.luoJaTallennaOsoite("20100", "Turku");
        osoiteService.luoJaTallennaOsoite("33100", "Tampere");
        osoiteService.luoJaTallennaOsoite("90100", "Oulu");
        osoiteService.luoJaTallennaOsoite("40100", "Jyväskylä");
        osoiteService.luoJaTallennaOsoite("80100", "Joensuu");
    }
}
