package tourGuideUser.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tourGuideUser.domain.User;
import tourGuideUser.domain.UserPreferences;
import tourGuideUser.repository.UserData;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserData userData;


    public void saveUser(User user) {
        userData.addANewUser(user);
    }

    public void setUserPreferences(String userName,
                                   UserPreferences userPreferences) {
        User user = findUserbyName(userName);
        user.setUserPreferences(userPreferences);
        saveUser(user);
    }

    public User findUserbyName(String userName) {

        return userData.findUserbyName(userName);
    }

    public List<User> collectAllUsers() {

        return userData.collectAllUsers();
    }
}
