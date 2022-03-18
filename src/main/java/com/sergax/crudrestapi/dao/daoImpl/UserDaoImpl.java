package com.sergax.crudrestapi.dao.daoImpl;

import com.sergax.crudrestapi.dao.UserDao;
import com.sergax.crudrestapi.model.User;
import com.sergax.crudrestapi.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private Transaction transaction = null;
    private User user = null;

    @Override
    public User getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE id=:id");
            query.setParameter("id", id);
            List userList = query.getResultList();
            user = (User) userList.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public User getByUsername(String username) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            Query query = session.createQuery("FROM User u WHERE userName = :userName");
            query.setParameter("username", username);
            List users = query.getResultList();
            if (users.isEmpty()) {
                return null;
            }
            user = (User) users.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery("FROM User").getResultList();
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return userList;
    }

    @Override
    public void create(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
//            user = session.get(User.class, id);
//            user.setUserName(name);
            session.update(user);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("User is deleted");
            } else {
                System.err.println("User doesn't exist");
            }
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

