package com.sergax.crudrestapi.service;

import com.sergax.crudrestapi.dao.FileDao;
import com.sergax.crudrestapi.dao.daoImpl.FileDaoImpl;
import com.sergax.crudrestapi.model.File;

import java.util.List;

public class FileService implements FileDao {
    private final FileDao fileDao = new FileDaoImpl();

    @Override
    public File getById(Long id) {
        return fileDao.getById(id);
    }

    @Override
    public List<File> getAll() {
        return fileDao.getAll();
    }

    @Override
    public void create(File file) {
        fileDao.create(file);
    }

    @Override
    public void update(File file) {
        fileDao.update(file);
    }

    @Override
    public void delete(Long id) {
        fileDao.delete(id);
    }
}
