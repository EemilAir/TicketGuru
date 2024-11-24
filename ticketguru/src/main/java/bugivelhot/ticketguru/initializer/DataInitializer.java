package bugivelhot.ticketguru.initializer;

import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final KayttajaInitializer kayttajaInitializer;
    private final LipputyyppiInitializer lipputyyppiInitializer;
    private final LipunmyyntipisteInitializer lipunmyyntipisteInitializer;
    private final MaksutapaInitializer maksutapaInitializer;
    private final MyyntitapahtumaInitializer myyntitapahtumaInitializer;
    private final OsoiteInitializer osoiteInitializer;
    private final TapahtumaInitializer tapahtumaInitializer;
    private final TapahtumanLipputyyppiInitializer tapahtumanLipputyyppiInitializer;

    public DataInitializer(KayttajaInitializer kayttajaInitializer, LipputyyppiInitializer lipputyyppiInitializer, LipunmyyntipisteInitializer lipunmyyntipisteInitializer, MaksutapaInitializer maksutapaInitializer, MyyntitapahtumaInitializer myyntitapahtumaInitializer, OsoiteInitializer osoiteInitializer, TapahtumaInitializer tapahtumaInitializer, TapahtumanLipputyyppiInitializer tapahtumanLipputyyppiInitializer) {
        this.kayttajaInitializer = kayttajaInitializer;
        this.lipputyyppiInitializer = lipputyyppiInitializer;
        this.lipunmyyntipisteInitializer = lipunmyyntipisteInitializer;
        this.maksutapaInitializer = maksutapaInitializer;
        this.myyntitapahtumaInitializer = myyntitapahtumaInitializer;
        this.osoiteInitializer = osoiteInitializer;
        this.tapahtumaInitializer = tapahtumaInitializer;
        this.tapahtumanLipputyyppiInitializer = tapahtumanLipputyyppiInitializer;
    }

    public void initialize(){
        maksutapaInitializer.luoMaksutavat();
        osoiteInitializer.luoOsoitteet();
        lipunmyyntipisteInitializer.luoLipunmyyntipisteet();
        lipputyyppiInitializer.luoLipputyypit();
        tapahtumaInitializer.luoTapahtumat();
        tapahtumanLipputyyppiInitializer.luoTapahtumanLipputyypit();
        kayttajaInitializer.luoKayttajat();
        myyntitapahtumaInitializer.luoMyyntitapahtumat();
    }

}
