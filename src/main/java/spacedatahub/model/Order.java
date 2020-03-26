package spacedatahub.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String paymentType;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private LocalDateTime orderDate;

    @ManyToOne
    private AppUser appUser;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_product",
            joinColumns = {
                @JoinColumn(name = "order_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "product_id")}
    )
    private List<Product> products = new ArrayList<>();

    public Order(String paymentType, String title, String description, AppUser appUser) {
        this.paymentType = paymentType;
        this.title = title;
        this.description = description;
        this.orderDate = LocalDateTime.now();
        this.appUser = appUser;
    }

    public Order(String paymentType, String title, String description, AppUser appUser, List<Product> products) {
        this.paymentType = paymentType;
        this.title = title;
        this.description = description;
        this.appUser = appUser;
        this.products = products;
        this.orderDate = LocalDateTime.now();
    }

    public Order() {
        this.orderDate = LocalDateTime.now();
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

    public AppUser getAppUser() {
        return appUser;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", paymentType=" + paymentType + ", title=" + title + ", description=" + description + ", orderDate=" + orderDate + ", appUser=" + appUser + ", products=" + products + '}';
    }
}
