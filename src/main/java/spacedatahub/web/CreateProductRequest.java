package spacedatahub.web;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import spacedatahub.struct.Footprint;

public class CreateProductRequest {

    @NotBlank
    private String missionName;

    @NotNull
    private LocalDateTime acquisitionDate;

    @NotNull
    private Footprint footprint;

    @NotNull
    private BigDecimal price;

    @NotBlank
    private String url;

    public CreateProductRequest(String missionName, LocalDateTime acquisitionDate, Footprint footprint, BigDecimal price, String url) {
        this.missionName = missionName;
        this.acquisitionDate = acquisitionDate;
        this.footprint = footprint;
        this.price = price;
        this.url = url;
    }

    public CreateProductRequest() {
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public LocalDateTime getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(LocalDateTime acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public Footprint getFootprint() {
        return footprint;
    }

    public void setFootprint(Footprint footprint) {
        this.footprint = footprint;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
