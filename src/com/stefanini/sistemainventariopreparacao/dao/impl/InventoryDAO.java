package com.stefanini.sistemainventariopreparacao.dao.impl;

import com.stefanini.sistemainventariopreparacao.dao.interf.DAO;
import com.stefanini.sistemainventariopreparacao.dao.util.DatabaseHelper;
import com.stefanini.sistemainventariopreparacao.db.ConnectionFactory;
import com.stefanini.sistemainventariopreparacao.domain.Inventory;
import com.stefanini.sistemainventariopreparacao.exception.DatabaseSQLException;
import com.stefanini.sistemainventariopreparacao.exception.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO implements DAO<Inventory>{
    private final ConnectionFactory connectionFactory;

    public InventoryDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public void create(Inventory inventory) {
        final String sql = "INSERT INTO tb_analyst (equipment_id, in_preparation_room) VALUES (?, ?);";
        DatabaseHelper.create(sql, connectionFactory, inventory.getEquipmentID(), inventory.getInPreparationRoom());
    }

    @Override
    public void delete(long id) {
        final String sql = "DELETE FROM tb_inventory WHERE id = ?;";
        DatabaseHelper.delete(sql, connectionFactory, id);
    }

    @Override
    public void update(Inventory inventory) {
        final String sql = "UPDATE tb_analyst SET equipment_id = ?, in_preparation_room = ? WHERE id = ?;";
        DatabaseHelper.update(sql, connectionFactory, inventory.getEquipmentID(), inventory.getInPreparationRoom());
    }

    @Override
    public Inventory findByID(long id) {
        final String sql = "SELECT equipment_id, in_preparation_room FROM tb_inventory WHERE id = ?;";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (!resultSet.next())
                    throw new NotFoundException("Equipment in inventory with id " + id + " not found.");

                long equipmentID = resultSet.getLong("equipment_id");
                Boolean inPreparationRoom = resultSet.getBoolean("in_preparation_room");

                return new Inventory(id, equipmentID, inPreparationRoom);
            }
        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while finding equipment in by id.", e);
        }
    }

    @Override
    public List<Inventory> findAll() {
        final String sql = "SELECT * FROM tb_inventory;";
        List<Inventory> inventories = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                Long equipmentID = resultSet.getLong("equipment_id");
                Boolean inPreparationRoom = resultSet.getBoolean("in_preparation_room");
                inventories.add(new Inventory(id, equipmentID, inPreparationRoom));
            }

            if (inventories.isEmpty())
                throw new NotFoundException("No equipment in found.");

            return inventories;
        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while listing equipments in inventory.", e);
        }
    }
}
