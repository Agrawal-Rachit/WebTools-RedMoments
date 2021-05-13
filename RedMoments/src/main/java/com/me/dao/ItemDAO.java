package com.me.dao;

import java.util.List;


import com.me.exception.ItemException;
import com.me.pojo.Item;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;


/**
 *
 * @author rachi
 */


public class ItemDAO extends DAO {

	



	public Item updateItem(long Id, Map<String, String> item) throws ItemException {
		try {
			begin();
			Item item1 = (Item) getSession().load(Item.class, Id);
			item1.setTitle(item.get("title"));
			item1.setMessage(item.get("description"));
			item1.setPrice(Float.parseFloat((item.get("price"))));
			getSession().update(item1);
			commit();
			return item1;
		} catch (HibernateException e) {
			rollback();
			throw new ItemException("Could not update item", e);
		}
	}

	public List<Item> list(long id) throws ItemException {

		try {
			begin();
			Query q = getSession().createQuery("from Item a where a.user.personID = :id");
                        q.setCacheable(true);
			q.setLong("id", id);
			List<Item> items = q.list();
			commit();
			return items;
		} catch (HibernateException e) {
			rollback();
			throw new ItemException("Could not exists item", e);
		}
	}
        
        	public Item removeItem(long id) throws ItemException {

		try {

			begin();
			Query q = getSession().createQuery("from Item a where a.id= :id");
                        q.setCacheable(true);
			q.setLong("id", id);
			Item a = (Item) q.uniqueResult();
			getSession().delete(a);
			commit();
			return a;
		} catch (HibernateException e) {
			rollback();
			throw new ItemException("Could not delete item", e);
		}

	}
                
                

	public List<Item> list() throws ItemException {

		try {
			begin();
			Query q = getSession().createQuery("from Item");
                        q.setCacheable(true);
			List<Item> items = q.list();
			commit();
			return items;
		} catch (HibernateException e) {
			rollback();
			throw new ItemException("Could not exists item", e);
		}
	}
        public Item createItem(Item item)
	throws ItemException {
		try {
			begin();
			getSession().save(item);
			commit();
			return item;
		} catch (HibernateException e) {
			rollback();
			throw new ItemException("Exception while creating item: " + e.getMessage());
		}
	}
        
        

}