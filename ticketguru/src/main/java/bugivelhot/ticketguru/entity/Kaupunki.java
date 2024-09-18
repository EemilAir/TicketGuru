package bugivelhot.ticketguru.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "kaupungit")
public class Kaupunki {

    // kaupunki_id-sarake tietokannassa
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automaattisesti kasvava pääavain
    @Column(name = "kaupunki_id")
    private int kaupunkiId;

    // kaupungin_nimi-sarake tietokannassa
    @Column(name = "kaupungin_nimi", nullable = false, length = 100)
    private String kaupunginNimi;

    // Konstruktorit
    public Kaupunki(String kaupunginNimi) {
        this.kaupunginNimi = kaupunginNimi;
    }

    public Kaupunki() {
    }

    // getterit ja setterit
    public int getKaupunkiId() {
        return kaupunkiId;
    }

    public void setKaupunkiId(int kaupunkiId) {
        this.kaupunkiId = kaupunkiId;
    }

    public String getKaupunginNimi() {
        return kaupunginNimi;
    }

    public void setKaupunginNimi(String kaupunginNimi) {
        this.kaupunginNimi = kaupunginNimi;
    }

}
