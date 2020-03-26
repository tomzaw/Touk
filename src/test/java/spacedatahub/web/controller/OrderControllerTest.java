package spacedatahub.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import spacedatahub.service.OrderService;
import spacedatahub.web.CreateOrderRequest;
import spacedatahub.web.OrderResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import spacedatahub.model.AppUser;
import spacedatahub.model.Order;
import spacedatahub.model.Product;
import spacedatahub.service.AppUserService;
import spacedatahub.service.ProductService;

@WebMvcTest(controllers = OrderController.class)
public class OrderControllerTest {

    @MockBean
    private OrderService orderService;

    @MockBean
    private AppUserService appUserService;

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreate() throws Exception {

        //Given.
        List<Long> productsIds = new ArrayList<>();
        productsIds.add(1L);
        CreateOrderRequest cor = new CreateOrderRequest("card", "title1", "description1", "customer", productsIds);
        String json = objectMapper.writeValueAsString(cor);

        //When.
        mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(json))
                //Then.
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReadById() throws Exception {

        //Given.
        List<Product> productsIds = new ArrayList<>();
        AppUser appUser = new AppUser();
        appUser.setId(1L);
        Order order = new Order("card", "title1", "description1", appUser, productsIds);
        order.setId(1L);
        order.setProducts(productsIds);

        //When.
        when(orderService.find(1L)).thenReturn(order);
        MvcResult mvcResult = mockMvc.perform(get("/order/1"))
                //Then.
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        OrderResponse orderResponse = objectMapper.readValue(responseJson, OrderResponse.class);

        assertEquals(order.getId(), orderResponse.getId());
        assertEquals(order.getPaymentType(), orderResponse.getPaymentType());
        assertEquals(order.getDescription(), orderResponse.getDescription());
        assertEquals(order.getDescription(), orderResponse.getDescription());
        assertEquals(order.getAppUser().getId(), orderResponse.getAppUserId());
    }
}
