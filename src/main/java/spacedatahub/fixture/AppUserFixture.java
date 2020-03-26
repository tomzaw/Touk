package spacedatahub.fixture;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import spacedatahub.model.AppUser;
import spacedatahub.repository.AppUserRepository;

@Component
public class AppUserFixture {

    private final AppUserRepository appUserRepository;

    public AppUserFixture(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @PostConstruct
    public void init() {

        AppUser root = new AppUser("root", "name1@foo.com", "root");
        AppUser customer = new AppUser("customer", "name2@foo.com", "customer");

        appUserRepository.save(root);
        appUserRepository.save(customer);
    }
}
