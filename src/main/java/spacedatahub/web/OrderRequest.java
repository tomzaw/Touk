package spacedatahub.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrderRequest {

    @NotBlank
    private Long id;

    @NotBlank
    private String paymentType;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private LocalDateTime orderDate;

    @NotBlank
    private String appUserUsername;

    @NotNull
    private List<Long> productsIds = new ArrayList<>();

    public OrderRequest(Long id, String paymentType, String title, String description, LocalDateTime orderDate, String appUserUsername) {
        this.id = id;
        this.paymentType = paymentType;
        this.title = title;
        this.description = description;
        this.orderDate = orderDate;
        this.appUserUsername = appUserUsername;
    }

    public OrderRequest(String paymentType, String title, String description, LocalDateTime orderDate, String appUserUsername) {
        this.paymentType = paymentType;
        this.title = title;
        this.description = description;
        this.orderDate = orderDate;
        this.appUserUsername = appUserUsername;
    }

    public OrderRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getAppUserUsername() {
        return appUserUsername;
    }

    public void setAppUserUsername(String appUserUsername) {
        this.appUserUsername = appUserUsername;
    }

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProductsIds(List<Long> productsIds) {
        this.productsIds = productsIds;
    }
}
