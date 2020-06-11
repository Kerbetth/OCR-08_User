package tourGuideUser.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tourGuideUser.client.dto.TrackerService.VisitedLocation;
import tourGuideUser.client.dto.TrackerService.location.Location;
import tourGuideUser.domain.User;
import tourGuideUser.domain.UserPreferences;
import tourGuideUser.repository.UserData;

import javax.money.CurrencyUnit;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
