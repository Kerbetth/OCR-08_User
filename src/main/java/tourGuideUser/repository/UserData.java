package tourGuideUser.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import tourGuideUser.domain.trackerservice.Location;
import tourGuideUser.domain.trackerservice.VisitedLocation;
import tourGuideUser.domain.userservice.User;
import tourGuideUser.domain.userservice.UserPreferences;

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
        User user = new User(UUID.randomUUID(), userName);
        user.setUserPreferences(new UserPreferences());
        generateUserLocationHistory(user);
        return user;
    }

    public List<User> collectAllUsers(){
        User user = new User(UUID.randomUUID(), "userName");
        user.setUserPreferences(new UserPreferences());
        generateUserLocationHistory(user);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        System.out.println(users);
        return users;
    }

    private User generateUserLocationHistory(User user) {
        List<VisitedLocation> visitedLocations = new ArrayList<>();
        IntStream.range(0, 3).forEach(i -> {
            visitedLocations.add(
                    new VisitedLocation(user.getUserId(),
                            new Location(generateRandomLatitude(),
                                    generateRandomLongitude()),
                            getRandomTime()));
        });
        user.setVisitedLocations(visitedLocations);
        return user;
    }

    private double generateRandomLongitude() {
        double leftLimit = -180;
        double rightLimit = 180;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    private double generateRandomLatitude() {
        double leftLimit = -85.05112878;
        double rightLimit = 85.05112878;
        return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
    }

    private Date getRandomTime() {
        LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
    }
}
