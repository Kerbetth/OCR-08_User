package tourGuideUser;


import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import tourGuideUser.service.UserService;

@SpringBootTest
public class TestPerformance {

    /*
     * A note on performance improvements:
     *
     *     The number of users generated for the high volume tests can be easily adjusted via this method:
     *
     *     		InternalTestHelper.setInternalUserNumber(100000);
     *
     *
     *     These tests can be modified to suit new solutions, just as long as the performance metrics
     *     at the end of the tests remains consistent.
     *
     *     These are performance metrics that we are trying to hit:
     *
     *     highVolumeTrackLocation: 100,000 users within 15 minutes:
     *     		assertTrue(TimeUnit.MINUTES.toSeconds(15) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
     *
     *     highVolumeGetRewards: 100,000 users within 20 minutes:
     *          assertTrue(TimeUnit.MINUTES.toSeconds(20) >= TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime()));
     */
    UserService userService;
    DataTest dataTest = new DataTest();


    @BeforeEach
    void before() {

    }


    @Test
    public void highVolumeTrackLocation() {

    }
}
