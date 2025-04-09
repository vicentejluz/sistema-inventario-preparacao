package com.stefanini.sistemainventariopreparacao.dao.util;

import com.stefanini.sistemainventariopreparacao.db.ConnectionFactory;
import com.stefanini.sistemainventariopreparacao.domain.MovementType;
import com.stefanini.sistemainventariopreparacao.exception.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {
    public static void create(final String sql, ConnectionFactory connectionFactory, String name) {
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);

            int rowsAffected = ps.executeUpdate();
            if (!(rowsAffected > 0))
                throw new InsertFailedException("Error inserting data");

        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while inserting data.", e);
        }
    }

    public static void create(final String sql, ConnectionFactory connectionFactory, String name, long id) {
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setLong(2, id);

            int rowsAffected = ps.executeUpdate();
            if (!(rowsAffected > 0))
                throw new InsertFailedException("Error inserting data");

        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while inserting data.", e);
        }
    }

    public static void create(final String sql, ConnectionFactory connectionFactory, long equipmentID, boolean inPreparationRoom) {
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, equipmentID);
            ps.setBoolean(2, inPreparationRoom);

            int rowsAffected = ps.executeUpdate();
            if (!(rowsAffected > 0))
                throw new InsertFailedException("Error inserting data");

        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while inserting data.", e);
        }
    }

    public static void create(String sql, ConnectionFactory connectionFactory, Long analystID, String date, String time, String serialNumber, Long equipmentModelID, MovementType movementType) {
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, analystID);
            ps.setString(2, date);
            ps.setString(3, time);
            ps.setString(4, serialNumber);
            ps.setLong(5, equipmentModelID);
            ps.setString(6, movementType.name());

            int rowsAffected = ps.executeUpdate();
            if (!(rowsAffected > 0))
                throw new InsertFailedException("Error inserting data");

        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while inserting data.", e);
        }

    }

    public static void delete(final String sql, ConnectionFactory connectionFactory, long id) {
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();
            if (!(rowsAffected > 0))
                throw new DeleteFailedException("Failed to delete the record. No rows were affected. Please check if the ID is correct.");

        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while deleting.", e);
        }
    }

    public static void update(final String sql, ConnectionFactory connectionFactory, long id, String name) {
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setLong(2, id);

            int rowsAffected = ps.executeUpdate();
            if (!(rowsAffected > 0))
                throw new UpdateFailedException("Failed to update the record. No rows were affected. Please check if the ID is correct.");

        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while updating.", e);
        }
    }

    public static void update(final String sql, ConnectionFactory connectionFactory, long equipmentID, boolean inPreparationRoom) {
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setLong(1, equipmentID);
            ps.setBoolean(2, inPreparationRoom);

            int rowsAffected = ps.executeUpdate();
            if (!(rowsAffected > 0))
                throw new UpdateFailedException("Error updating");

        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while updating.", e);
        }
    }

    public static void update(final String sql, ConnectionFactory connectionFactory, long id, String name, long genericId) {
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setLong(2, genericId);
            ps.setLong(3, id);

            int rowsAffected = ps.executeUpdate();
            if (!(rowsAffected > 0))
                throw new UpdateFailedException("Error updating");

        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while updating.", e);
        }
    }

    public static void update(String sql, ConnectionFactory connectionFactory, Long id, Long analystID, String date, String time, String serialNumber, Long equipmentModelID, MovementType movementType) {
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, analystID);
            ps.setString(2, date);
            ps.setString(3, time);
            ps.setString(4, serialNumber);
            ps.setLong(5, equipmentModelID);
            ps.setString(6, movementType.name());
            ps.setLong(7, id);

            int rowsAffected = ps.executeUpdate();
            if (!(rowsAffected > 0))
                throw new UpdateFailedException("Error updating");

        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while updating.", e);
        }
    }

    public static String findByID(String sql, ConnectionFactory connectionFactory, long id) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (!resultSet.next())
                    throw new NotFoundException("Record with id " + id + " not found.");

                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while finding record by id.", e);
        }
    }
}
