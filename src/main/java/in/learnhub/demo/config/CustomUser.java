package in.learnhub.demo.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import in.learnhub.demo.Model.AppUser;

public class CustomUser implements UserDetails {

	
	
	private AppUser user;

	public CustomUser(AppUser user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		SimpleGrantedAuthority authority= new SimpleGrantedAuthority(user.getRole());
		
		
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		// 
		return user.getPassword() ;
	}

	@Override
	public String getUsername() {
		
		return user.getEmail();
	}

}
