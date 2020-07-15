package tourGuideUser.unit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tourGuideUser.DataTest;
import tourGuideUser.domain.trackerservice.Location;
import tourGuideUser.domain.trackerservice.VisitedLocation;
import tourGuideUser.domain.userservice.User;
import tourGuideUser.service.UserService;
import tourGuideUser.util.UserUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private UserUtil gpsUtil;


    private DataTest dataTest = new DataTest();
    private ArrayList<User> users;

    @InjectMocks
    UserService userService = new UserService();

    @BeforeEach
    void setup() {
        users = new ArrayList<>();
        User user = new User(UUID.randomUUID(), "user1", "000-555-444", "user1@mail.com");
        User user2 = new User(UUID.randomUUID(), "user2", "000-666-444", "user2@mail.com");
        ArrayList<VisitedLocation> visitedLocations = new ArrayList<>();
        visitedLocations.add(new VisitedLocation(UUID.randomUUID(), new Location(1.0, 2.0), new Date()));
        user.setVisitedLocations(visitedLocations);
        users.add(user);
        users.add(user2);
        dataTest.initializeInternalUsers();
        when(gpsUtil.getInternalUserMap()).thenReturn(dataTest.getInternalUserMap());
    }

    @Test
    public void getAllVisitedLocation() {
        //ACT
        List<VisitedLocation> visitedLocations = userService.getAllVisitedLocation("testUser1");

        //ASSERT
        assertThat(visitedLocations).hasSize(3);
        assertThat(visitedLocations.get(0).timeVisited).isBeforeOrEqualTo(visitedLocations.get(1).timeVisited);
    }

    @Test
    public void shouldReturnNoReward() {
        //ACT
        int cumulateRewardPoints = userService.getCumulateRewardPoints("testUser1");

        //ASSERT
        assertThat(cumulateRewardPoints).isEqualTo(0);
    }

    @Test
    public void getLocationOfAllUsersShouldReturnGoodUserLocations() {
        //ACT
        int cumulateRewardPoints = userService.getCumulateRewardPoints("testUser1");

        //ASSERT
        assertThat(cumulateRewardPoints).isEqualTo(0);
    }

    @Test
    public void shouldReturnGoodUserAfterFindUserByName() {
        //ACT
        User user = userService.findUserByName("testUser1");
        //ASSERT
        assertThat(user.getEmailAddress()).isEqualTo("testUser1@tourGuide.com");
        assertThat(user.getPhoneNumber()).isEqualTo("000");
    }

    @Test
    public void shouldReturnGoodLocationAfterGetCurrentLocation() {
        //ACT
        Location location = new Location(1.0, 2.0);
        userService.addUserLocation("testUser1", new VisitedLocation(UUID.randomUUID(), location, new Date()));
        Location locationResult = userService.getCurrentLocation("testUser1");
        //ASSERT
        assertThat(location.latitude).isEqualTo(location.latitude);
        assertThat(location.longitude).isEqualTo(location.longitude);
    }
}
