package com.me.controller;

import java.util.List;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.me.dao.ItemDAO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.me.dao.DAO;
import com.me.dao.UserDAO;
import com.me.dao.CartDAO;
import com.me.exception.CartException;
import com.me.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import com.me.pojo.User;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author rachi
 */



@Controller
public class CartController extends DAO {

	@Autowired
	@Qualifier("itemDao")
	ItemDAO itemDao;

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;

	@Autowired
	ServletContext servletContext;

	

	@RequestMapping(value = "/cart/customerlist", method = RequestMethod.GET)
	public ModelAndView customerList(HttpServletRequest request) throws Exception {

		try {
			HttpSession session = (HttpSession) request.getSession();
			User user = (User) session.getAttribute("user");
			long id = user.getPersonID();
			List<Cart> carts = cartDao.list(id);
			int len = carts.size();
			return new ModelAndView("viewCart", "carts", carts);

		} catch (CartException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

	@RequestMapping(value = "/cart/remove/*", method = RequestMethod.GET)
	public ModelAndView removeItem(HttpServletRequest request) throws Exception {

		String url = request.getRequestURI();
		long id = 0;
		id = Integer.parseInt((url.split("/")[4]).split("\\.")[0]);
		try {
			Cart cart = cartDao.removeFromCart(id);
			HttpSession session = (HttpSession) request.getSession();
			User user = (User) session.getAttribute("user");
			long ident = user.getPersonID();
			List<Cart> carts = cartDao.list(ident);

			return new ModelAndView("viewCart", "carts", carts);
		} catch (CartException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

	@RequestMapping(value = "/cart/edit", method = RequestMethod.GET)
	public ModelAndView editCart(HttpServletRequest request) throws Exception {

		try {
			HttpSession session = (HttpSession) request.getSession();
			User user = (User) session.getAttribute("user");
			long id = user.getPersonID();
			List<Cart> carts = cartDao.list(id);
			int len = carts.size();
			return new ModelAndView("editCart", "carts", carts);

		} catch (CartException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}
        
        @RequestMapping(value = "/cart/insert/*", method = RequestMethod.POST)
	public ModelAndView insertCart(@ModelAttribute("cart") Cart cart, BindingResult result, HttpServletRequest request) throws Exception {
		String url = request.getRequestURI();
		long id = 0;
		id = Integer.parseInt((url.split("/")[4]).split("\\.")[0]);

		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		int quant = cart.getQuantity();
		cart = cartDao.insert(id, user, quant);
		return new ModelAndView("addToCart", "cart", cart);
	}

	@RequestMapping(value = "/cart/update/*", method = RequestMethod.POST)
	public ModelAndView updateCart(HttpServletRequest request) throws Exception {

		String url = request.getRequestURI();
		long id = 0;
		id = Integer.parseInt((url.split("/")[4]).split("\\.")[0]);

		try {
                    int quantitys = Integer.parseInt(request.getParameter("quantity"));
                    if(quantitys<0 || quantitys==0){
                       HttpSession session = (HttpSession) request.getSession();
			User user = (User) session.getAttribute("user");
			id = user.getPersonID();
			List<Cart> carts = cartDao.list(id);
			int len = carts.size();
			return new ModelAndView("editCart", "carts", carts); 
                    }
                    else{
			Map<String, String> quantity = new HashMap<>();
			quantity.put("quantity", request.getParameter("quantity"));
			System.out.println("Hash Map is  " + quantity);
			Cart cart = cartDao.updateQuantity(id, quantity);
			HttpSession session = (HttpSession) request.getSession();
			User user = (User) session.getAttribute("user");
			long ident = user.getPersonID();
			List<Cart> carts = cartDao.list(ident);
			return new ModelAndView("viewCart", "carts", carts);
                    }

		} catch (CartException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

}