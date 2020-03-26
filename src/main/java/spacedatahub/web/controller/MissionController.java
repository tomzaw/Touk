package spacedatahub.web.controller;

import java.math.BigInteger;
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
import spacedatahub.web.CreateMissionRequest;
import spacedatahub.web.MissionRequest;
import spacedatahub.web.MissionResponse;
import spacedatahub.web.ProductResponse;

@RestController
@RequestMapping("/mission")
public class MissionController {

    private MissionService missionService;

    private ProductService productService;

    public MissionController(MissionService missionService, ProductService productService) {
        this.missionService = missionService;
        this.productService = productService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@Valid @RequestBody CreateMissionRequest cmr) {

        Mission mission = new Mission(cmr.getMissionName(), cmr.getImageryType(), cmr.getStartDate(), cmr.getFinishDate());
        missionService.save(mission);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MissionResponse read(@PathVariable Long id) {

        Mission mission = missionService.find(id);
        return new MissionResponse(mission.getId(), mission.getName(), mission.getImageryType(), mission.getStartDate(), mission.getFinishDate());
    }

    //Return most ordered missions where count is the number of missions to get(starting from most to least ordered).
    @GetMapping(value = "/getMostOrdered", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MissionResponse> readAllMostOrderedMissions(@RequestParam(required = true, defaultValue = "1") Long count) {

        List<String> missionNames = productService.findMostOrderedMissionNames(count);
        List<Mission> missions = new ArrayList<>();
        for (String missionName : missionNames) {
            missions.add(missionService.find(missionName));
        }

        List<MissionResponse> missionResponses = new ArrayList<>();
        for (Mission mission : missions) {
            missionResponses.add(new MissionResponse(mission.getId(), mission.getName(), mission.getImageryType(), mission.getStartDate(), mission.getFinishDate()));
        }
        return missionResponses;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody MissionRequest mr) {

        Mission mission = new Mission(mr.getMissionName(), mr.getImageryType(), mr.getStartDate(), mr.getFinishDate());
        mission.setId(mr.getId());
        missionService.save(mission);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void delete(String missionName) {

        missionService.delete(missionName);
    }
}
