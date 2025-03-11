package com.stefanini.sistemainventariopreparacao.dao.interf;

import java.util.List;

public interface DAO<T> {
    public void create(T data);

    public void delete(long id);

    public void update(T data);

    public T findByID(long id);

    public List<T> findAll();
}
