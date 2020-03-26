package spacedatahub.web.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
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
import spacedatahub.model.Mission;
import spacedatahub.model.Order;
import spacedatahub.model.Product;
import spacedatahub.service.MissionService;
import spacedatahub.service.ProductService;
import spacedatahub.struct.ImageryType;
import spacedatahub.web.CreateProductRequest;
import spacedatahub.web.ProductRequest;
import spacedatahub.web.ProductResponse;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    private MissionService missionService;

    public ProductController(ProductService productService, MissionService missionService) {
        this.productService = productService;
        this.missionService = missionService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@Valid @RequestBody CreateProductRequest cpr) {

        Mission mission = missionService.find(cpr.getMissionName());
        Product product = new Product(cpr.getMissionName(), cpr.getAcquisitionDate(), cpr.getFootprint(), cpr.getPrice(), cpr.getUrl(), mission);
        productService.save(product);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse read(@PathVariable Long id) {

        Product product = productService.find(id);
        List<Order> orders = product.getOrders();
        List<Long> ordersIds = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            ordersIds.add(orders.get(i).getId());
        }
        return new ProductResponse(product.getId(), product.getMissionName(), product.getAcquisitionDate(), product.getFootprint(), product.getPrice(), product.getUrl(), product.getMission().getId(), ordersIds);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> readAllByMissionName(@RequestParam(required = true) String missionName) {

        List<Product> products = productService.findAllByMissionName(missionName);
        List<ProductResponse> productsResponses = new ArrayList<>();
        for (Product product : products) {

            List<Order> orders = product.getOrders();
            List<Long> ordersIds = new ArrayList<>();
            for (int i = 0; i < orders.size(); i++) {
                ordersIds.add(orders.get(i).getId());
            }
            productsResponses.add(new ProductResponse(product.getId(), product.getMissionName(), product.getAcquisitionDate(), product.getFootprint(), product.getPrice(), product.getUrl(), product.getMission().getId(), ordersIds));
        }
        return productsResponses;
    }

    @GetMapping(value = "/missionImageryType", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> readAllByImageryType(@RequestParam(required = true) ImageryType imageryType) {

        List<Product> products = productService.findAllByMissionImageryType(imageryType);
        List<ProductResponse> productsResponses = new ArrayList<>();
        for (Product product : products) {

            List<Order> orders = product.getOrders();
            List<Long> ordersIds = new ArrayList<>();
            for (int i = 0; i < orders.size(); i++) {
                ordersIds.add(orders.get(i).getId());
            }
            productsResponses.add(new ProductResponse(product.getId(), product.getMissionName(), product.getAcquisitionDate(), product.getFootprint(), product.getPrice(), product.getUrl(), product.getMission().getId(), ordersIds));
        }
        return productsResponses;
    }

    @GetMapping(value = "/acquisitionDateBefore", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> readAllByAcquisitionDateBefore(@RequestParam(required = true) LocalDateTime datetime) {

        List<Product> products = productService.findAllByAcquisitionDateBefore(datetime);
        List<ProductResponse> productsResponses = new ArrayList<>();
        for (Product product : products) {

            List<Order> orders = product.getOrders();
            List<Long> ordersIds = new ArrayList<>();
            for (int i = 0; i < orders.size(); i++) {
                ordersIds.add(orders.get(i).getId());
            }
            productsResponses.add(new ProductResponse(product.getId(), product.getMissionName(), product.getAcquisitionDate(), product.getFootprint(), product.getPrice(), product.getUrl(), product.getMission().getId(), ordersIds));
        }
        return productsResponses;
    }

    @GetMapping(value = "/acquisitionDateAfter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> readAllByAcquisitionDateAfter(@RequestParam(required = true) LocalDateTime datetime) {

        List<Product> products = productService.findAllByAcquisitionDateAfter(datetime);
        List<ProductResponse> productsResponses = new ArrayList<>();
        for (Product product : products) {

            List<Order> orders = product.getOrders();
            List<Long> ordersIds = new ArrayList<>();
            for (int i = 0; i < orders.size(); i++) {
                ordersIds.add(orders.get(i).getId());
            }
            productsResponses.add(new ProductResponse(product.getId(), product.getMissionName(), product.getAcquisitionDate(), product.getFootprint(), product.getPrice(), product.getUrl(), product.getMission().getId(), ordersIds));
        }
        return productsResponses;
    }

    @GetMapping(value = "/acquisitionDateBetween", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> readAllByAcquisitionDateBetween(@RequestParam(required = true) LocalDateTime datetime1, LocalDateTime datetime2) {

        List<Product> products = productService.findAllByAcquisitionDateBetween(datetime1, datetime2);
        List<ProductResponse> productsResponses = new ArrayList<>();
        for (Product product : products) {

            List<Order> orders = product.getOrders();
            List<Long> ordersIds = new ArrayList<>();
            for (int i = 0; i < orders.size(); i++) {
                ordersIds.add(orders.get(i).getId());
            }
            productsResponses.add(new ProductResponse(product.getId(), product.getMissionName(), product.getAcquisitionDate(), product.getFootprint(), product.getPrice(), product.getUrl(), product.getMission().getId(), ordersIds));
        }
        return productsResponses;
    }

    //Return most ordered products where count is the number of products to get(starting from most to least ordered).
    @GetMapping(value = "/getMostOrdered", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> readAllMostOrdered(@RequestParam(required = true, defaultValue = "1") Long count) {

        List<BigInteger> productsIds = productService.findMostOrderedIds(count);
        List<Product> products = new ArrayList<>();
        for (BigInteger productId : productsIds) {
            products.add(productService.find(productId.longValue()));
        }

        List<ProductResponse> productsResponses = new ArrayList<>();
        for (Product product : products) {

            List<Order> orders = product.getOrders();
            List<Long> ordersIds = new ArrayList<>();
            for (int i = 0; i < orders.size(); i++) {
                ordersIds.add(orders.get(i).getId());
            }
            productsResponses.add(new ProductResponse(product.getId(), product.getMissionName(), product.getAcquisitionDate(), product.getFootprint(), product.getPrice(), product.getUrl(), product.getMission().getId(), ordersIds));
        }
        return productsResponses;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody ProductRequest pr) {

        Mission mission = missionService.find(pr.getMissionName());
        Product product = new Product(pr.getMissionName(), pr.getAcquisitionDate(), pr.getFootprint(), pr.getPrice(), pr.getUrl(), mission);
        product.setId(pr.getId());
        productService.save(product);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void delete(Long id) {

        productService.delete(id);
    }
}
