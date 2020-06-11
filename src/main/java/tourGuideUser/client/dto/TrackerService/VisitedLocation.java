package tourGuideUser.client.dto.TrackerService;

import tourGuideUser.client.dto.TrackerService.location.Location;

import java.util.Date;
import java.util.UUID;

public class VisitedLocation {
    public final UUID userId;
    public final Location location;
    public final Date timeVisited;

    public VisitedLocation(UUID userId, Location location, Date timeVisited) {
        this.userId = userId;
        this.location = location;
        this.timeVisited = timeVisited;
    }
}
