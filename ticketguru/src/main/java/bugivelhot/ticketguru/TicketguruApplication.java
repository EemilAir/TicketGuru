package bugivelhot.ticketguru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import bugivelhot.ticketguru.model.Tapahtuma;
import bugivelhot.ticketguru.repository.TapahtumaRepository;
import java.time.LocalDateTime;
import bugivelhot.ticketguru.model.Osoite;
import bugivelhot.ticketguru.repository.OsoiteRepository;

@SpringBootApplication
public class TicketguruApplication {

	@Bean
	public CommandLineRunner loadData(TapahtumaRepository tapahtumaRepository, OsoiteRepository osoiteRepository) {
		return (args) -> {
			Osoite osoite1 = new Osoite("00100", "Helsinki");
			Osoite osoite2 = new Osoite("00200", "Espoo");
			Osoite osoite3 = new Osoite("00300", "Vantaa");

			osoiteRepository.save(osoite1);
			osoiteRepository.save(osoite2);
			osoiteRepository.save(osoite3);

			tapahtumaRepository.save(new Tapahtuma("Tapahtuma 1", "Kuvaus 1", "Kategoria 1", 
				LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2), "Katuosoite1", osoite1, 100));
			tapahtumaRepository.save(new Tapahtuma("Tapahtuma 2", "Kuvaus 2", "Kategoria 2", 
				LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4), "Katuosoite2", osoite2, 200));
			tapahtumaRepository.save(new Tapahtuma("Tapahtuma 3", "Kuvaus 3", "Kategoria 3", 
				LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(6), "Katuosoite3", osoite3, 300));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(TicketguruApplication.class, args);
	}
}
