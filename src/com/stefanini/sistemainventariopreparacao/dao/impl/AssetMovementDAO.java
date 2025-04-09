package com.stefanini.sistemainventariopreparacao.dao.impl;

import com.stefanini.sistemainventariopreparacao.dao.interf.DAO;
import com.stefanini.sistemainventariopreparacao.dao.util.DatabaseHelper;
import com.stefanini.sistemainventariopreparacao.db.ConnectionFactory;
import com.stefanini.sistemainventariopreparacao.domain.AssetMovement;
import com.stefanini.sistemainventariopreparacao.domain.MovementType;
import com.stefanini.sistemainventariopreparacao.exception.DatabaseSQLException;
import com.stefanini.sistemainventariopreparacao.exception.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AssetMovementDAO implements DAO<AssetMovement> {
    private final ConnectionFactory connectionFactory;

    public AssetMovementDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public void create(AssetMovement assetMovement) {
        final String sql = "INSERT INTO tb_asset_movement (analyst_id, date, time, serial_number, equipment_model_id, movement_type) VALUES (?, ?, ?, ?, ?, ?);";
        DatabaseHelper.create(sql, connectionFactory, assetMovement.getAnalystID(), assetMovement.getDate(), assetMovement.getTime(), assetMovement.getSerialNumber(), assetMovement.getEquipmentModelID(), assetMovement.getMovementType());
    }

    @Override
    public void delete(long id) {
        final String sql = "DELETE FROM tb_asset_movement WHERE id = ?;";
        DatabaseHelper.delete(sql, connectionFactory, id);
    }

    @Override
    public void update(AssetMovement assetMovement) {
        final String sql = "UPDATE tb_asset_movement SET analyst_id = ?, date = ?, time = ?, serial_number = ?, equipment_model_id = ?, movement_type = ? WHERE id = ?;";
        DatabaseHelper.update(sql, connectionFactory, assetMovement.getId(), assetMovement.getAnalystID(), assetMovement.getDate(), assetMovement.getTime(), assetMovement.getSerialNumber(), assetMovement.getEquipmentModelID(), assetMovement.getMovementType());
    }

    @Override
    public AssetMovement findByID(long id) {
        final String sql = "SELECT analyst_id, date, time, serial_number, equipment_model_id, movement_type FROM tb_asset_movement WHERE id = ?;";
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (!resultSet.next())
                    throw new NotFoundException("Asset Movement with id " + id + " not found.");

                return databaseToAssetMovement(resultSet, id);
            }
        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while finding Asset movement by id.", e);
        }
    }

    @Override
    public List<AssetMovement> findAll() {
        final String sql = "SELECT * FROM tb_asset_movement;";
        List<AssetMovement> assetMovements = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                assetMovements.add(databaseToAssetMovement(resultSet, id));
            }

            if (assetMovements.isEmpty())
                throw new NotFoundException("No asset movement found.");

            return assetMovements;
        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while listing asset movements.", e);
        }
    }

    private AssetMovement databaseToAssetMovement(ResultSet resultSet, Long id) throws SQLException {
        Long analystID = resultSet.getLong("analyst_id");
        String date = resultSet.getString("date");
        String time = resultSet.getString("time");
        String serialNumber = resultSet.getString("serial_number");
        Long equipmentModelID = resultSet.getLong("equipment_model_id");
        MovementType movementType = MovementType.valueOf(resultSet.getString("movement_type"));

        return new AssetMovement(id, analystID, date, time, serialNumber, equipmentModelID, movementType);
    }
}
