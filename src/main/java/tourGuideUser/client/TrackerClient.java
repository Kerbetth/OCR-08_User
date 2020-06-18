package tourGuideUser.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import tourGuideUser.client.dto.TrackerService.FiveNearestAttractions;
import tourGuideUser.client.dto.TrackerService.location.Location;

@Component
@Slf4j
public class TrackerClient {

    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public FiveNearestAttractions get5NearestAttraction(Location location) {
        HttpGet request = new HttpGet(
                "http://localhost:8081//getFiveNearbyAttractions?longitude="
                + location.longitude
                + "&latitude="
                + location.latitude);
        request.addHeader(HttpHeaders.ACCEPT, "MediaType.APPLICATION_JSON_VALUE");

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            ObjectMapper objectMapper = new ObjectMapper();
            FiveNearestAttractions fiveNearestAttractions = objectMapper.readValue(response.toString(), FiveNearestAttractions.class);
            return fiveNearestAttractions;
        } catch (Exception e) {
            log.error("cannot send the get http request");
        }
        return null;
    }

}