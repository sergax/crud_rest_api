package com.sergax.crudrestapi.dao.daoImpl;

import com.sergax.crudrestapi.dao.FileDao;
import com.sergax.crudrestapi.model.Event;
import com.sergax.crudrestapi.model.File;
import com.sergax.crudrestapi.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FileDaoImpl implements FileDao {
    private EventDaoImpl eventDao;
    private Transaction transaction = null;
    private File file = null;

    @Override
    public File getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            file = session.get(File.class, id);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return file;
    }

    @Override
    public List<File> getAll() {
        List<File> eventList = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            eventList = session.createQuery("FROM File").getResultList();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return eventList;
    }

    @Override
    public void create(File file) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(file);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void update(File file) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(file);
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
            file = session.get(File.class, id);
            if (file != null) {
                session.delete(file);
                System.out.println("File is deleted");
            } else {
                System.err.println("File doesn't exist");
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
