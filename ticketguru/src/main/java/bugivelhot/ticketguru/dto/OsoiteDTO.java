package bugivelhot.ticketguru.dto;

public class OsoiteDTO {

    private Long osoiteId;
    private String postinumero;
    private String postitmp;

    // konstruktorit
    public OsoiteDTO() {
    }

    public OsoiteDTO(Long osoiteId, String postinumero, String postitmp) {
        this.osoiteId = osoiteId;
        this.postinumero = postinumero;
        this.postitmp = postitmp;
    }

    public Long getOsoiteId() {
        return osoiteId;
    }

    public void setOsoiteId(Long osoiteId) {
        this.osoiteId = osoiteId;
    }

    public String getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(String postinumero) {
        this.postinumero = postinumero;
    }

    public String getPostitmp() {
        return postitmp;
    }

    public void setPostitmp(String postitmp) {
        this.postitmp = postitmp;
    }

    
}
