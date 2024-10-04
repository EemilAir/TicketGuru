package bugivelhot.ticketguru.service;

import bugivelhot.ticketguru.model.Maksutapa;
import bugivelhot.ticketguru.repository.MaksutapaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaksutapaService {

    @Autowired
    private MaksutapaRepository maksutapaRepository;

    public Maksutapa luoJaTallennaMaksutapa(String maksutapaNimi) {
        Maksutapa maksutapa = new Maksutapa(maksutapaNimi);
        return maksutapaRepository.save(maksutapa);
    }
}