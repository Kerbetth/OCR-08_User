package tourGuideUser.client.dto.TrackerService;


import tourGuideUser.client.dto.TrackerService.location.Location;

import java.util.UUID;


public class UserLocation {
    private UUID userID;
    private Location latLongUser;


    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public Location getLatLongUser() {
        return latLongUser;
    }

    public void setLatLongUser(Location latLongUser) {
        this.latLongUser = latLongUser;
    }

}