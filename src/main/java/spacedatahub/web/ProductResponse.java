package spacedatahub.web;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import spacedatahub.struct.Footprint;

public class ProductResponse {

    private Long id;

    private String missionName;

    private LocalDateTime acquisitionDate;

    private Footprint footprint;

    private BigDecimal price;

    private String url;

    private Long missionId;

    private List<Long> ordersIds = new ArrayList<>();

    public ProductResponse(Long id, String missionName, LocalDateTime acquisitionDate, Footprint footprint, BigDecimal price, String url, Long missionId) {
        this.id = id;
        this.missionName = missionName;
        this.acquisitionDate = acquisitionDate;
        this.footprint = footprint;
        this.price = price;
        this.url = url;
        this.missionId = missionId;
    }

    public ProductResponse(Long id, String missionName, LocalDateTime acquisitionDate, Footprint footprint, BigDecimal price, String url, Long missionId, List<Long> ordersIds) {
        this.id = id;
        this.missionName = missionName;
        this.acquisitionDate = acquisitionDate;
        this.footprint = footprint;
        this.price = price;
        this.url = url;
        this.missionId = missionId;
        this.ordersIds = ordersIds;
    }

    public ProductResponse(String missionName, LocalDateTime acquisitionDate, Footprint footprint, BigDecimal price, String url, Long missionId) {
        this.missionName = missionName;
        this.acquisitionDate = acquisitionDate;
        this.footprint = footprint;
        this.price = price;
        this.url = url;
        this.missionId = missionId;
    }

    public ProductResponse(String missionName, LocalDateTime acquisitionDate, Footprint footprint, BigDecimal price, String url, Long missionId, List<Long> ordersIds) {
        this.missionName = missionName;
        this.acquisitionDate = acquisitionDate;
        this.footprint = footprint;
        this.price = price;
        this.url = url;
        this.missionId = missionId;
        this.ordersIds = ordersIds;
    }

    public ProductResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getMissionId() {
        return missionId;
    }

    public void setMissioIdn(Long mission) {
        this.missionId = missionId;
    }

    public List<Long> getOrdersIds() {
        return ordersIds;
    }

    public void setOrders(List<Long> orders) {
        this.ordersIds = ordersIds;
    }
}
