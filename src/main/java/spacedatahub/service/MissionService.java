package spacedatahub.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import spacedatahub.model.Mission;
import spacedatahub.repository.MissionRepository;

@Transactional
@Service
public class MissionService {

    private MissionRepository missionRepository;

    public MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public List<Mission> findAll() {

        List<Mission> missions = missionRepository.findAll();
        return missions;
    }

    public Mission find(Long id) {

        Mission mission = missionRepository.findById(id).get();
        return mission;
    }

    public Mission find(String name) {

        Mission mission = missionRepository.findByName(name).get();
        return mission;
    }

    public void save(Mission mission) {

        missionRepository.save(mission);
    }

    public void delete(Long id) {

        missionRepository.deleteById(id);
    }

    public void delete(String name) {

        missionRepository.deleteByName(name);
    }
}
