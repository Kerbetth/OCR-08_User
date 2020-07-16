package tourGuideUser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tourGuideUser.domain.CreateUser;
import tourGuideUser.domain.SetUserPreferences;
import tourGuideUser.domain.pricerreward.TripPricerTask;
import tourGuideUser.domain.trackerservice.Location;
import tourGuideUser.domain.trackerservice.VisitedLocation;
import tourGuideUser.domain.userservice.UserReward;
import tourGuideUser.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    /**
     * createUser controller create a new user with all the UserPreferences
     */

    @RequestMapping("/setInternalTestUser")
    public void setInternalTestUser(@RequestParam Integer number) {
        userService.setInternalTestUser(number);
    }

    @PostMapping("/setUserPreferences")
    public void setUserPreferences(@RequestParam String userName, @RequestBody SetUserPreferences userPreferences) {
        userService.setUserPreferences(userName, userPreferences);
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody CreateUser userParam) {
        userService.addUser(userParam);
    }

    @PostMapping("/addUserLocation")
    public void addUserLocation(@RequestParam String userId, @RequestBody VisitedLocation visitedLocation) {
        userService.addUserLocation(userId, visitedLocation);
    }

    @PostMapping("/addUserReward")
    public void addUserReward(@RequestParam String userId, @RequestBody UserReward userReward) {
        userService.addUserReward(userId, userReward);
    }

    @GetMapping("/getUserId")
    public UUID getUserId(@RequestParam String userName) {
        return userService.findUserByName(userName).getUserId();
    }

    @GetMapping("/getUserLocation")
    public Location getUserLocation(@RequestParam String userName) {
        return userService.getCurrentLocation(userName);
    }

    @GetMapping("/getAllUsersID")
    public List<UUID> getAllUsersID() {
        return userService.getAllUsersID();
    }

    @GetMapping("/getAllUsersName")
    public List<String> getAllUsersName() {
        return userService.getAllUsersName();
    }

    @GetMapping("/getAllVisitedLocations")
    public List<VisitedLocation> getAllVisitedLocations(@RequestParam String userId) {
        return userService.getAllVisitedLocation(userId); }

    @GetMapping("/getTripPricerTask")
    public TripPricerTask getTripPricerTask(@RequestParam String userName) {
        return userService.getTripPricerTask(userName);
    }

    @GetMapping("/getAttractionIds")
    public List<String> getAttractionIds(@RequestParam String userId) {
        return userService.getAttractionIds( userId);
    }

    @GetMapping("/getUserRewardSize")
    public int getUserRewardSize(@RequestParam String userId) {
        return userService.getUserRewardSize( userId);
    }

    @GetMapping("/getCumulateRewardPoints")
    public int getCumulateRewardPoints(@RequestParam String userId) {
        return userService.getCumulateRewardPoints(userId);
    }
}