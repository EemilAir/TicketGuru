package bugivelhot.ticketguru.dto;

import java.util.List;

public class TapahtumaResponseDTO {

    private String nimi;
    private String kuvaus;
    private String kategoria;
    private String aloituspvm;
    private String lopetuspvm;
    private String katuosoite;
    private OsoiteDTO osoite;
    private Integer lippujaJaljella;
    private List<TapahtumanLipputyyppiDTO> lipputyypit;

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
    public String getAloituspvm() {
        return aloituspvm;
    }
    public void setAloituspvm(String aloituspvm) {
        this.aloituspvm = aloituspvm;
    }
    public String getLopetuspvm() {
        return lopetuspvm;
    }
    public void setLopetuspvm(String lopetuspvm) {
        this.lopetuspvm = lopetuspvm;
    }
    public String getKatuosoite() {
        return katuosoite;
    }
    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }
    public OsoiteDTO getOsoite() {
        return osoite;
    }
    public void setOsoite(OsoiteDTO osoite) {
        this.osoite = osoite;
    }
    public Integer getLippujaJaljella() {
        return lippujaJaljella;
    }
    public void setLippujaJaljella(Integer lippujaJaljella) {
        this.lippujaJaljella = lippujaJaljella;
    }
    public List<TapahtumanLipputyyppiDTO> getLipputyypit() {
        return lipputyypit;
    }
    public void setLipputyypit(List<TapahtumanLipputyyppiDTO> lipputyypit) {
        this.lipputyypit = lipputyypit;
    }

}
