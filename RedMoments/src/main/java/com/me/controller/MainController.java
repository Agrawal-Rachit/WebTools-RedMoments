package com.me.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.me.dao.UserDAO;
import com.me.dao.AdminDAO;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import com.me.exception.UserException;
import com.me.pojo.User;
import com.me.pojo.Admin;
import com.me.validator.UserValidator;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.DefaultAuthenticator;



/**
 *
 * @author rachi
 */



@Controller
@RequestMapping("/user/*")
public class MainController {
    
	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("adminDao")
	AdminDAO adminDao;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;

	@Autowired
	ServletContext servletContext;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	
        @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
	public String logout(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		httpSession.invalidate();
		return "logout";
	}
        

	@RequestMapping(value = "/user/buyer", method = RequestMethod.GET)
	protected String buyerHome(HttpServletRequest request) throws Exception {
		return "buyerHome";
	}

        
        @RequestMapping(value = "/", method = RequestMethod.GET)
	protected String sellerHome(HttpServletRequest request) throws Exception {
           return "sellerHome";
	}
        
        
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected String userLogin(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();

		try {

			System.out.print("loginUser");

			User user = userDao.getUser(request.getParameter("username"), request.getParameter("password"));
			Admin admin = adminDao.get(request.getParameter("username"), request.getParameter("password"));

			if ((user == null) && (admin == null)) {
				System.out.println("UserName/Password does not exist");
				session.setAttribute("errorMessage", "UserName/Password does not exist");
                               
				return "error";
			} 
                                          
			else if (((user != null) && user.getUsertype().equals("Seller") && (user.getActive().equalsIgnoreCase("false"))) ||
				((user != null) && user.getUsertype().equals("Seller") && (user.getActive().equalsIgnoreCase("Reject")))) {
				session.setAttribute("errorMessage", "Account is not activate");
				return "error";
			}
                        
                        else if ((user != null) && user.getUsertype().equals("Buyer") && (user.getActive().equalsIgnoreCase("true"))) {
				session.setAttribute("user", user);
				return "buyerHome";
			}
                        else if ((admin != null) && (admin.getUsertype().equals("admin"))) {
				session.setAttribute("admin", "a");
				return "adminHome";
			}

			 else {
                              session.setAttribute("user", user);
				return "sellerHome";
			}

		} catch (UserException e) {
                       	System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");
			return "error";
		}

	}

	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		ModelAndView mv = new ModelAndView("signUp");
		Map<String, String> usertype = new LinkedHashMap<String, String> ();
		usertype.put("Buyer", "Buyer");
		usertype.put("Seller", "Seller");

		mv.addObject("usertype", usertype);
		mv.addObject("user", new User());
		return mv;

	}

	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request, @ModelAttribute("user") User user, BindingResult result, HttpServletResponse response) throws Exception {

		validator.validate(user, result);

		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("signUp");
			Map<String, String> usertype = new LinkedHashMap<String, String> ();
			usertype.put("Buyer", "Buyer");
			usertype.put("Seller", "Seller");

			mv.addObject("usertype", usertype);
			mv.addObject("user", user);
			return mv;

		}

		try {

			System.out.print("registerNewUser");
		
			User userdao = userDao.signUpUser(user);
			request.getSession().setAttribute("user", userdao);
			if (userdao.getActive().equalsIgnoreCase("true")) {
				Email email = new SimpleEmail();
				email.setHostName("smtp.neu.edu");
				email.setSmtpPort(465);
                                email.setAuthenticator(new DefaultAuthenticator("username", "password"));
				email.setSSLOnConnect(true);
				email.setFrom("agrawal.rac@northeastern.edu");
				email.setSubject("Sign Up Successful");
				email.setMsg("Your account has been successfully created.");
				email.addTo("agrawal.rac@northeastern.edu");
				email.send();
                                
                                return new ModelAndView("accountSuccess", "user", userdao);
			}
			return new ModelAndView("sellerVerification", "user", userdao);


		} catch (UserException e) {
                        System.out.println("Exception: " + e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}

	}

	protected Map referenceData(HttpServletRequest request) throws Exception {

		Map referenceData = new HashMap();

		Map<String, String> usertype = new LinkedHashMap<String, String> ();
		usertype.put("Buyer", "Buyer");
		usertype.put("Seller", "Seller");
		referenceData.put("usertype", usertype);

		return referenceData;
	}
}