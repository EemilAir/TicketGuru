package bugivelhot.ticketguru.initializer;

import org.springframework.stereotype.Component;

import bugivelhot.ticketguru.service.LipputyyppiService;

@Component
public class LipputyyppiInitializer {

    private final LipputyyppiService lipputyyppiService;

    public LipputyyppiInitializer(LipputyyppiService lipputyyppiService) {
        this.lipputyyppiService = lipputyyppiService;
    }

    public void luoLipputyypit() {
        // luodaan lipputyypit
        lipputyyppiService.luoJaTallennaLipputyyppi("Normaali", "Normaali lippu");
        lipputyyppiService.luoJaTallennaLipputyyppi("VIP", "VIP lippu");
        lipputyyppiService.luoJaTallennaLipputyyppi("Opiskelija",
                "Alennettu opiskelijalippu");
        lipputyyppiService.luoJaTallennaLipputyyppi("Perhelippu",
                "Perhelippu 2 aikuista ja 2 lasta");
        lipputyyppiService.luoJaTallennaLipputyyppi("Eläkeläinen",
                "Alennettu eläkeläislippu");
    }

}
