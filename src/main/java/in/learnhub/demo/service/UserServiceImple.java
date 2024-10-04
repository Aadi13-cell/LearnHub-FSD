package in.learnhub.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import in.learnhub.demo.Model.AppUser;
import in.learnhub.demo.repo.UserRepo;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImple implements UserService {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public AppUser SaveUser(AppUser newuser) {
		
		String password = passwordEncoder.encode(newuser.getPassword());
		newuser.setPassword(password);
		newuser.setRole("ROLE_NORMAL");
		
		AppUser u = repo.save(newuser);
		
		return u;
	}

	@Override
	public void removemessage() {
		
	HttpSession session=((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
	
	session.removeAttribute("msg");
		
	}


	@Override
	public List<AppUser> findAllUsers() {
		return repo.findAll();  // Directly return the list of AppUser entities
	}
	



}
