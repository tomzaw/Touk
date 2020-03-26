package spacedatahub.web;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {

    private Long id;

    private String paymentType;

    private String title;

    private String description;

    private LocalDateTime orderDate;

    private Long appUserId;

    private List<Long> productsIds = new ArrayList<>();

    public OrderResponse(Long id, String paymentType, String title, String description, LocalDateTime localDateTime, Long appUserId) {
        this.id = id;
        this.paymentType = paymentType;
        this.title = title;
        this.description = description;
        this.orderDate = localDateTime;
        this.appUserId = appUserId;
    }

    public OrderResponse(Long id, String paymentType, String title, String description, LocalDateTime localDateTime, Long appUserId, List<Long> productsIds) {
        this.id = id;
        this.paymentType = paymentType;
        this.title = title;
        this.description = description;
        this.appUserId = appUserId;
        this.productsIds = productsIds;
        this.orderDate = localDateTime;
    }

    public OrderResponse(String paymentType, String title, String description, LocalDateTime localDateTime, Long appUserId) {
        this.paymentType = paymentType;
        this.title = title;
        this.description = description;
        this.orderDate = localDateTime;
        this.appUserId = appUserId;
    }

    public OrderResponse(String paymentType, String title, String description, LocalDateTime localDateTime, Long appUserId, List<Long> productsIds) {
        this.paymentType = paymentType;
        this.title = title;
        this.description = description;
        this.appUserId = appUserId;
        this.productsIds = productsIds;
        this.orderDate = localDateTime;
    }

    public OrderResponse() {
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

    public Long getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(Long appUserId) {
        this.appUserId = appUserId;
    }

    public List<Long> getProductsIds() {
        return productsIds;
    }

    public void setProducts(List<Long> productsIds) {
        this.productsIds = productsIds;
    }
}
