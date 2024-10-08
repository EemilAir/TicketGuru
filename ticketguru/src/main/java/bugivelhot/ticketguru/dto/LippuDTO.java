package bugivelhot.ticketguru.dto;

public class LippuDTO {

    private Long tapahtumaId; // tapahtuma
    private Long lipputyyppiId; // lipputyyppi
    private int maara; // lippujen määrä

    // getterit ja setterit
    public Long getTapahtumaId() {
        return tapahtumaId;
    }

    public void setTapahtumaId(Long tapahtumaId) {
        this.tapahtumaId = tapahtumaId;
    }

    public Long getLipputyyppiId() {
        return lipputyyppiId;
    }

    public void setLipputyyppiId(Long lipputyyppiId) {
        this.lipputyyppiId = lipputyyppiId;
    }

    public int getMaara() {
        return maara;
    }

    public void setMaara(int maara) {
        this.maara = maara;
    }

    

    
}
