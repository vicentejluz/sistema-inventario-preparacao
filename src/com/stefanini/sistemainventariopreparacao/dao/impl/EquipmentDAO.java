package com.stefanini.sistemainventariopreparacao.dao.impl;

import com.stefanini.sistemainventariopreparacao.dao.interf.DAO;
import com.stefanini.sistemainventariopreparacao.dao.util.DatabaseHelper;
import com.stefanini.sistemainventariopreparacao.db.ConnectionFactory;
import com.stefanini.sistemainventariopreparacao.domain.Equipment;
import com.stefanini.sistemainventariopreparacao.exception.DatabaseSQLException;
import com.stefanini.sistemainventariopreparacao.exception.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentDAO implements DAO<Equipment> {
    private final ConnectionFactory connectionFactory;

    public EquipmentDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public void create(Equipment equipment) {
        final String sql = "INSERT INTO tb_equipment (serial_number, equipment_model_id) VALUES (?, ?);";
        DatabaseHelper.create(sql, connectionFactory, equipment.getSerialNumber(), equipment.getEquipmentModelID());
    }

    @Override
    public void delete(long id) {
        final String sql = "DELETE FROM tb_equipment WHERE id = ?;";
        DatabaseHelper.delete(sql, connectionFactory, id);
    }

    @Override
    public void update(Equipment equipment) {
        final String sql = "UPDATE tb_equipment SET serial_number = ?, equipment_model_id WHERE id = ?;";
        DatabaseHelper.update(sql, connectionFactory, equipment.getId(), equipment.getSerialNumber(), equipment.getEquipmentModelID());
    }

    @Override
    public Equipment findByID(long id) {
        final String sql = "SELECT serial_number, equipment_model_id FROM tb_equipment WHERE id = ?;";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (!resultSet.next())
                    throw new NotFoundException("Equipment with id " + id + " not found.");

                String serial_number = resultSet.getString("serial_number");
                long equipmentModelID = resultSet.getLong("equipment_model_id");

                return new Equipment(id, serial_number, equipmentModelID);
            }
        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while finding equipment by id.", e);
        }
    }

    @Override
    public List<Equipment> findAll() {
        final String sql = "SELECT * FROM tb_equipment;";
        List<Equipment> equipments = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String serial_number = resultSet.getString("serial_number");
                long equipmentModelID = resultSet.getLong("equipment_model_id");
                equipments.add(new Equipment(id, serial_number, equipmentModelID));
            }

            if (equipments.isEmpty())
                throw new NotFoundException("No equipment found.");

            return equipments;
        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while listing equipments.", e);
        }
    }
}
