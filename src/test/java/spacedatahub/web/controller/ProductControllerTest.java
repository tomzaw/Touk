package spacedatahub.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
import spacedatahub.struct.Footprint;
import spacedatahub.struct.ImageryType;
import spacedatahub.web.CreateProductRequest;
import spacedatahub.web.ProductResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import spacedatahub.model.Mission;
import spacedatahub.model.Product;
import spacedatahub.service.MissionService;
import spacedatahub.service.ProductService;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @MockBean
    private OrderService orderService;

    @MockBean
    private MissionService missionService;

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @WithMockUser(username = "root", password = "root", roles = "root")
    @Test
    public void shouldCreate() throws Exception {

        //Given.
        CreateProductRequest cpr = new CreateProductRequest("mission1", LocalDateTime.now(), new Footprint(0d, 1d, 0d, 1d), BigDecimal.TEN, "http://localhost:8080/testUrl");
        String json = objectMapper.writeValueAsString(cpr);

        //When.
        mockMvc.perform(post("/product").contentType(MediaType.APPLICATION_JSON).content(json))
                //Then.
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void shouldReadById() throws Exception {

        //Given.
        Mission mission = new Mission("mission1", ImageryType.PANCHROMATIC, LocalDateTime.parse("2020-02-02T00:00:00"), LocalDateTime.parse("2021-02-02T00:00:00"));
        mission.setId(1L);
        Product product = new Product("mission1", LocalDateTime.now(), new Footprint(0d, 1d, 0d, 1d), BigDecimal.TEN, "http://localhost:8080/testUrl", mission);
        product.setId(1L);

        //When.
        when(productService.find(1L)).thenReturn(product);
        MvcResult mvcResult = mockMvc.perform(get("/product/1"))
                //Then.
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        ProductResponse productResponse = objectMapper.readValue(responseJson, ProductResponse.class);

        assertEquals(product.getId(), productResponse.getId());
        assertEquals(product.getMissionName(), productResponse.getMissionName());
        assertEquals(product.getAcquisitionDate(), productResponse.getAcquisitionDate());
        assertEquals(product.getFootprint().getX1(), productResponse.getFootprint().getX1());
        assertEquals(product.getFootprint().getX2(), productResponse.getFootprint().getX2());
        assertEquals(product.getFootprint().getY1(), productResponse.getFootprint().getY1());
        assertEquals(product.getFootprint().getY2(), productResponse.getFootprint().getY2());
        assertEquals(product.getPrice(), productResponse.getPrice());
        assertEquals(product.getUrl(), productResponse.getUrl());
    }

    @Test
    public void shouldReadAllByMissionName() throws Exception {

        //Given.
        Mission mission = new Mission("mission1", ImageryType.PANCHROMATIC, LocalDateTime.parse("2020-02-02T00:00:00"), LocalDateTime.parse("2021-02-02T00:00:00"));
        mission.setId(1L);
        Product product = new Product("mission1", LocalDateTime.now(), new Footprint(0d, 1d, 0d, 1d), BigDecimal.TEN, "http://localhost:8080/testUrl", mission);
        product.setId(1L);

        List<Product> products = new ArrayList<>();
        products.add(product);

        //When.
        when(productService.findAllByMissionName("mission1")).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(get("/product?missionName=mission1"))
                //Then.
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        ProductResponse[] productResponses = objectMapper.readValue(responseJson, ProductResponse[].class);
        for (int i = 0; i < productResponses.length; i++) {

            assertEquals(products.get(i).getId(), productResponses[i].getId());
            assertEquals(products.get(i).getMissionName(), productResponses[i].getMissionName());
            assertEquals(products.get(i).getAcquisitionDate(), productResponses[i].getAcquisitionDate());
            assertEquals(products.get(i).getFootprint().getX1(), productResponses[i].getFootprint().getX1());
            assertEquals(products.get(i).getFootprint().getX2(), productResponses[i].getFootprint().getX2());
            assertEquals(products.get(i).getFootprint().getY1(), productResponses[i].getFootprint().getY1());
            assertEquals(products.get(i).getFootprint().getY2(), productResponses[i].getFootprint().getY2());
            assertEquals(products.get(i).getPrice(), productResponses[i].getPrice());
            assertEquals(products.get(i).getUrl(), productResponses[i].getUrl());
        }
    }

    @Test
    public void shouldReadAllByImageryType() throws Exception {

        //Given.
        Mission mission = new Mission("mission1", ImageryType.PANCHROMATIC, LocalDateTime.parse("2020-02-02T00:00:00"), LocalDateTime.parse("2021-02-02T00:00:00"));
        mission.setId(1L);
        Product product = new Product("mission1", LocalDateTime.now(), new Footprint(0d, 1d, 0d, 1d), BigDecimal.TEN, "http://localhost:8080/testUrl", mission);
        product.setId(1L);

        List<Product> products = new ArrayList<>();
        products.add(product);

        //When.
        when(productService.findAllByMissionImageryType(ImageryType.PANCHROMATIC)).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(get("/product/missionImageryType?imageryType=PANCHROMATIC"))
                //Then.
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        System.out.println("Test " + responseJson);
        ProductResponse[] productResponses = objectMapper.readValue(responseJson, ProductResponse[].class);
        for (int i = 0; i < productResponses.length; i++) {

            assertEquals(products.get(i).getId(), productResponses[i].getId());
            assertEquals(products.get(i).getMissionName(), productResponses[i].getMissionName());
            assertEquals(products.get(i).getAcquisitionDate(), productResponses[i].getAcquisitionDate());
            assertEquals(products.get(i).getFootprint().getX1(), productResponses[i].getFootprint().getX1());
            assertEquals(products.get(i).getFootprint().getX2(), productResponses[i].getFootprint().getX2());
            assertEquals(products.get(i).getFootprint().getY1(), productResponses[i].getFootprint().getY1());
            assertEquals(products.get(i).getFootprint().getY2(), productResponses[i].getFootprint().getY2());
            assertEquals(products.get(i).getPrice(), productResponses[i].getPrice());
            assertEquals(products.get(i).getUrl(), productResponses[i].getUrl());
        }
    }

    @Test
    public void shouldReadAllByAcquisitionDateBefore() throws Exception {

        //Given.
        Mission mission = new Mission("mission1", ImageryType.PANCHROMATIC, LocalDateTime.parse("2020-02-02T00:00:00"), LocalDateTime.parse("2021-02-02T00:00:00"));
        mission.setId(1L);
        Product product = new Product("mission1", LocalDateTime.now(), new Footprint(0d, 1d, 0d, 1d), BigDecimal.TEN, "http://localhost:8080/testUrl", mission);
        product.setId(1L);

        List<Product> products = new ArrayList<>();
        products.add(product);

        //When.
        when(productService.findAllByAcquisitionDateBefore(LocalDateTime.parse("2021-02-02T00:00:00"))).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(get("/product/acquisitionDateBefore?datetime=2021-02-02T00:00:00"))
                //Then.
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        ProductResponse[] productResponses = objectMapper.readValue(responseJson, ProductResponse[].class);
        for (int i = 0; i < productResponses.length; i++) {

            assertEquals(products.get(i).getId(), productResponses[i].getId());
            assertEquals(products.get(i).getMissionName(), productResponses[i].getMissionName());
            assertEquals(products.get(i).getAcquisitionDate(), productResponses[i].getAcquisitionDate());
            assertEquals(products.get(i).getFootprint().getX1(), productResponses[i].getFootprint().getX1());
            assertEquals(products.get(i).getFootprint().getX2(), productResponses[i].getFootprint().getX2());
            assertEquals(products.get(i).getFootprint().getY1(), productResponses[i].getFootprint().getY1());
            assertEquals(products.get(i).getFootprint().getY2(), productResponses[i].getFootprint().getY2());
            assertEquals(products.get(i).getPrice(), productResponses[i].getPrice());
            assertEquals(products.get(i).getUrl(), productResponses[i].getUrl());
        }
    }

    @Test
    public void shouldReadAllByAcquisitionDateAfter() throws Exception {

        //Given.
        Mission mission = new Mission("mission1", ImageryType.PANCHROMATIC, LocalDateTime.parse("2020-02-02T00:00:00"), LocalDateTime.parse("2021-02-02T00:00:00"));
        mission.setId(1L);
        Product product = new Product("mission1", LocalDateTime.now(), new Footprint(0d, 1d, 0d, 1d), BigDecimal.TEN, "http://localhost:8080/testUrl", mission);
        product.setId(1L);

        List<Product> products = new ArrayList<>();
        products.add(product);

        //When.
        when(productService.findAllByAcquisitionDateAfter(LocalDateTime.parse("2019-02-02T00:00:00"))).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(get("/product/acquisitionDateBefore?datetime=2019-02-02T00:00:00"))
                //Then.
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        ProductResponse[] productResponses = objectMapper.readValue(responseJson, ProductResponse[].class);
        for (int i = 0; i < productResponses.length; i++) {

            assertEquals(products.get(i).getId(), productResponses[i].getId());
            assertEquals(products.get(i).getMissionName(), productResponses[i].getMissionName());
            assertEquals(products.get(i).getAcquisitionDate(), productResponses[i].getAcquisitionDate());
            assertEquals(products.get(i).getFootprint().getX1(), productResponses[i].getFootprint().getX1());
            assertEquals(products.get(i).getFootprint().getX2(), productResponses[i].getFootprint().getX2());
            assertEquals(products.get(i).getFootprint().getY1(), productResponses[i].getFootprint().getY1());
            assertEquals(products.get(i).getFootprint().getY2(), productResponses[i].getFootprint().getY2());
            assertEquals(products.get(i).getPrice(), productResponses[i].getPrice());
            assertEquals(products.get(i).getUrl(), productResponses[i].getUrl());
        }
    }

    @Test
    public void shouldReadAllByAcquisitionDateBetween() throws Exception {

        //Given.
        Mission mission = new Mission("mission1", ImageryType.PANCHROMATIC, LocalDateTime.parse("2020-02-02T00:00:00"), LocalDateTime.parse("2021-02-02T00:00:00"));
        mission.setId(1L);
        Product product = new Product("mission1", LocalDateTime.now(), new Footprint(0d, 1d, 0d, 1d), BigDecimal.TEN, "http://localhost:8080/testUrl", mission);
        product.setId(1L);

        List<Product> products = new ArrayList<>();
        products.add(product);

        //When.
        when(productService.findAllByAcquisitionDateBetween(LocalDateTime.parse("2019-02-02T00:00:00"), LocalDateTime.parse("2014-02-02T00:00:00"))).thenReturn(products);
        MvcResult mvcResult = mockMvc.perform(get("/product/acquisitionDateBetween?datetime1=2019-02-02T00:00:00&datetime2=2024-02-02T00:00:00"))
                //Then.
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        ProductResponse[] productResponses = objectMapper.readValue(responseJson, ProductResponse[].class);
        for (int i = 0; i < productResponses.length; i++) {

            assertEquals(products.get(i).getId(), productResponses[i].getId());
            assertEquals(products.get(i).getMissionName(), productResponses[i].getMissionName());
            assertEquals(products.get(i).getAcquisitionDate(), productResponses[i].getAcquisitionDate());
            assertEquals(products.get(i).getFootprint().getX1(), productResponses[i].getFootprint().getX1());
            assertEquals(products.get(i).getFootprint().getX2(), productResponses[i].getFootprint().getX2());
            assertEquals(products.get(i).getFootprint().getY1(), productResponses[i].getFootprint().getY1());
            assertEquals(products.get(i).getFootprint().getY2(), productResponses[i].getFootprint().getY2());
            assertEquals(products.get(i).getPrice(), productResponses[i].getPrice());
            assertEquals(products.get(i).getUrl(), productResponses[i].getUrl());
        }
    }
}
