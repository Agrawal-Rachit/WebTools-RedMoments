package com.me.dao;


import com.me.exception.CategoryException;
import com.me.pojo.Category;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;


/**
 *
 * @author rachi
 */

public class CategoryDAO extends DAO {

    public List<Category> getAllCategory() throws CategoryException {
        try {
            begin();
            Query q = getSession().createQuery("from Category");
            q.setCacheable(true);
            List<Category> list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not list the categories", e);
        }
    }
    
    
    public Category get(String title) throws CategoryException {
        try {
            begin();
            Query q=getSession().createQuery("from Category where title= :title");
            q.setCacheable(true);
            q.setString("title",title);
            Category category=(Category)q.uniqueResult();
            commit();
            return category;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not obtain the named category " + title + " " + e.getMessage());
        }
    }


    public Category create(String title) throws CategoryException {
        try {
            begin();
            Category cat = new Category(title);
            getSession().save(cat);
            commit();
            return cat;
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Exception while creating category: " + e.getMessage());
        }
    }

    
    public void updateCategory(Category category) throws CategoryException {
        try {
            begin();
            getSession().update(category);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new CategoryException("Could not save the category", e);
        }
    }
    
    
    
}