package tourGuideUser.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import tourGuideUser.client.dto.TrackerService.VisitedLocation;
import tourGuideUser.client.dto.TrackerService.location.Attraction;
import tourGuideUser.client.dto.TrackerService.location.Location;
import tourGuideUser.domain.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.IntStream;

@Slf4j
@Repository
public class UserData {

    private static int internalUserNumber = 100;

    //Todo this map replace the future needed database
    private final Map<String, User> internalUserMap = new HashMap<>();

    public static void setInternalUserNumber(int internalUserNumber) {
        internalUserNumber = internalUserNumber;
    }


    public void addANewUser(User user){
        internalUserMap.put(user.getUserName(),user);
    }

    public User findUserbyName(String userName){
        return internalUserMap.get(userName);
    }

    public List<User> collectAllUsers(){
        return (List<User>) internalUserMap.values();
    }

}
