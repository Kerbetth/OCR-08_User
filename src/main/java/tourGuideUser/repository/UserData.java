package tourGuideUser.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import tourGuideUser.client.dto.TrackerService.VisitedLocation;
import tourGuideUser.client.dto.TrackerService.location.Location;
import tourGuideUser.domain.User;
import tourGuideUser.domain.UserPreferences;

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
        User user = new User(UUID.randomUUID(), userName, "phone", "email");
        user.setUserPreferences(new UserPreferences());
        generateUserLocationHistory(user);
        return user;
    }

    public List<User> collectAllUsers(){
        User user = new User(UUID.randomUUID(), "userName", "phone", "email");
        user.setUserPreferences(new UserPreferences());
        generateUserLocationHistory(user);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        System.out.println(users);
        return users;
    }

    private void generateUserLocationHistory(User user) {
        IntStream.range(0, 3).forEach(i -> {
            user.addToVisitedLocations(
                    new VisitedLocation(user.getUserId(),
                            new Location(generateRandomLatitude(),
                                    generateRandomLongitude()),
                            getRandomTime()));
        });
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
