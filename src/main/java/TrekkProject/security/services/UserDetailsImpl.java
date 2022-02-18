package TrekkProject.security.services;

import TrekkProject.security.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@Data
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String id;

	private String firstname;

	private String lastname;

	private String email;

	@JsonIgnore
	private String password;

	public UserDetailsImpl(String id, String firstname, String lastname, String email, String password) {
		this.id = id;
		this.firstname=firstname;
		this.lastname=lastname;
		this.email = email;
		this.password = password;
	}

	public static UserDetailsImpl build(User user) {
		return new UserDetailsImpl(
				user.getId(),
				user.getFirstname(),
				user.getLastname(),
				user.getEmail(),
				user.getPassword());
	}

	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {return email;}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
