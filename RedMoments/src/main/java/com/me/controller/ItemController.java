package com.me.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.me.dao.ItemDAO;
import com.me.dao.CartDAO;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.me.dao.CategoryDAO;
import com.me.dao.UserDAO;
import com.me.exception.ItemException;
import com.me.pojo.Item;
import com.me.pojo.Cart;
import com.me.pojo.Category;
import com.me.pojo.User;
import com.me.validator.ItemValidator;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 *
 * @author rachi
 */



@Controller
public class ItemController {
  

	@Autowired
	@Qualifier("itemDao")
	ItemDAO itemDao;

	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDao;

	@Autowired
	@Qualifier("cartDao")
	CartDAO cartDao;

	@Autowired
	@Qualifier("userDao")
	UserDAO userDao;

	@Autowired
	@Qualifier("itemValidator")
	ItemValidator validator;

	@Autowired
	ServletContext servletContext;
        
        	@RequestMapping(value = "/item/add", method = RequestMethod.GET)
	public ModelAndView addItem(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("categories", categoryDao.getAllCategory());
		mv.addObject("item", new Item());
		mv.setViewName("addItem");
		return mv;
	}
        
	@RequestMapping(value = "/item/add", method = RequestMethod.POST)
        public ModelAndView addItem(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("item") Item item, BindingResult result) throws Exception {

		if ("true" == request.getAttribute("unsafe_check")) {
			ModelAndView mv = null;
			mv = new ModelAndView("securityError");
			return mv;
		}

		validator.validate(item, result);
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView();
			mv.addObject("categories", categoryDao.getAllCategory());
			mv.addObject("item", item);
			mv.setViewName("addItem");
			return mv;
		}

		try {
			User user = userDao.get(item.getPostedBy());
			item.setUser(user);
			item = itemDao.createItem(item);

			for (Category category: item.getCategories()) {
				category = categoryDao.get(category.getTitle());
				category.getItems().add(item);
				categoryDao.updateCategory(category); 

			}

                        Item item1 = itemDao.createItem(item);
			return new ModelAndView("addItemSuccess", "item", item);

		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}

	}

	@RequestMapping(value = "/item/list", method = RequestMethod.GET)
	public ModelAndView listItem(HttpServletRequest request) throws Exception {

		ModelAndView mv = new ModelAndView("viewAllBuyer");
		List<Item> items = itemDao.list();
		Map<Integer, Integer> quant = new LinkedHashMap<Integer, Integer> ();
		quant.put(1, 1);
		quant.put(2, 2);
		quant.put(3, 3);
		quant.put(4, 4);
		quant.put(5, 5);
		quant.put(6, 6);
		quant.put(7, 7);
		quant.put(8, 8);
		quant.put(9, 9);
		quant.put(10, 10);

		mv.addObject("quantity", quant);
		mv.addObject("items", items);
		mv.addObject("cart", new Cart());
		return mv;

	}

	

	@RequestMapping(value = "/item/remove/*", method = RequestMethod.GET)
	public ModelAndView removeItem(HttpServletRequest request) throws Exception {

		String url = request.getRequestURI();
		long id = 0;
		id = Integer.parseInt((url.split("/")[4]).split("\\.")[0]);
		try {
			Item itemdao = itemDao.removeItem(id);
			HttpSession session = (HttpSession) request.getSession();
			User user = (User) session.getAttribute("user");
			long ident = user.getPersonID();
			List<Item> items = itemDao.list(ident);

			return new ModelAndView("viewAllItems", "items", items);
		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

	@RequestMapping(value = "/item/edit", method = RequestMethod.GET)
	public ModelAndView changeItem(HttpServletRequest request) throws Exception {

		try {
			HttpSession session = (HttpSession) request.getSession();
			User user = (User) session.getAttribute("user");
			long id = user.getPersonID();
			List<Item> items = itemDao.list(id);
			return new ModelAndView("editItems", "items", items);

		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

        
        @RequestMapping(value = "/item/itemlist", method = RequestMethod.GET)
	public ModelAndView listItems(HttpServletRequest request) throws Exception {

		try {
			HttpSession session = (HttpSession) request.getSession();
			User user = (User) session.getAttribute("user");
			long id = user.getPersonID();
			List<Item> items = itemDao.list(id);
			return new ModelAndView("viewAllItems", "items", items);

		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}

	}
        
        
	@RequestMapping(value = "/item/update/*", method = RequestMethod.POST)
	public ModelAndView updateItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getRequestURI();
		long id = 0;
		id = Integer.parseInt((url.split("/")[4]).split("\\.")[0]);
		try {

                        
                    Float float1 = Float.parseFloat(request.getParameter("price"));
      
                         if((float1<0) || (float1==0.0)){  
                         HttpSession session = (HttpSession) request.getSession();
			User user = (User) session.getAttribute("user");
			id = user.getPersonID();
			List<Item> items = itemDao.list(id);
                        
			return new ModelAndView("editItems", "items", items);
                           
                            }
                            
                        else{
                        Map<String, String> item = new HashMap<>();
			item.put("title", request.getParameter("title"));
			item.put("description", request.getParameter("description"));
			item.put("price", request.getParameter("price"));
			Item itemdao = itemDao.updateItem(id, item);
			HttpSession session = (HttpSession) request.getSession();
			User user = (User) session.getAttribute("user");
			long ident = user.getPersonID();
			List<Item> items = itemDao.list(ident);

			return new ModelAndView("viewAllItems", "items", items);
                            }

		} catch (ItemException e) {
			System.out.println(e.getMessage());
			return new ModelAndView("error", "errorMessage", "error while login");
		}
	}

}