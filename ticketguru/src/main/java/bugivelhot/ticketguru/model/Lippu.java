package bugivelhot.ticketguru.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "liput") // Määrittää, että tämä entiteetti vastaa tietokantataulua "liput"
public class Lippu {

    // Tietokantataulun kentät
    // Määrittää, että lippuId on pääavain ja se generoidaan automaattisesti
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lippuId;
    
    // Koodi ei voi olla tyhjä, koodia voi validoida myös muilla tavoilla, esim. pituus
    @NotBlank(message = "Lipun koodi ei voi olla tyhjä")
    @Column(unique = true) // Koodi on uniikki
    private String koodi;

    // Luontiaika asetetaan automaattisesti, ei tarvita validointia
    private LocalDateTime luontiaika;

    // Lipun tila voi olla AKTIIVINEN tai KAYTETTY
    public enum Tila {
        AKTIIVINEN,
        KAYTETTY
    }

    // Ei tarvita validointia, koska enum on määritelty, tila on pakollinen ja arvo asetetaan automaattisesti
    @Enumerated(EnumType.STRING) // Tallennetaan tila tietokantaan merkkijonona (esim. 'AKTIIVINEN' tai 'KAYTETTY')
    private Tila lipunTila;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "tapahtuma_id")
    @NotNull(message = "Tapahtuma ei voi olla tyhjä")
    private Tapahtuma tapahtuma;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "myyntitapahtuma_id")
    private Myyntitapahtuma myyntitapahtuma;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "lipputyyppi_id")
    @NotNull(message = "Lipputyyppi ei voi olla tyhjä")
    private Lipputyyppi lipputyyppi;

    public Lippu() {
    }

    // Lisätään oletusarvot lippuun ennen tietokantaan tallentamista
    @PrePersist
    public void prePersist() {
        // koodiksi uniikki merkkijono käyttäen UUID
        this.koodi = java.util.UUID.randomUUID().toString();

        // luontiajaksi asetetaan nykyhetki
        this.luontiaika = LocalDateTime.now();

        // tilaksi asetetaan AKTIIVINEN
        this.lipunTila = Tila.AKTIIVINEN;
    }

    // getterit ja setterit
    public Long getLippuId() {
        return lippuId;
    }

    public void setLippuId(Long lippuId) {
        this.lippuId = lippuId;
    }

    public String getKoodi() {
        return koodi;
    }

    public void setKoodi(String koodi) {
        this.koodi = koodi;
    }

    public LocalDateTime getLuontiaika() {
        return luontiaika;
    }

    public void setLuontiaika(LocalDateTime luontiaika) {
        this.luontiaika = luontiaika;
    }

    /* public LocalDateTime getMyyntiaika() {
        return myyntiaika;
    }

    public void setMyyntiaika(LocalDateTime myyntiaika) {
        this.myyntiaika = myyntiaika;
    } */

    public Tila getLipunTila() {
        return lipunTila;
    }

    public void setLipunTila(Tila lipunTila) {
        this.lipunTila = lipunTila;
    }

    public Tapahtuma getTapahtuma() {
        return tapahtuma;
    }

    public void setTapahtuma(Tapahtuma tapahtuma) {
        this.tapahtuma = tapahtuma;
    }

    public Myyntitapahtuma getMyyntitapahtuma() {
        return myyntitapahtuma;
    }

    public void setMyyntitapahtuma(Myyntitapahtuma myyntitapahtuma) {
        this.myyntitapahtuma = myyntitapahtuma;
    }

    public Lipputyyppi getLipputyyppi() {
        return lipputyyppi;
    }

    public void setLipputyyppi(Lipputyyppi lipputyyppi) {
        this.lipputyyppi = lipputyyppi;
    }

    @Override
    public String toString() {
        return "Lippu [lippuId=" + lippuId + ", koodi=" + koodi + ", luontiaika=" + luontiaika + /* ", myyntiaika="
                + myyntiaika + */  ", lipunTila=" + lipunTila + ", tapahtuma=" + tapahtuma + ", myyntitapahtuma="
                + myyntitapahtuma + ", lipputyyppi=" + lipputyyppi + "]";
    }

}
