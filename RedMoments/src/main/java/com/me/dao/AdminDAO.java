/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.dao;


import com.me.pojo.User;
import java.util.List;
import java.util.Map;
import org.hibernate.query.Query;
import static com.me.dao.DAO.getSession;
import com.me.exception.UserException;
import org.hibernate.HibernateException;
import com.me.pojo.Admin;

/**
 *
 * @author rachi
 */


public class AdminDAO extends DAO {

    
    
	public AdminDAO() {}

	
        
        
        
        
        public User deleteUser(long id) throws UserException {

		try {

			begin();
			Query q = getSession().createQuery("from User u where u.personID= :id");
                        q.setCacheable(true);
			q.setLong("id", id);
			User a = (User) q.uniqueResult();
			getSession().delete(a);
			commit();
			return a;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not delete user", e);
		}

	}
        

	public List<User> list() throws UserException {

		try {
			begin();
			Query q = getSession().createQuery("from User u where u.active = 'true'");
                        q.setCacheable(true);
			List<User> users = q.list();
			commit();
			return users;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not find User", e);
		}

	}
        
        
        
        
        public Admin get(String username, String password) throws UserException {
		try {
			begin();

			System.out.println("inside AdminDAO");
			Query q1 = getSession().createQuery("from Admin a where a.username = :username");
                        q1.setCacheable(true);
			q1.setString("username", "admin");
			Admin u = (Admin) q1.uniqueResult();
			if (u == null) {
				Admin admin = new Admin();
				admin.setIsactive("true");
				admin.setPassword("admin");
				admin.setUsername("admin");
				admin.setUsertype("admin");
				admin.setAdminID(1);
				getSession().save(admin);
			}

			Query q = getSession().createQuery("from Admin ad where ad.username = :username and ad.password = :password");
			q.setCacheable(true);
                        q.setString("username", username);
			q.setString("password", password);
			Admin admin = (Admin) q.uniqueResult();
			commit();
			return admin;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}

        
        
        
	public List<User> rejectlist() throws UserException {

		try {
			begin();
			Query q = getSession().createQuery("from User u where u.active = 'false'");
                        q.setCacheable(true);
			List<User> users = q.list();
			commit();
			return users;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not find User", e);
		}

	}

	

	public User updateUserA(long Id, Map<String, String> status) throws UserException {
		try {
			begin();
			User u = (User) getSession().load(User.class, Id);
			u.setActive(status.get("active"));
			getSession().update(u);
			commit();
			return u;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not update User Status", e);
		}
	}
        public List<User> rejectuser() throws UserException {

		try {
			begin();
			Query q = getSession().createQuery("from User u where u.active = 'Reject'");
                        q.setCacheable(true);
			List<User> users = q.list();
			commit();
			return users;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not find User", e);
		}

	}

	public User updateUserR(long Id, Map<String, String> status) throws UserException {
		try {
			begin();
			User u = (User) getSession().load(User.class, Id);
			u.setActive(status.get("active"));
			getSession().update(u);
			commit();
			return u;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not update User Status", e);
		}
	}

}