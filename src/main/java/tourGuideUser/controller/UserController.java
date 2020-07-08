package tourGuideUser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tourGuideUser.domain.CreateUser;
import tourGuideUser.domain.SetUserPreferences;
import tourGuideUser.domain.pricerreward.TripPricerTask;
import tourGuideUser.domain.trackerservice.Location;
import tourGuideUser.domain.trackerservice.VisitedLocation;
import tourGuideUser.domain.userservice.User;
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

    @PostMapping("/setUserPreferences")
    public void setUserPreferences(@RequestParam String userName, @RequestBody SetUserPreferences userPreferences) {
        userService.setUserPreferences(userName, userPreferences);
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody CreateUser userParam) {
        userService.addUser(userParam);
    }

    @PostMapping("/addUserLocation")
    public void addUserLocation(@RequestParam String userName, @RequestBody VisitedLocation visitedLocation) {
        userService.addUserLocation(userName, visitedLocation);
    }

    @PostMapping("/addUserReward")
    public void addUserReward(@RequestParam String userName, @RequestBody UserReward userReward) {
        userService.addUserReward(userName, userReward);
    }

    @GetMapping("/getUser")
    public User getUser(@RequestParam String userName) {
        return userService.findUserByName(userName);
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

    @GetMapping("/getAllVisitedLocations")
    public List<VisitedLocation> getAllVisitedLocations(String userName) {
        return userService.getAllVisitedLocation(userName); }

    @GetMapping("/getTripPricerTask")
    public TripPricerTask getTripPricerTask(String userName) {
        return userService.getTripPricerTask(userName);
    }

    @GetMapping("/getUserRewards")
    public List<UserReward> getUserRewards(String userName) {
        return userService.getUserRewards(userName);
    }

    @GetMapping("/getCumulateRewardPoints")
    public int getCumulateRewardPoints(String userName) {
        return userService.getCumulateRewardPoints(userName);
    }
}