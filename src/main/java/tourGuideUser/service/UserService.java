package tourGuideUser.service;


import lombok.extern.slf4j.Slf4j;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tourGuideUser.domain.CreateUser;
import tourGuideUser.domain.SetUserPreferences;
import tourGuideUser.domain.pricerreward.TripPricerTask;
import tourGuideUser.domain.trackerservice.Location;
import tourGuideUser.domain.trackerservice.VisitedLocation;
import tourGuideUser.domain.userservice.User;
import tourGuideUser.domain.userservice.UserPreferences;
import tourGuideUser.domain.userservice.UserReward;
import tourGuideUser.util.UserUtil;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
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
                                   SetUserPreferences preferences) {
        CurrencyUnit currencyUnit = Monetary.getCurrency(preferences.getCurrencyUnit());
        User user = findUserByName(userName);
        UserPreferences userPreferences = new UserPreferences(
                preferences.getAttractionProximity(),
                currencyUnit,
                Money.of(preferences.getLowerPricePoint(), currencyUnit),
                Money.of(preferences.getLowerPricePoint(), currencyUnit),
                preferences.getTripDuration(),
                preferences.getTicketQuantity(),
                preferences.getNumberOfAdults(),
                preferences.getNumberOfChildren()
        );

        user.setUserPreferences(userPreferences);
        Map<String, User> userMap = userUtil.getInternalUserMap();
        userMap.put(userName, user);
        userUtil.setInternalUserMap(userMap);
    }

    public void addUser(CreateUser createUser) {
        User user =new User(
                UUID.randomUUID(),
                createUser.getUserName(),
                createUser.getPhoneNumber(),
                createUser.getPhoneNumber());
        if (!userUtil.getInternalUserMap().containsKey(user.getUserName())) {
            userUtil.getInternalUserMap().put(user.getUserName(), user);
        }
    }
    public void addUserLocation(String userName, VisitedLocation visitedLocation) {
        User user = findUserByName(userName);
        user.getVisitedLocations().add(new VisitedLocation(user.getUserId(), visitedLocation.location, visitedLocation.timeVisited));
        Map<String, User> userMap = userUtil.getInternalUserMap();
        userMap.put(userName, user);
        userUtil.setInternalUserMap(userMap);
    }

    public void addUserReward( String userName, UserReward userReward) {
        User user = findUserByName(userName);
        List<UserReward> userRewards = user.getUserRewards();
        userRewards.add(userReward);
        Map<String, User> userMap = userUtil.getInternalUserMap();
        userMap.put(userName, user);
        userUtil.setInternalUserMap(userMap);
    }

    public User findUserByName(String userName) {
        return userUtil.getInternalUserMap().get(userName);
    }

    public Location getCurrentLocation(String userName) {
        User user = findUserByName(userName);
        return user.getVisitedLocations().get(
                user.getVisitedLocations().size() - 1).location;
    }

    public List<VisitedLocation> getAllVisitedLocation(String userName) {
        return findUserByName(userName).getVisitedLocations();
    }

    public List<UserReward> getUserRewards(String userName) {
        return findUserByName(userName).getUserRewards();
    }

    public List<UUID> getAllUsersID() {
        List<UUID> userId = new ArrayList<>();
        List<User> users = new ArrayList<>(userUtil.getInternalUserMap().values());
        for (User user : users) {
            userId.add(user.getUserId());
        }
        return userId;
    }

    public TripPricerTask getTripPricerTask(String userName) {
        UserPreferences userPreferences = findUserByName(userName).getUserPreferences();
        return new TripPricerTask(
                "randomAPIKey",
                userPreferences.getNumberOfAdults(),
                userPreferences.getNumberOfChildren(),
                userPreferences.getTripDuration());
    }

    public Integer getCumulateRewardPoints(String userName){
        List<UserReward> userRewards = findUserByName(userName).getUserRewards();
        return userRewards.stream().mapToInt(i -> i.getRewardPoints()).sum();
    }


    public void setInternalTestUser(Integer number) {
        userUtil.changeNumberOfUsers(number);
    }
}
