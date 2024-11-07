package bugivelhot.ticketguru.initializer;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import bugivelhot.ticketguru.exception.ResourceAlreadyExistsException;
import bugivelhot.ticketguru.model.Osoite;
import bugivelhot.ticketguru.service.OsoiteService;
import bugivelhot.ticketguru.service.TapahtumaService;

@Component
public class TapahtumaInitializer {

	private final TapahtumaService tapahtumaService;
	private final OsoiteService osoiteService;

	public TapahtumaInitializer(TapahtumaService tapahtumaService, OsoiteService osoiteService) {
		this.tapahtumaService = tapahtumaService;
		this.osoiteService = osoiteService;
	}

	public void luoTapahtumat() {
		// haetaan osoitteet
		Osoite osoite1 = osoiteService.haeOsoitePostinumerolla("00540").get();
		Osoite osoite2 = osoiteService.haeOsoitePostinumerolla("20100").get();
		Osoite osoite3 = osoiteService.haeOsoitePostinumerolla("33100").get();
		Osoite osoite4 = osoiteService.haeOsoitePostinumerolla("90100").get();
		Osoite osoite5 = osoiteService.haeOsoitePostinumerolla("40100").get();
		Osoite osoite6 = osoiteService.haeOsoitePostinumerolla("80100").get();

		// luo tapahtumat
		luoTapahtuma("Tuska Festival 2025",
				"Tuska on Helsingin Suvilahdessa järjestettävä metallimusiikkiin keskittynyt festivaali.",
				"Festivaali",
				LocalDateTime.of(2025, 6, 27, 12, 0),
				LocalDateTime.of(2025, 6, 29, 23, 0),
				"Kaasutehtaankatu 1", osoite1, 1000);

		luoTapahtuma("Ruisrock 2025",
				"Ruisrock on Turun Ruissalossa järjestettävä musiikkifestivaali.",
				"Festivaali",
				LocalDateTime.of(2025, 7, 4, 12, 0),
				LocalDateTime.of(2025, 7, 6, 23, 0),
				"Ruissalon puistotie 1", osoite2, 2000);

		luoTapahtuma("Flow Festival 2025",
				"Flow Festival on Helsingin Suvilahdessa järjestettävä kaupunkikulttuuri- ja musiikkifestivaali.",
				"Festivaali",
				LocalDateTime.of(2025, 8, 8, 12, 0),
				LocalDateTime.of(2025, 8, 10, 23, 0),
				"Kaasutehtaankatu 1", osoite1, 1500);

		luoTapahtuma("Blockfest 2025",
				"Blockfest on Tampereella järjestettävä hiphop-musiikkiin keskittynyt festivaali.",
				"Festivaali",
				LocalDateTime.of(2025, 8, 20, 12, 0),
				LocalDateTime.of(2025, 8, 22, 23, 0),
				"Ratinan Stadion", osoite3, 1800);

		luoTapahtuma("Qstock 2025",
				"Qstock on Oulussa järjestettävä monipuolinen musiikkifestivaali.",
				"Festivaali",
				LocalDateTime.of(2025, 7, 24, 12, 0),
				LocalDateTime.of(2025, 7, 25, 23, 0),
				"Kuusisaaren puistotie 1", osoite4, 1200);

		luoTapahtuma("Jyrock 2025",
				"Jyrock on Jyväskylässä järjestettävä indie-musiikkifestivaali.",
				"Festivaali",
				LocalDateTime.of(2025, 5, 15, 14, 0),
				LocalDateTime.of(2025, 5, 16, 22, 0),
				"Ilokivi", osoite5, 800);

		luoTapahtuma("Ilosaarirock 2025",
				"Ilosaarirock on Joensuussa järjestettävä perinteikäs festivaali.",
				"Festivaali",
				LocalDateTime.of(2025, 7, 17, 12, 0),
				LocalDateTime.of(2025, 7, 19, 23, 0),
				"Laulurinne", osoite6, 1500);
	}

	private void luoTapahtuma(String nimi, String kuvaus, String kategoria, LocalDateTime aloituspvm,
			LocalDateTime lopetuspvm, String katuosoite, Osoite osoite, int lippujaJaljella) {
		try {
			tapahtumaService.luoJaTallennaTapahtuma(nimi, kuvaus, kategoria, aloituspvm, lopetuspvm,
					katuosoite, osoite, lippujaJaljella);
		} catch (ResourceAlreadyExistsException e) {
			System.out.println("Tapahtuma '" + nimi + "' on jo olemassa.");
		}
	}
}
