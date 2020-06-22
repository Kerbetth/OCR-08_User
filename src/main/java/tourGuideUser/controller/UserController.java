package tourGuideUser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tourGuideUser.domain.User;
import tourGuideUser.domain.UserPreferences;
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
    public User getUser(String username) {
        return userService.findUserbyName(username);
    }


    @GetMapping("/getAllUsersID")
    public List<UUID> getAllUsersID() {
        return userService.getAllUsersID() ;
    }

}