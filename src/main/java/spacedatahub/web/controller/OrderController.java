package spacedatahub.web.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spacedatahub.model.AppUser;
import spacedatahub.model.Order;
import spacedatahub.model.Product;
import spacedatahub.service.AppUserService;
import spacedatahub.service.OrderService;
import spacedatahub.service.ProductService;
import spacedatahub.web.CreateOrderRequest;
import spacedatahub.web.OrderRequest;
import spacedatahub.web.OrderResponse;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    private AppUserService appUserService;

    private ProductService productService;

    public OrderController(OrderService orderService, AppUserService appUserService, ProductService productService) {
        this.orderService = orderService;
        this.appUserService = appUserService;
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@Valid @RequestBody CreateOrderRequest cor) {

        AppUser appUser = appUserService.find(cor.getAppUserUsername());
        List<Long> productsIds = cor.getProductsIds();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productsIds.size(); i++) {

            products.add(productService.find(productsIds.get(i)));
        }
        Order order = new Order(cor.getPaymentType(), cor.getTitle(), cor.getDescription(), appUser, products);

        orderService.save(order);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public OrderResponse read(@PathVariable Long id) {

        Order order = orderService.find(id);
        List<Product> products = order.getProducts();
        List<Long> productsIds = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            productsIds.add(products.get(i).getId());
        }
        return new OrderResponse(order.getId(), order.getPaymentType(), order.getTitle(), order.getDescription(), order.getOrderDate(), order.getAppUser().getId(), productsIds);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderResponse> readCustomerOrders(@RequestParam(required = true) Long clientId) {

        List<Order> orders = orderService.findAllByAppUserId(clientId);
        List<OrderResponse> orderResponses = new ArrayList<>();
        for (Order order : orders) {

            List<Product> products = order.getProducts();
            List<Long> productsIds = new ArrayList<>();
            for (int i = 0; i < products.size(); i++) {
                productsIds.add(products.get(i).getId());
            }
            orderResponses.add(new OrderResponse(order.getId(), order.getPaymentType(), order.getTitle(), order.getDescription(), order.getOrderDate(), order.getAppUser().getId(), productsIds));
        }
        return orderResponses;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody OrderRequest or) {

        AppUser appUser = appUserService.find(or.getAppUserUsername());
        List<Long> productsIds = or.getProductsIds();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productsIds.size(); i++) {

            products.add(productService.find(productsIds.get(i)));
        }
        Order order = new Order(or.getPaymentType(), or.getTitle(), or.getDescription(), appUser, products);
        order.setId(or.getId());
        orderService.save(order);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void delete(Long id) {

        orderService.delete(id);
    }
}
