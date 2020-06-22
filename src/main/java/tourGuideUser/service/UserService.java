package tourGuideUser.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tourGuideUser.domain.User;
import tourGuideUser.domain.UserPreferences;
import tourGuideUser.util.UserUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserUtil userUtil;


    public void setUserPreferences(String userName,
                                   UserPreferences userPreferences) {
        User user = findUserbyName(userName);
        user.setUserPreferences(userPreferences);
        Map<String, User> userMap = userUtil.getInternalUserMap();
        userMap.put(userName, user);
        userUtil.setInternalUserMap(userMap);
    }

    public User findUserbyName(String userName) {
        return userUtil.getInternalUserMap().get(userName);
    }


    public List<UUID> getAllUsersID() {
        List<UUID> userId = new ArrayList<>();
        List<User> users = new ArrayList<>(userUtil.getInternalUserMap().values());
        for (User user : users) {
            userId.add(user.getUserId());
        }
        return userId;
    }
}
