package tourGuideUser.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import tourGuideUser.domain.userservice.User;
import tourGuideUser.domain.userservice.UserReward;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TrackerIT {

    @Autowired
    UserController userController;

    private DataTest dataTest = new DataTest();

    @Test
    public void setUserPreferences() throws Exception {
        SetUserPreferences userPreferences = new SetUserPreferences(
                20, "USD", 0, 1000, 2, 2, 2, 1
        );
        userController.setUserPreferences("testUser1", userPreferences);
    }

    @Test
    public void addUser() throws Exception {
        CreateUser createUser = new CreateUser("newUser");
        userController.addUser(createUser);
        User user =userController.getUser("newUser");
        assertThat(user.getUserName()).isEqualTo("newUser");
    }

    @Test
    public void addUserLocation() throws Exception {
        VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(1.0, 2.0), new Date());
        ObjectMapper postMapper = new ObjectMapper();
        String requestBody = null;

    }

    @Test
    public void addUserReward() throws Exception {
        UserReward userReward = new UserReward(
                new VisitedLocation(UUID.randomUUID(),
                        new Location(1.0, 2.0),
                        new Date()),
                new Attraction("attraction", "city", "state", 1.0, 2.0));
        ObjectMapper postMapper = new ObjectMapper();
        String requestBody = null;

    }

    @Test
    public void getUser() throws Exception {

    }

    @Test
    public void getUserID() throws Exception {

    }

    @Test
    public void getUserLocation() throws Exception {

    }

    @Test
    public void getAllUsersID() throws Exception {

    }

    @Test
    public void getAllVisitedLocations() throws Exception {

    }

    @Test
    public void getTripPricerTask() throws Exception {

    }

    @Test
    public void getUserRewards() throws Exception {

    }

    @Test
    public void getCumulateRewardPoints() throws Exception {

    }
}
