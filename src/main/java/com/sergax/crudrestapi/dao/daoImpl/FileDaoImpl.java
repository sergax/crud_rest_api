package com.sergax.crudrestapi.dao.daoImpl;

import com.sergax.crudrestapi.dao.FileDao;
import com.sergax.crudrestapi.model.File;
import com.sergax.crudrestapi.model.User;
import com.sergax.crudrestapi.utils.HibernateUtil;
import com.sergax.crudrestapi.utils.JDBCUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileDaoImpl implements FileDao {
    private final static String GET_ALL_FILES_BY_USER = "SELECT file_id, file_name " +
            "FROM file " +
            "LEFT JOIN event USING(file_id) " +
            "LEFT JOIN user USING(user_id) " +
            "WHERE user_id = ?";
    private EventDaoImpl eventDao;
    private Transaction transaction = null;
    private File file = null;

    @Override
    public File getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM File WHERE id=:id");
            query.setParameter("id", id);
            List fileList = query.getResultList();
            file = (File) fileList.get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return file;
    }

    @Override
    public List<File> getAll() {
        List<File> fileList = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            fileList = session.createSQLQuery("FROM Files").getResultList();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return fileList;
    }

    public List<File> getAllByID() {
        List<File> fileList = new ArrayList<>();
        User user = new User();

        try (PreparedStatement preparedStatement = JDBCUtil.
                getConnection().
                prepareStatement(GET_ALL_FILES_BY_USER)) {
            preparedStatement.setLong(1, user.getUser_id());
            System.out.println(preparedStatement);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                File newFile = new File();
                newFile.setFile_id(result.getLong("file_id"));
                newFile.setFileName(result.getString("file_name"));
                fileList.add(newFile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fileList;
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
