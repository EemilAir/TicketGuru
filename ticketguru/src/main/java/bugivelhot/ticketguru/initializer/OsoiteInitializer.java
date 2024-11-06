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
        osoiteService.luoOsoitteet();
    }
}
