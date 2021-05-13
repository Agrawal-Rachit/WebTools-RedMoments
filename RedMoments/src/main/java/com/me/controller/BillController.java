package com.me.controller;

import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import com.me.dao.CartDAO;
import com.me.pojo.Cart;
import com.me.pojo.PDFView;
import com.me.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 *
 * @author rachi
 */



@Controller
@RequestMapping("/cart/*")
public class BillController extends PDFView {

	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;

	@Autowired
	ServletContext servletContext;

	@RequestMapping(value = "/cart/checkout", method = RequestMethod.POST)
	public ModelAndView PDF(@ModelAttribute("cart") Cart cart,
		ModelMap model,
		BindingResult result,
		HttpServletRequest request) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		User user = (User) session.getAttribute("user");
		long id = user.getPersonID();
		long cid = cart.getId();
		List<Cart> view = cartDao.list(id);
		model.addAttribute("cartitems", view);
		for (Cart c: view) {
			cartDao.delete(c);
		}

		View viewpdf = new PDFView();
		return new ModelAndView(viewpdf);

	}

}