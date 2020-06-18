package tourGuideUser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import tourGuideUser.domain.User;
import tourGuideUser.domain.UserPreferences;
import tourGuideUser.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    /**
     * createUser controller create a new user with all the UserPreferences
     */

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PostMapping("/setUserPreferences")
    public void setUserPreferences(@RequestParam String userName, UserPreferences userPreferences) {
        userService.setUserPreferences(userName, userPreferences);
    }

    @GetMapping("/getUser")
    public User getUser(String username) {
        return userService.findUserbyName(username);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.collectAllUsers();
    }

    @GetMapping("/getAllCurrentLocation")
    public List<User> getAllUsers() {
        return userService.collectAllUsers();
    }
}