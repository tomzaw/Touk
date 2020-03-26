package spacedatahub.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import spacedatahub.converter.FootprintConverter;
import spacedatahub.struct.Footprint;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String missionName;

    @NotNull
    private LocalDateTime acquisitionDate;

    @Convert(converter = FootprintConverter.class)
    @NotNull
    private Footprint footprint;

    @NotNull
    private BigDecimal price;

    @Column(unique = true)
    @NotBlank
    private String url;

    @ManyToOne
    private Mission mission;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

    public Product(String missionName, LocalDateTime acquisitionDate, Footprint footprint, BigDecimal price, String url, Mission mission) {
        this.missionName = missionName;
        this.acquisitionDate = acquisitionDate;
        this.footprint = footprint;
        this.price = price;
        this.url = url;
        this.mission = mission;
    }

    public Product(String missionName, LocalDateTime acquisitionDate, Footprint footprint, BigDecimal price, String url, Mission mission, List<Order> order) {
        this.missionName = missionName;
        this.acquisitionDate = acquisitionDate;
        this.footprint = footprint;
        this.price = price;
        this.url = url;
        this.mission = mission;
        this.orders = orders;
    }

    public Product() {
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

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrder(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", missionName=" + missionName + ", acquisitionDate=" + acquisitionDate + ", footprint=" + footprint + ", price=" + price + ", url=" + url + ", mission=" + mission + ", orders=" + orders + '}';
    }
}
