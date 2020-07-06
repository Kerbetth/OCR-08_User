package tourGuideUser.domain;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreateUser {
	private final String userName;
	private String phoneNumber;
	private String emailAddress;

	public CreateUser(String userName) {
		this.userName = userName;
	}
}
