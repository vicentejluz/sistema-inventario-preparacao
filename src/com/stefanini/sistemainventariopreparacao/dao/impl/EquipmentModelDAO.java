package com.stefanini.sistemainventariopreparacao.dao.impl;

import com.stefanini.sistemainventariopreparacao.dao.interf.DAO;
import com.stefanini.sistemainventariopreparacao.dao.util.DatabaseHelper;
import com.stefanini.sistemainventariopreparacao.db.ConnectionFactory;
import com.stefanini.sistemainventariopreparacao.domain.EquipmentModel;
import com.stefanini.sistemainventariopreparacao.exception.DatabaseSQLException;
import com.stefanini.sistemainventariopreparacao.exception.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentModelDAO implements DAO<EquipmentModel> {
    private final ConnectionFactory connectionFactory;

    public EquipmentModelDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public void create(EquipmentModel equipmentModel) {
        final String sql = "INSERT INTO tb_equipment_model (name, category_id) VALUES (?, ?);";
        DatabaseHelper.create(sql, connectionFactory, equipmentModel.getName(), equipmentModel.getCategoryId());
    }

    @Override
    public void delete(long id) {
        final String sql = "DELETE FROM tb_equipment_model WHERE id = ?;";
        DatabaseHelper.delete(sql, connectionFactory, id);
    }

    @Override
    public void update(EquipmentModel equipmentModel) {
        final String sql = "UPDATE tb_equipment_model SET name = ?, category_id = ? WHERE id = ?;";
        DatabaseHelper.update(sql, connectionFactory, equipmentModel.getId(), equipmentModel.getName(), equipmentModel.getCategoryId());
    }

    @Override
    public EquipmentModel findByID(long id) {
        final String sql = "SELECT name, category_id FROM tb_equipment_model WHERE id = ?;";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (!resultSet.next())
                    throw new NotFoundException("Model with id " + id + " not found.");

                String name = resultSet.getString("name");
                long categoryId = resultSet.getLong("category_id");

                return new EquipmentModel(id, name, categoryId);
            }
        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while finding model by id.", e);
        }
    }

    @Override
    public List<EquipmentModel> findAll() {
        final String sql = "SELECT * FROM tb_equipment_model;";
        List<EquipmentModel> equipmentModels = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                long categoryId = resultSet.getLong("category_id");
                equipmentModels.add(new EquipmentModel(id, name, categoryId));
            }

            if (equipmentModels.isEmpty())
                throw new NotFoundException("No model found.");

            return equipmentModels;
        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while listing models.", e);
        }
    }
}
