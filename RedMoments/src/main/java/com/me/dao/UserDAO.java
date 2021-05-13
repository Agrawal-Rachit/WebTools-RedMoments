package com.me.dao;


import com.me.exception.UserException;
import com.me.pojo.Email;
import com.me.pojo.User;
import static com.me.dao.DAO.getSession;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;


/**
 *
 * @author rachi
 */

public class UserDAO extends DAO {
	public UserDAO() {}

	public User getUser(String username, String password) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where username = :username and password = :password");
                         q.setCacheable(true);
                        q.setString("username", username);
			q.setString("password", password);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + username, e);
		}
	}

	

	public User checkUser(String username) throws UserException {
	try {
			begin();
			System.out.println("inside userDAO");
			Query q = getSession().createQuery("from User u where u.username= :username");
                        q.setCacheable(true);
			q.setString("username", username);
			User u = (User) q.uniqueResult();
			commit();
			return u;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not obtain the username " + username + " " + e.getMessage());
		}

	}	

	public User signUpUser(User u)
	throws UserException {
		try {
			begin();
			

			Email email = new Email(u.getEmail().getEmailAddress());
			User user = new User(u.getUsername(), u.getPassword(), u.getUsertype());

			user.setFirstName(u.getFirstName());
			user.setLastName(u.getLastName());
			user.setEmail(email);
			email.setUser(user);
			if (u.getUsertype().equalsIgnoreCase("Buyer")) {
				user.setActive("true");
			} else {
				user.setActive("false");
			}
			getSession().save(user);
			commit();
			return user;

		} catch (HibernateException e) {
			rollback();
			throw new UserException("Exception while creating user: " + e.getMessage());
		}
	}


                public User get(int userId) throws UserException {
		try {
			begin();
			Query q = getSession().createQuery("from User where personID= :personID");
                       q.setCacheable(true);
			q.setInteger("personID", userId);
			User user = (User) q.uniqueResult();
			commit();
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " + userId, e);
		}
	}


	public Boolean checkEmail(String emailAddress) throws UserException {
		try {
			begin();
			System.out.println("inside userDAO");
			Query q = getSession().createQuery("from  Email e where e.emailAddress= :emailAddress");
                        q.setCacheable(true);
			q.setString("emailAddress", emailAddress);
			Email email = (Email) q.uniqueResult();
			commit();
			if (email == null) {
				return Boolean.TRUE;
			}
			return Boolean.FALSE;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not obtain the email Address " + emailAddress + " " + e.getMessage());
		}
	}

}