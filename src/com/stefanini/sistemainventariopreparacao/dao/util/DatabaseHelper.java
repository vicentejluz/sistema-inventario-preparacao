package com.stefanini.sistemainventariopreparacao.dao.util;

import com.stefanini.sistemainventariopreparacao.db.ConnectionFactory;
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

    public static void delete(final String sql, ConnectionFactory connectionFactory, long id) {
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);

            int rowsAffected = ps.executeUpdate();
            if (!(rowsAffected > 0))
                throw new DeleteFailedException("Error deleting");

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
                throw new UpdateFailedException("Error updating");

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

    public static void update(final String sql, ConnectionFactory connectionFactory, long id, String name, long categoryId) {
        try (Connection connection = connectionFactory.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setLong(2, categoryId);
            ps.setLong(3, id);

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
