package tourGuideUser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuideUser.domain.pricerreward.TripPricerTask;
import tourGuideUser.domain.trackerservice.Location;
import tourGuideUser.domain.trackerservice.VisitedLocation;
import tourGuideUser.domain.userservice.User;
import tourGuideUser.domain.userservice.UserPreferences;
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
    public void setUserPreferences(@RequestParam String userName, UserPreferences userPreferences) {
        userService.setUserPreferences(userName, userPreferences);
    }
    @GetMapping("/getUser")
    public User getUser(@RequestParam String userName) {
        return userService.findUserbyName(userName);
    }
    @GetMapping("/getUserId")
    public UUID getUserId(@RequestParam String userName) {
        return userService.findUserbyName(userName).getUserId();
    }
    @GetMapping("/getUserLocation")
    public Location getUserLocation(@RequestParam String userName) {
        return userService.getLocation(userName);
    }
    @GetMapping("/getAllUsersID")
    public List<UUID> getAllUsersID() {
        return userService.getAllUsersID() ;
    }
    @GetMapping("/getAllVisitedLocations")
    public List<VisitedLocation> getAllVisitedLocations(String userName) {
        return userService.getAllVisitedLocation(userName);
    }
    @GetMapping("/getTripPricerTask")
    public TripPricerTask getTripPricerTask(String userName) {
        return userService.getTripPricerTask(userName);
    }
    @GetMapping("/getUserRewards")
    public int getUserRewards(String userName) {
        return userService.getUserRewards(userName);
    }
}