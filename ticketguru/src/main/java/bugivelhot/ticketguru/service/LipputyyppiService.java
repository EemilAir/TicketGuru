package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.Lipputyyppi;
import bugivelhot.ticketguru.repository.LipputyyppiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LipputyyppiService {

    @Autowired
    private LipputyyppiRepository lipputyyppiRepository;

    public Lipputyyppi luoJaTallennaLipputyyppi(String nimi, String kuvaus) {
        Lipputyyppi lipputyyppi = new Lipputyyppi(nimi, kuvaus);
        return lipputyyppiRepository.save(lipputyyppi);
    }
}