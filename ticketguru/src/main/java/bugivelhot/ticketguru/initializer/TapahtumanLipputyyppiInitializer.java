package bugivelhot.ticketguru.initializer;

import org.springframework.stereotype.Component;

import bugivelhot.ticketguru.model.Lipputyyppi;
import bugivelhot.ticketguru.model.Tapahtuma;
import bugivelhot.ticketguru.service.LipputyyppiService;
import bugivelhot.ticketguru.service.TapahtumaService;

@Component
public class TapahtumanLipputyyppiInitializer {

    private final TapahtumaService tapahtumaService;
    private final LipputyyppiService lipputyyppiService;

    public TapahtumanLipputyyppiInitializer(TapahtumaService tapahtumaService, LipputyyppiService lipputyyppiService) {
        this.tapahtumaService = tapahtumaService;
        this.lipputyyppiService = lipputyyppiService;
    }

    public void luoTapahtumanLipputyypit() {
        // haetaan lipputyypit
        Lipputyyppi normaaliLippu = lipputyyppiService.haeLipputyyppiNimella("Normaali");
        Lipputyyppi vipLippu = lipputyyppiService.haeLipputyyppiNimella("VIP");
        Lipputyyppi opiskelijaLippu = lipputyyppiService.haeLipputyyppiNimella("Opiskelija");
        Lipputyyppi perheLippu = lipputyyppiService.haeLipputyyppiNimella("Perhelippu");
        Lipputyyppi elakelaisLippu = lipputyyppiService.haeLipputyyppiNimella("Eläkeläinen");

        // haetaan tapahtumat
        Tapahtuma tapahtuma1 = tapahtumaService.haeTapahtumaNimella("Tuska Festival 2025");
        Tapahtuma tapahtuma2 = tapahtumaService.haeTapahtumaNimella("Ruisrock 2025");
        Tapahtuma tapahtuma3 = tapahtumaService.haeTapahtumaNimella("Flow Festival 2025");
        Tapahtuma tapahtuma4 = tapahtumaService.haeTapahtumaNimella("Blockfest 2025");
        Tapahtuma tapahtuma5 = tapahtumaService.haeTapahtumaNimella("Qstock 2025");
        Tapahtuma tapahtuma6 = tapahtumaService.haeTapahtumaNimella("Jyrock 2025");
        Tapahtuma tapahtuma7 = tapahtumaService.haeTapahtumaNimella("Ilosaarirock 2025");

        // luodaan tapahtuman lipputyypit
        // Tapahtuma1
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma1, normaaliLippu, 25.0);
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma1, vipLippu, 50.0);

        // Tapahtuma2
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma2, normaaliLippu, 30.0);
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma2, vipLippu, 60.0);

        // Tapahtuma3
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma3, normaaliLippu, 35.0);
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma3, vipLippu, 70.0);

        // Tapahtuma4
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma4, normaaliLippu, 30.0);
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma4, opiskelijaLippu, 20.0);
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma4, vipLippu, 65.0);

        // Tapahtuma5
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma5, normaaliLippu, 25.0);
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma5, opiskelijaLippu, 20.0);
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma5, vipLippu, 45.0);
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma5, elakelaisLippu, 15.0);

        // Tapahtuma6
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma6, normaaliLippu, 20.0);
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma6, opiskelijaLippu, 15.0);
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma6, perheLippu, 60.0);

        // Tapahtuma7
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma7, normaaliLippu, 30.0);
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma7, perheLippu, 75.0);
        tapahtumaService.luoJaTallennaTapahtumanLipputyyppi(tapahtuma7, vipLippu, 65.0);
    }

}
