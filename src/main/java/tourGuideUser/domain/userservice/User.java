package tourGuideUser.domain.userservice;


import lombok.Getter;
import lombok.Setter;
import tourGuideUser.domain.pricerreward.Provider;
import tourGuideUser.domain.trackerservice.VisitedLocation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
public class User {
	private final UUID userId;
	private final String userName;
	private String phoneNumber;
	private String emailAddress;
	private Date latestLocationTimestamp;
	private List<VisitedLocation> visitedLocations = new ArrayList<>();
	private List<UserReward> userRewards = new ArrayList<>();
	private UserPreferences userPreferences = new UserPreferences();
	private List<Provider> tripDeals = new ArrayList<>();

	public User(UUID userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public User(UUID userId, String userName, String phoneNumber, String emailAddress, Date latestLocationTimestamp, List<VisitedLocation> visitedLocations, List<UserReward> userRewards, UserPreferences userPreferences, List<Provider> tripDeals) {
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.latestLocationTimestamp = latestLocationTimestamp;
		this.visitedLocations = visitedLocations;
		this.userRewards = userRewards;
		this.userPreferences = userPreferences;
		this.tripDeals = tripDeals;
	}


	public User(UUID userId, String userName, String phoneNumber, String emailAddress) {
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
	}
}
