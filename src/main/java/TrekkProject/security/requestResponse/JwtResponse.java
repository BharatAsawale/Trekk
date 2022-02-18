package TrekkProject.security.requestResponse;

import lombok.Data;

@Data
public class JwtResponse {
	private String id;
	private String firstname;
	private String lastname;
	private String email;
	private String type = "Bearer";
	private String token;

	public JwtResponse(String accessToken, String id, String firstname, String lastname, String email) {
		this.token = accessToken;
		this.id = id;
		this.firstname=firstname;
		this.lastname=lastname;
		this.email = email;
	}
}
