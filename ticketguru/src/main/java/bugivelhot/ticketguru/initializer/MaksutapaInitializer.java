package bugivelhot.ticketguru.initializer;

import org.springframework.stereotype.Component;

import bugivelhot.ticketguru.service.MaksutapaService;

@Component
public class MaksutapaInitializer {

    private final MaksutapaService maksutapaService;

    public MaksutapaInitializer(MaksutapaService maksutapaService) {
        this.maksutapaService = maksutapaService;
    }

    public void luoMaksutavat() {
        // Maksutavat luodaan tässä
        maksutapaService.luoJaTallennaMaksutapa("Käteinen");
        maksutapaService.luoJaTallennaMaksutapa("Debit");
        maksutapaService.luoJaTallennaMaksutapa("Credit");
        maksutapaService.luoJaTallennaMaksutapa("MobilePay");
        maksutapaService.luoJaTallennaMaksutapa("PayPal");
    }

}
