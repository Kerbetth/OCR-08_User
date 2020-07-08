package tourGuideUser.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateUser {
    private final String userName;
    private String phoneNumber;
    private String emailAddress;

    @JsonCreator
    public CreateUser(String userName) {
        this.userName = userName;
    }
}
