package bugivelhot.ticketguru.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class TapahtumaDTO {

    @NotBlank(message = "Tapahtuman nimi ei voi olla tyhjä")
    @Size(min = 3, max = 100, message = "Tapahtuman nimi voi olla korkeintaan 100 merkkiä pitkä ja 3 merkkiä lyhyt")
    private String nimi;

    @Size(min = 3, max = 500, message = "Tapahtuman kuvaus voi olla korkeintaan 500 merkkiä pitkä ja 3 merkkiä lyhyt")
    private String kuvaus;

    @NotBlank(message = "Tapahtuman kategoria ei voi olla tyhjä")
    @Size(min = 3, max = 75, message = "Tapahtuman kategoria voi olla korkeintaan 75 merkkiä pitkä ja 3 merkkiä lyhyt")
    private String kategoria;

    @NotNull(message = "Tapahtuman aloituspvm ei voi olla tyhjä")
    @FutureOrPresent(message = "Tapahtuman aloituspvm ei voi olla menneisyydessä")
    private LocalDateTime aloituspvm;

    @NotNull(message = "Tapahtuman lopetuspvm ei voi olla tyhjä")
    @FutureOrPresent(message = "Tapahtuman lopetuspvm ei voi olla menneisyydessä")
    private LocalDateTime lopetuspvm;

    @NotBlank(message = "Tapahtuman katuosoite ei voi olla tyhjä")
    @Size(min = 3, max = 100, message = "Tapahtuman katuosoite voi olla korkeintaan 100 merkkiä pitkä ja 3 merkkiä lyhyt")
    private String katuosoite;

    @NotNull(message = "LippujaJaljella ei voi olla null")
    @PositiveOrZero(message = "LippujaJaljella on oltava positiivinen tai nolla")
    private Integer lippujaJaljella;

    @NotNull(message = "OsoiteId ei voi olla null")
    @Positive(message = "OsoiteId on oltava positiivinen")
    private Long osoiteId;

    // sisältää lipputyypin id:n ja hinnan
    private List<LipputyyppiDTO> lipputyypit;

    // getterit ja setterit
    public String getNimi() {
        return nimi;
    }
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    public String getKuvaus() {
        return kuvaus;
    }
    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
    public String getKategoria() {
        return kategoria;
    }
    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }
    public LocalDateTime getAloituspvm() {
        return aloituspvm;
    }
    public void setAloituspvm(LocalDateTime aloituspvm) {
        this.aloituspvm = aloituspvm;
    }
    public LocalDateTime getLopetuspvm() {
        return lopetuspvm;
    }
    public void setLopetuspvm(LocalDateTime lopetuspvm) {
        this.lopetuspvm = lopetuspvm;
    }
    public String getKatuosoite() {
        return katuosoite;
    }
    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }
    public Integer getLippujaJaljella() {
        return lippujaJaljella;
    }
    public void setLippujaJaljella(Integer lippujaJaljella) {
        this.lippujaJaljella = lippujaJaljella;
    }
    public Long getOsoiteId() {
        return osoiteId;
    }
    public void setOsoiteId(Long osoiteId) {
        this.osoiteId = osoiteId;
    }
    public List<LipputyyppiDTO> getLipputyypit() {
        return lipputyypit;
    }
    public void setLipputyypit(List<LipputyyppiDTO> lipputyypit) {
        this.lipputyypit = lipputyypit;
    }

    
    
}
