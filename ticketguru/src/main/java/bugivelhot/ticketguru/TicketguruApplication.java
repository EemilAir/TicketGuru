package bugivelhot.ticketguru;

import bugivelhot.ticketguru.initializer.DataInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketguruApplication {

    private final DataInitializer dataInitializer;

    public TicketguruApplication(DataInitializer dataInitializer) {
        this.dataInitializer = dataInitializer;
    }

    public static void main(String[] args) {
        SpringApplication.run(TicketguruApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {
            dataInitializer.initialize();
        };
    }
}