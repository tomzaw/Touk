package spacedatahub.web;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateOrderRequest {

    @NotBlank
    private String paymentType;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String appUserUsername;

    @NotNull
    private List<Long> productsIds = new ArrayList<>();

    public CreateOrderRequest(String paymentType, String title, String description, String appUserUsername, List<Long> productsIds) {
        this.paymentType = paymentType;
        this.title = title;
        this.description = description;
        this.appUserUsername = appUserUsername;
        this.productsIds = productsIds;
    }

    public CreateOrderRequest(String paymentType, String title, String description, String appUserUsername) {
        this.paymentType = paymentType;
        this.title = title;
        this.description = description;
        this.appUserUsername = appUserUsername;
    }

    public CreateOrderRequest() {
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
