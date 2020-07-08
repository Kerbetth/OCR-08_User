package tourGuideUser.unit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tourGuideUser.DataTest;
import tourGuideUser.controller.UserController;
import tourGuideUser.domain.CreateUser;
import tourGuideUser.domain.SetUserPreferences;
import tourGuideUser.domain.trackerservice.Attraction;
import tourGuideUser.domain.trackerservice.Location;
import tourGuideUser.domain.trackerservice.VisitedLocation;
import tourGuideUser.domain.userservice.UserReward;

import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class TrackerControllerTest {

    @MockBean
    private UserController trackerController;

    private DataTest dataTest = new DataTest();

    @Autowired
    protected MockMvc mockMvc;


    @Test
    public void setUserPreferences() throws Exception {
        ObjectMapper postMapper = new ObjectMapper();
        SetUserPreferences userPreferences = new SetUserPreferences(
                20,"USD", 0,1000,2,2,2,1
        );
        String requestBody = null;
        try {
            requestBody = postMapper
                    .writeValueAsString(userPreferences);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.mockMvc.perform(post("/setUserPreferences?userName=testUser1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void addUser() throws Exception {
        CreateUser createUser = new CreateUser("newUser");
        ObjectMapper postMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = postMapper
                    .writeValueAsString(createUser);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.mockMvc.perform(post("/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void addUserLocation() throws Exception {
        VisitedLocation visitedLocation = new VisitedLocation(UUID.randomUUID(), new Location(1.0, 2.0), new Date());
        ObjectMapper postMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = postMapper
                    .writeValueAsString(visitedLocation);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.mockMvc.perform(post("/addUserLocation?userName=testUser1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
                .andExpect(status().isOk());
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
        try {
            requestBody = postMapper
                    .writeValueAsString(userReward);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.mockMvc.perform(post("/addUserReward?userName=testUser1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void getUser() throws Exception {
        this.mockMvc.perform(get("/getUser?userName=testUser1" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void getUserID() throws Exception {
        this.mockMvc.perform(get("/getUserId?userName=testUser1" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void getUserLocation() throws Exception {
        this.mockMvc.perform(get("/getUserLocation?userName=testUser1" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void getAllUsersID() throws Exception {
        this.mockMvc.perform(get("/getUserLocation?userName=testUser1" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }
    @Test
    public void getAllVisitedLocations() throws Exception {
        this.mockMvc.perform(get("/getAllVisitedLocations?userName=testUser1" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void getTripPricerTask() throws Exception {
        this.mockMvc.perform(get("/getTripPricerTask?userName=testUser1" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void getUserRewards() throws Exception {
        this.mockMvc.perform(get("/getUserRewards?userName=testUser1" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void getCumulateRewardPoints() throws Exception {
        this.mockMvc.perform(get("/getCumulateRewardPoints?userName=testUser1" )
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }
}
