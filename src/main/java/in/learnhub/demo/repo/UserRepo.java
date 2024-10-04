package in.learnhub.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.learnhub.demo.Model.AppUser;

public interface UserRepo extends JpaRepository<AppUser, Integer> {
	
	public AppUser findByEmail(String email);

	public AppUser findByName(String name);



}
