package bugivelhot.ticketguru.initializer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import bugivelhot.ticketguru.dto.LippuDTO;
import bugivelhot.ticketguru.dto.MyyntitapahtumaJaLiputDTO;
import bugivelhot.ticketguru.model.Kayttaja;
import bugivelhot.ticketguru.model.Lipputyyppi;
import bugivelhot.ticketguru.model.Maksutapa;
import bugivelhot.ticketguru.model.Tapahtuma;
import bugivelhot.ticketguru.service.KayttajaService;
import bugivelhot.ticketguru.service.LipputyyppiService;
import bugivelhot.ticketguru.service.MaksutapaService;
import bugivelhot.ticketguru.service.MyyntitapahtumaService;
import bugivelhot.ticketguru.service.TapahtumaService;

@Component
public class MyyntitapahtumaInitializer {

    private final MyyntitapahtumaService myyntitapahtumaService;
    private final LipputyyppiService lipputyyppiService;
    private final TapahtumaService tapahtumaService;
    private final KayttajaService kayttajaService;
    private final MaksutapaService maksutapaService;

    public MyyntitapahtumaInitializer(MyyntitapahtumaService myyntitapahtumaService,
            LipputyyppiService lipputyyppiService, TapahtumaService tapahtumaService, KayttajaService kayttajaService,
            MaksutapaService maksutapaService) {
        this.myyntitapahtumaService = myyntitapahtumaService;
        this.lipputyyppiService = lipputyyppiService;
        this.tapahtumaService = tapahtumaService;
        this.kayttajaService = kayttajaService;
        this.maksutapaService = maksutapaService;
    }

    public void luoMyyntitapahtumat() {

        // haetaan lipputyypit
        Lipputyyppi normaaliLippu = lipputyyppiService.haeLipputyyppiNimella("Normaali");
        Lipputyyppi vipLippu = lipputyyppiService.haeLipputyyppiNimella("VIP");
        Lipputyyppi opiskelijaLippu = lipputyyppiService.haeLipputyyppiNimella("Opiskelija");
        Lipputyyppi perheLippu = lipputyyppiService.haeLipputyyppiNimella("Perhe");

        // haetaan tapahtumat
        Tapahtuma tapahtuma1 = tapahtumaService.haeTapahtumaNimella("Tuska Festival 2025");
        Tapahtuma tapahtuma2 = tapahtumaService.haeTapahtumaNimella("Ruisrock 2025");
        Tapahtuma tapahtuma3 = tapahtumaService.haeTapahtumaNimella("Flow Festival 2025");
        Tapahtuma tapahtuma4 = tapahtumaService.haeTapahtumaNimella("Blockfest 2025");
        Tapahtuma tapahtuma5 = tapahtumaService.haeTapahtumaNimella("Qstock 2025");
        Tapahtuma tapahtuma6 = tapahtumaService.haeTapahtumaNimella("Jyrock 2025");
        Tapahtuma tapahtuma7 = tapahtumaService.haeTapahtumaNimella("Ilosaarirock 2025");

        // haetaan käyttäjät
        Kayttaja myyja1 = kayttajaService.haeKayttajaNimella("myyja1");
        Kayttaja myyja2 = kayttajaService.haeKayttajaNimella("myyja2");
        Kayttaja myyja3 = kayttajaService.haeKayttajaNimella("myyja3");
        Kayttaja myyja4 = kayttajaService.haeKayttajaNimella("myyja4");
        Kayttaja myyja5 = kayttajaService.haeKayttajaNimella("myyja5");
        Kayttaja myyja6 = kayttajaService.haeKayttajaNimella("myyja6");

        // haetaan maksutavat
        Maksutapa kateinen = maksutapaService.haeMaksutapaNimella("Käteinen");
        Maksutapa debit = maksutapaService.haeMaksutapaNimella("Debit");
        Maksutapa mobilepay = maksutapaService.haeMaksutapaNimella("MobilePay");
        Maksutapa paypal = maksutapaService.haeMaksutapaNimella("PayPal");

        // Luodaan ensimmäinen myyntitapahtuma ja liput
        List<LippuDTO> lippuLista1 = new ArrayList<>(); // Luodaan lista lippuja varten

        LippuDTO lippu1 = new LippuDTO(); // Luodaan lippu
        lippu1.setLipputyyppiId(normaaliLippu.getLipputyyppiId()); // Lisätään lipputyypiksi normaali lippu
        lippu1.setTapahtumaId(tapahtuma1.getTapahtumaId()); // Lisätään tapahtumaan id
        lippu1.setMaara(2); // 2 normaalia lippua
        lippuLista1.add(lippu1); // Lisätään liput listaan

        LippuDTO lippu2 = new LippuDTO(); // Luodaan lippu
        lippu2.setLipputyyppiId(vipLippu.getLipputyyppiId()); // Lisätään lipputyypiksi VIP-lippu
        lippu2.setTapahtumaId(tapahtuma1.getTapahtumaId()); // Lisätään tapahtumaan id
        lippu2.setMaara(1); // 1 VIP-lippu
        lippuLista1.add(lippu2); // Lisätään liput listaan

        MyyntitapahtumaJaLiputDTO myyntitapahtuma1 = new MyyntitapahtumaJaLiputDTO(); // Luodaan
                                                                                      // MyyntitapahtumaJaLiputDTO
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
        lippu3.setMaara(3); // 3 normaalia lippua
        lippuLista2.add(lippu3);

        LippuDTO lippu4 = new LippuDTO();
        lippu4.setLipputyyppiId(vipLippu.getLipputyyppiId());
        lippu4.setTapahtumaId(tapahtuma2.getTapahtumaId());
        lippu4.setMaara(2); // 2 VIP-lippua
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
        lippu5.setMaara(2); // 2 normaalia lippua
        lippuLista3.add(lippu5);

        LippuDTO lippu6 = new LippuDTO();
        lippu6.setLipputyyppiId(vipLippu.getLipputyyppiId());
        lippu6.setTapahtumaId(tapahtuma3.getTapahtumaId());
        lippu6.setMaara(1); // 1 VIP-lippu
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
        lippu7.setMaara(1); // 1 Normaali-lippu
        lippuLista4.add(lippu7);

        LippuDTO lippu8 = new LippuDTO();
        lippu8.setLipputyyppiId(opiskelijaLippu.getLipputyyppiId());
        lippu8.setTapahtumaId(tapahtuma4.getTapahtumaId());
        lippu8.setMaara(1); // 1 Opiskelija-lippu
        lippuLista4.add(lippu8);

        LippuDTO lippu9 = new LippuDTO();
        lippu9.setLipputyyppiId(vipLippu.getLipputyyppiId());
        lippu9.setTapahtumaId(tapahtuma4.getTapahtumaId());
        lippu9.setMaara(1); // 1 VIP-lippu
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
        lippu10.setMaara(2); // 2 VIP-lippua
        lippuLista5.add(lippu10);

        LippuDTO lippu11 = new LippuDTO();
        lippu11.setLipputyyppiId(vipLippu.getLipputyyppiId());
        lippu11.setTapahtumaId(tapahtuma5.getTapahtumaId());
        lippu11.setMaara(2); // 2 VIP-lippua
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
        lippu12.setMaara(1); // 1 Perhe-lippu
        lippuLista6.add(lippu12);

        LippuDTO lippu13 = new LippuDTO();
        lippu13.setLipputyyppiId(opiskelijaLippu.getLipputyyppiId());
        lippu13.setTapahtumaId(tapahtuma6.getTapahtumaId());
        lippu13.setMaara(1); // 1 Opiskelija-lippu
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
        lippu14.setMaara(5); // 5 normaalia lippua
        lippuLista7.add(lippu14);

        LippuDTO lippu15 = new LippuDTO();
        lippu15.setLipputyyppiId(vipLippu.getLipputyyppiId());
        lippu15.setTapahtumaId(tapahtuma7.getTapahtumaId());
        lippu15.setMaara(2); // 2 VIP-lippua
        lippuLista7.add(lippu15);

        MyyntitapahtumaJaLiputDTO myyntitapahtuma7 = new MyyntitapahtumaJaLiputDTO();
        myyntitapahtuma7.setKayttajaId(myyja6.getKayttajaId());
        myyntitapahtuma7.setMaksutapaId(mobilepay.getMaksutapaId());
        myyntitapahtuma7.setLiput(lippuLista7);

        // Tallennetaan myyntitapahtuma 7
        myyntitapahtumaService.luoMyyntitapahtumaJaLiput(myyntitapahtuma7);
        System.out.println("Myyntitapahtuma 7 luotu ja liput lisätty!");

    }

}
