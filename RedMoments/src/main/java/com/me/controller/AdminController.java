/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.controller;


import javax.servlet.http.HttpServletRequest;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import com.me.dao.AdminDAO;
import com.me.dao.ItemDAO;
import com.me.dao.UserDAO;
import com.me.exception.UserException;
import com.me.pojo.Item;
import com.me.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.commons.mail.DefaultAuthenticator;

/**
 *
 * @author rachi
 */


@Controller
public class AdminController {

	@Autowired
	@Qualifier("itemDao")
	ItemDAO itemDao;

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("adminDao")
	AdminDAO adminDao;

	@Autowired
	ServletContext servletContext;


	@RequestMapping(value = "/admin/viewUsers", method = RequestMethod.GET)
	public ModelAndView viewUsers(HttpServletRequest request) throws Exception {

		try {
			List<User> users = adminDao.list();
			return new ModelAndView("userList", "users", users);

		} catch (UserException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}

	}

	@RequestMapping(value = "/admin/pendingSellers", method = RequestMethod.GET)
	public ModelAndView pendingSellers(HttpServletRequest request) throws Exception {

		try {
			List<User> users = adminDao.rejectlist();
			return new ModelAndView("pendingSellers", "users", users);

		} catch (UserException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}

	}

        
        
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	protected String adminHome(HttpServletRequest request) throws Exception {
		return "adminHome";
	}
        
        
        
	@RequestMapping(value = "/admin/remove/*", method = RequestMethod.GET)
	public ModelAndView removeUser(HttpServletRequest request) throws Exception {

		String uri = request.getRequestURI();
		long id = 0;
		id = Integer.parseInt((uri.split("/")[4]).split("\\.")[0]);
		try {
			User user = adminDao.deleteUser(id);
			List<User> users = adminDao.list();
			return new ModelAndView("userList", "users", users);
		} catch (UserException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

	@RequestMapping(value = "/admin/approveSeller/*", method = RequestMethod.POST)
	public ModelAndView approveSeller(HttpServletRequest request) throws Exception {

		String uri = request.getRequestURI();
		long id = 0;
		id = Integer.parseInt((uri.split("/")[4]).split("\\.")[0]);
		try {

			Map<String, String> status = new HashMap<>();
			status.put("active", "true");

			User user = adminDao.updateUserA(id, status);
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
			List<User> users = adminDao.rejectlist();
			return new ModelAndView("pendingSellers", "users", users);
		} catch (UserException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

	

	@RequestMapping(value = "/admin/rejectedSellers", method = RequestMethod.GET)
	public ModelAndView rejectedSellers(HttpServletRequest request) throws Exception {
		try {
			List<User> users = adminDao.rejectuser();
			return new ModelAndView("rejectedSellers", "users", users);
		} catch (UserException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

	@RequestMapping(value = "/admin/itemsList", method = RequestMethod.GET)
	public ModelAndView viewItems(HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("itemListMain");
		List<Item> items = itemDao.list();
		mv.addObject("items", items);
		return mv;
	}
        @RequestMapping(value = "/admin/reject/*", method = RequestMethod.GET)
	public ModelAndView rejectSeller(HttpServletRequest request) throws Exception {

		String url = request.getRequestURI();
		long id = 0;
		id = Integer.parseInt((url.split("/")[4]).split("\\.")[0]);
		try {

			Map<String, String> status = new HashMap<>();
			status.put("active", "Reject");
			User user = adminDao.updateUserR(id, status);
			List<User> users = adminDao.rejectuser();
			return new ModelAndView("rejectedSellers", "users", users);
		} catch (UserException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

}