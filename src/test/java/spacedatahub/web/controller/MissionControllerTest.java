package spacedatahub.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import spacedatahub.service.MissionService;
import spacedatahub.struct.ImageryType;
import spacedatahub.web.CreateMissionRequest;
import spacedatahub.web.MissionResponse;
import spacedatahub.model.Mission;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import spacedatahub.service.ProductService;

@WebMvcTest(controllers = MissionController.class)
public class MissionControllerTest {

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
        CreateMissionRequest cmr = new CreateMissionRequest("mission1", ImageryType.PANCHROMATIC, LocalDateTime.parse("2020-02-02T00:00:00"), LocalDateTime.parse("2021-02-02T00:00:00"));
        String json = objectMapper.writeValueAsString(cmr);

        //When.
        mockMvc.perform(post("/mission").contentType(MediaType.APPLICATION_JSON).content(json))
                //Then.
                .andExpect(status().is2xxSuccessful());
    }

    @WithMockUser(username = "root", password = "root", roles = "root")
    @Test
    public void shouldReadById() throws Exception {

        //Given.
        Mission mission = new Mission("mission1", ImageryType.PANCHROMATIC, LocalDateTime.parse("2020-02-02T00:00:00"), LocalDateTime.parse("2021-02-02T00:00:00"));
        mission.setId(1L);

        //When.
        when(missionService.find(1L)).thenReturn(mission);
        MvcResult mvcResult = mockMvc.perform(get("/mission/1"))
                //Then.
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        MissionResponse missionResponse = objectMapper.readValue(responseJson, MissionResponse.class);

        assertEquals(mission.getId(), missionResponse.getId());
        assertEquals(mission.getImageryType(), missionResponse.getImageryType());
        assertEquals(mission.getStartDate(), missionResponse.getStartDate());
        assertEquals(mission.getFinishDate(), missionResponse.getFinishDate());
    }
}
