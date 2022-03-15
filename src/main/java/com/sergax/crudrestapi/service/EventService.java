package com.sergax.crudrestapi.service;

import com.sergax.crudrestapi.dao.EventDao;
import com.sergax.crudrestapi.dao.daoImpl.EventDaoImpl;
import com.sergax.crudrestapi.model.Event;

import java.util.List;

public class EventService implements EventDao {
    private final EventDao eventDao = new EventDaoImpl();

    @Override
    public Event getById(Long id) {
        return eventDao.getById(id);
    }

    @Override
    public List<Event> getAll() {
        return eventDao.getAll();
    }

    @Override
    public void create(Event event) {
        eventDao.create(event);
    }

    @Override
    public void update(Event event) {
        eventDao.update(event);
    }
    
    @Override
    public void delete(Long id) {
        eventDao.delete(id);
    }
}
