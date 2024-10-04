package in.learnhub.demo.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.learnhub.demo.Model.AppUser;
import in.learnhub.demo.repo.UserRepo;
import in.learnhub.demo.service.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	private UserService userve;
	
	@Autowired 
	private UserRepo repo;
	
	@GetMapping("/signup")
	public String signuppage(){
		return "signup";
	}
	
	@PostMapping("/register")
	public String registerpage(@ModelAttribute AppUser user, HttpSession session) {
		
		AppUser u =userve.SaveUser(user);
		
		if(u != null) {
			session.setAttribute("msg", "User Registered Successfully <p>&#x1F616;</p>");
			//System.out.println("success");
		}else {
			//System.out.println("error");
			session.setAttribute("msg", "Something Went wWong");
		}
		
		return "redirect:/signup";
	}
	
	@GetMapping("/customlogin")
	public String loginpage() {
		return "login";
	}

	@GetMapping("/Tools")
	public String Tools() {
		return "Tools";
	}

	@GetMapping("/comingsoon")
	public String comingsoon() {
		return "comingsoon";
	}

	@GetMapping("/snapshot")
	public String snapshot() {
		return "snapshot";
	}

	@GetMapping("/computer_courses")
	public String computer_courses() {
		return "computer_courses";
	}

	@GetMapping("/pdf")
	public String pdf() {
		return "pdf";
	}

	@GetMapping("/quiz")
	public String quiz() {
		return "quiz";
	}

	@GetMapping("/pdfviewer")
	public String pdfviewer() {
		return "pdfviewer";
	}
	
	
	@GetMapping("/user/profile")
	public String profile(Principal p, Model m) {
		
		
		String email = p.getName();
				
			AppUser user = repo.findByEmail(email);	
			
			m.addAttribute("value", user);


		return "profilepage";
	}
	
	@GetMapping("/admin/profile")
	public String adminprofile(Principal p, Model m) {
		
		
		String email = p.getName();
				
			AppUser user = repo.findByEmail(email);	
			List<AppUser> users = userve.findAllUsers();

			m.addAttribute("value1", user);
			m.addAttribute("value", users);


		 return "admindashboard";
	}
	
	

	

}
