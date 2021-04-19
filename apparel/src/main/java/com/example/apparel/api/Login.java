package com.example.apparel.api;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apparel.model.User;
import com.example.apparel.repository.UserRepository;


//@RestController
//public class Login {
//	private UserRepository repository;
//	
//	public Login(UserRepository repository) {
//		this.repository = repository;
//	}
//	
//	@GetMapping("/")
//	ResponseEntity<User> login_get(HttpServletRequest req){
//		HttpSession session =  req.getSession();
//		String id  = (String) session.getAttribute("userid");
//		return new ResponseEntity<>(new User("Rajarshi","Roychoudhury","M","rroychoudhury2@gmail.com"),HttpStatus.OK);
//
//	}
//	
//	
//	@GetMapping("/login")
//	public void post_request(HttpServletRequest req,HttpServletResponse res) throws IOException {
//		HttpSession session  = req.getSession();
//		if(session.isNew()) {
//			System.out.println("New Session is created");
//			String email  = (String)req.getParameter("email");
//			System.out.println(req.getRequestURI());
//			System.out.println(email);
//			res.sendRedirect("/getUser");
//			return;
//			
//		}
//		else if(session.getAttribute("userid")!=null) {
//			System.out.println("UserId not found(maybe session expired?)");
//			res.sendRedirect("/getUser");
//			return;
//		}
//		System.out.println("Redirected");
//		res.sendRedirect("/getUser");
//	}
//	
//	@GetMapping("/getUser")
//	ResponseEntity<User> getUser(HttpServletRequest req){
//		HttpSession session =  req.getSession();
//		String id  = (String) session.getAttribute("userid");
//		if(id!=null) {
//			User user = repository.findById(id).get();
//			return new ResponseEntity<>(user,HttpStatus.OK);
//		}
//		System.out.println("No id");
//		return new ResponseEntity<>(null,HttpStatus.valueOf(202));
//	}
//	
//	
//	@PostMapping("/login1")
//	ResponseEntity<User> login_post(HttpServletRequest req){
//		System.out.println("Post");
//		System.out.println(req.getRequestURL());
//		HttpSession session =  req.getSession();
//		String id  = (String) session.getAttribute("userid");
//		if(id!=null) {
//			User user = repository.findById(id).get();
//			return new ResponseEntity<>(user,HttpStatus.OK);
//		}
//			String email = req.getParameter("email");
//			System.out.println(email);
//			if(!repository.findByEmail(email).isEmpty()) {
//				return new ResponseEntity<>(HttpStatus.valueOf(402));
//			}
//			String firstname = req.getParameter("firstname");
//			String lastname = req.getParameter("lastname");
//			String gender = req.getParameter("gender");
//			User user = new User(firstname,lastname,gender,email);
//			session.setAttribute("userid",user.getId());
//			repository.save(user);
//			System.out.println(user);
//			return new ResponseEntity<>(user,HttpStatus.OK);
//			
//
//	}
//	
//}