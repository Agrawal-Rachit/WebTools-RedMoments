package com.me.dao;

import com.me.pojo.Cart;
import com.me.pojo.User;
import java.util.Iterator;
import com.me.exception.CartException;
import com.me.exception.CategoryException;
import com.me.pojo.Item;
import java.util.Map;
import static com.me.dao.DAO.getSession;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

/**
 *
 * @author rachi
 */


public class CartDAO extends DAO {

	public CartDAO() {

	}

	

	public Cart removeFromCart(long id) throws CartException {

		try {

			begin();
			Query q = getSession().createQuery("from Cart c where c.id= :id");
                        q.setCacheable(true);
			q.setLong("id", id);
			Cart cart = (Cart) q.uniqueResult();
			getSession().delete(cart);
			commit();
			return cart;
		} catch (HibernateException e) {
			rollback();
			throw new CartException("Could not delete Cart", e);
		}

	}

	public Cart insert(long id, User u, int quant) throws CartException {

		try {
			begin();
                        String hql = "select a.title,a.price from Item a where a.id= :id";
			System.out.println("" + id);
			Query q = getSession().createQuery(hql);
			q.setLong("id", id);
			List<Item> a = q.getResultList();
			Iterator it = a.iterator();
			Cart cart = new Cart();
			while (it.hasNext()) {
				Object[] b = (Object[]) it.next();
				cart.setUser(u);
				cart.setQuantity(quant);
				cart.setTitle(b[0].toString());
                                cart.setTotalprice(Float.parseFloat(b[1].toString()));
			}
			getSession().save(cart);
			commit();
			return cart;
		} catch (HibernateException e) {
			rollback();
			throw new CartException("Could not save the cart", e);
		}
	}

        public User update(User user) throws CategoryException {
		try {
			begin();
			getSession().update(user);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new CategoryException("Could not save the user", e);
		}
		return user;
	}
        
	public void update(Cart cart) throws CategoryException {
		try {
			begin();
			getSession().update(cart);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new CategoryException("Could not save the cart", e);
		}
	}

	

	public Cart updateQuantity(long Id, Map<String, String> quantity) throws CartException {
		try {
			begin();
			Cart cart = (Cart) getSession().load(Cart.class, Id);
			cart.setQuantity(Integer.parseInt(quantity.get("quantity")));
			getSession().update(cart);
			commit();
			return cart;
		} catch (HibernateException e) {
			rollback();
			throw new CartException("Could not update item", e);
		}
	}

	public List<Cart> list(long id) throws CartException {

		try {
			begin();
			Query q = getSession().createQuery("from Cart c where c.user.personID = :id");
                        q.setCacheable(true);
			q.setLong("id", id);
			List<Cart> c = q.list();
			commit();
			return c;
		} catch (HibernateException e) {
			rollback();
			throw new CartException("Could not exists item", e);
		}
	}
        
            public void delete(Cart cart)
	throws CartException {
		try {
			begin();
			getSession().delete(cart);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new CartException("Could not delete cart", e);
		}
	}
	public Cart updateCart(Cart cart) throws CategoryException {
		try {
			begin();
			getSession().update(cart);
			commit();
			return cart;
		} catch (HibernateException e) {
			rollback();
			throw new CategoryException("Could not save the cart", e);
		}
	}

}