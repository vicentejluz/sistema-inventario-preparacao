package com.stefanini.sistemainventariopreparacao.dao.impl;

import com.stefanini.sistemainventariopreparacao.dao.interf.DAO;
import com.stefanini.sistemainventariopreparacao.domain.Inventory;

import java.sql.SQLException;
import java.util.List;

public class InventoryDAO implements DAO<Inventory>{
    @Override
    public void create(Inventory data) {
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(Inventory data) {

    }

    @Override
    public Inventory findByID(long id) {
        return null;
    }

    @Override
    public List<Inventory> findAll() {
        return null;
    }
}
