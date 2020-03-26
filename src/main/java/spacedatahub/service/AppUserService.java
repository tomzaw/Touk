package spacedatahub.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import spacedatahub.model.AppUser;
import spacedatahub.repository.AppUserRepository;

@Transactional
@Service
public class AppUserService {

    private AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> findAll() {

        List<AppUser> appUsers = appUserRepository.findAll();
        return appUsers;
    }

    public AppUser find(Long id) {

        AppUser appUser = appUserRepository.findById(id).get();
        return appUser;
    }

    public AppUser find(String username) {

        AppUser appUser = appUserRepository.findByUsername(username).get();
        return appUser;
    }

    public void save(AppUser appUser) {

        appUserRepository.save(appUser);
    }

    public void delete(Long id) {

        appUserRepository.deleteById(id);
    }

    public void delete(String username) {

        appUserRepository.deleteByUsername(username);
    }
}
