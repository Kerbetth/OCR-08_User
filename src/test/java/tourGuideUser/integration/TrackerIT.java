package tourGuideUser.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import tourGuideUser.DataTest;
import tourGuideUser.controller.UserController;
import tourGuideUser.domain.CreateUser;
import tourGuideUser.domain.SetUserPreferences;
import tourGuideUser.domain.trackerservice.Attraction;
import tourGuideUser.domain.trackerservice.Location;
import tourGuideUser.domain.trackerservice.VisitedLocation;
import tourGuideUser.domain.userservice.UserReward;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TrackerIT {

    @Autowired
    UserController userController;

    private DataTest dataTest = new DataTest();

    @Test
    public void setUserPreferences() {
        SetUserPreferences userPreferences = new SetUserPreferences(
                20, "USD", 0, 1000, 2, 2, 2, 1
        );
        userController.setUserPreferences("internalUser1", userPreferences);
    }

    @Test
    public void addUser() {
        CreateUser createUser = new CreateUser("internalUser1b");
        userController.addUser(createUser);
        UUID user = userController.getUserId("internalUser1b");
        assertThat(user).isNotNull();
    }

    @Test
    public void addUserLocation() {
        VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(1.0, 2.0), new Date());
        userController.addUserLocation("internalUser1", visitedLocation);
        VisitedLocation visitedLocation1 = userController.getAllVisitedLocations("internalUser1").get(4);
        assertThat(visitedLocation1.location.latitude).isEqualTo(1.0);
        assertThat(visitedLocation1.location.longitude).isEqualTo(2.0);
    }

    @Test
    public void addUserReward() {
        Attraction attraction = new Attraction("attraction", "city", "state", 1.0, 2.0);
        UserReward userReward = new UserReward(
                new VisitedLocation(UUID.randomUUID(),
                        new Location(1.0, 2.0),
                        new Date()),
                attraction);
        userController.addUserReward("internalUser1", userReward);
        String attractionId = userController.getAttractionIds(userController.getAllUsersID().get(0).toString()).get(4);
        assertThat(attractionId).isEqualTo(attraction.attractionId);
    }

    @Test
    public void getUserLocation() {
        Location location = userController.getUserLocation("internalUser1");
        assertThat(location.longitude).isBetween(-180D, 180D);
        assertThat(location.latitude).isBetween(-86D, 86D);
    }

    @Test
    public void getAllUsersID() {
        List<UUID> uuids = userController.getAllUsersID();
        assertThat(uuids).hasSize(100);
    }

    @Test
    public void getAllVisitedLocations() {
        List<UUID> uuids = userController.getAllUsersID();
        assertThat(uuids).hasSize(100);
    }

    @Test
    public void getTripPricerTask() {
        List<UUID> uuids = userController.getAllUsersID();
        assertThat(uuids).hasSize(100);
    }

    @Test
    public void getUserRewards(){
        List<String> userRewards = userController.getAttractionIds("internalUser1");
        assertThat(userRewards).hasSize(0);
    }

    @Test
    public void getCumulateRewardPoints(){
        int userRewards = userController.getCumulateRewardPoints("internalUser1");
        assertThat(userRewards).isEqualTo(0);
    }
}
