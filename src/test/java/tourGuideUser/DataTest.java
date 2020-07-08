package tourGuideUser;

import lombok.extern.slf4j.Slf4j;
import tourGuideUser.domain.trackerservice.Location;
import tourGuideUser.domain.trackerservice.VisitedLocation;
import tourGuideUser.domain.userservice.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.IntStream;

@Slf4j
public class DataTest {

    private final Map<String, User> internalUserMap = new HashMap<>();

    public Map<String, User> getInternalUserMap() {
        return internalUserMap;
    }
    public void initializeInternalUsers() {
        IntStream.range(0, 10).forEach(i -> {
            String userName = "testUser" + i;
            String phone = "000";
            String email = userName + "@tourGuide.com";
            User user = new User(UUID.randomUUID(), userName, phone, email);
            generateUserLocationHistory(user);

            internalUserMap.put(userName, user);
        });
        log.debug("Created 10 internal test users.");
    }

    private void generateUserLocationHistory(User user) {
        ArrayList<VisitedLocation> visitedLocations = new ArrayList<>();
        IntStream.range(0, 3).forEach(i -> {
            visitedLocations.add(
                    new VisitedLocation(
                    user.getUserId(),
                            new Location(generateRandomLatitude(),
                                    generateRandomLongitude()),
                            getRandomTime()));
        });
        user.setVisitedLocations(visitedLocations);
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
