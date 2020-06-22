package tourGuideUser.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import tourGuideUser.domain.Location;
import tourGuideUser.domain.User;
import tourGuideUser.domain.VisitedLocation;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.IntStream;


@Slf4j
@Repository
public class UserUtil {

    private static int internalUserNumber = 100;
    private static final String tripPricerApiKey = "test-server-api-key";
    private Map<String, User> internalUserMap = new HashMap<>();

    public static void setInternalUserNumber(int internalUserNumber) {
        internalUserNumber = internalUserNumber;
    }


    private UserUtil() {
        IntStream.range(0, internalUserNumber).forEach(i -> {
            String userName = "internalUser" + i;
            String phone = "000";
            String email = userName + "@tourGuide.com";
            User user = new User(UUID.randomUUID(), userName, phone, email);
            generateUserLocationHistory(user);
            internalUserMap.put(userName, user);
        });

        log.debug("Created " + internalUserNumber + " internal test users.");
    }

    public Map<String, User> getInternalUserMap() {
        return internalUserMap;
    }
    public void setInternalUserMap(Map<String, User> internalUserMap) {
        this.internalUserMap = internalUserMap;
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
