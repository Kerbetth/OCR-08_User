package tourGuideUser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tourGuideUser.domain.User;

import java.util.UUID;

@Repository

public interface ServiceTrackerProxy {

    @GetMapping(value = "/Rewards/{userId}")
    Integer getRewards(@PathVariable("userId") UUID userId, @RequestParam UUID attractionID);

    void calculateRewards(User user);

}