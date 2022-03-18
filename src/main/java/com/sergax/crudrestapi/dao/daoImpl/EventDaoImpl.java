package com.sergax.crudrestapi.dao.daoImpl;

import com.sergax.crudrestapi.dao.EventDao;
import com.sergax.crudrestapi.model.Event;
import com.sergax.crudrestapi.model.User;
import com.sergax.crudrestapi.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EventDaoImpl implements EventDao {
    private Transaction transaction = null;
    private Event event = null;
    private UserDaoImpl userDao = new UserDaoImpl();

    @Override
    public Event getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Event WHERE id=:id");
            query.setParameter("id", id);
            List eventList = query.getResultList();
            event = (Event) eventList.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return event;
    }

    @Override
    public List<Event> getAll() {
        List<Event> eventList = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            eventList = session.createSQLQuery("SELECT event_id, event_name, file_name FROM event " +
//                    "LEFT JOIN file USING(file_id)").getResultList();
            transaction = session.beginTransaction();
            eventList = session.createQuery("FROM Event").getResultList();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return eventList;
    }

    public List getUsersEvent(User user) {
        List events = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession();) {
            Query query = session.createQuery("FROM Event WHERE user =: user");
            query.setParameter("user", user);
            events = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public void create(Event event) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(event);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Event event) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(event);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Event event = session.get(Event.class, id);
            if (event != null) {
                session.delete(event);
                System.out.println("Event is deleted");
            } else {
                System.err.println("Event doesn't exist");
            }
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
}
