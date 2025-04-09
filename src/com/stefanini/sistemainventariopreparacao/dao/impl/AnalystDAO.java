package com.stefanini.sistemainventariopreparacao.dao.impl;

import com.stefanini.sistemainventariopreparacao.dao.interf.DAO;
import com.stefanini.sistemainventariopreparacao.dao.util.DatabaseHelper;
import com.stefanini.sistemainventariopreparacao.db.ConnectionFactory;
import com.stefanini.sistemainventariopreparacao.domain.Analyst;
import com.stefanini.sistemainventariopreparacao.exception.DatabaseSQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class AnalystDAO implements DAO<Analyst> {
    private final ConnectionFactory connectionFactory;

    public AnalystDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public void create(Analyst analyst) {
        final String sql = "INSERT INTO tb_analyst (name) VALUES (?);";
        DatabaseHelper.create(sql, connectionFactory, analyst.getName());
    }

    @Override
    public void delete(long id) {
        final String sql = "UPDATE tb_analyst SET active = 0 WHERE id = ?;";
        DatabaseHelper.delete(sql, connectionFactory, id);
    }

    @Override
    public void update(Analyst analyst) {
        final String sql = "UPDATE tb_analyst SET name = ?, active = 1 WHERE id = ?;";
        DatabaseHelper.update(sql, connectionFactory, analyst.getId(), analyst.getName());
    }

    @Override
    public Analyst findByID(long id) {
        final String sql = "SELECT name FROM tb_analyst WHERE id = ?;";
        String analystName = DatabaseHelper.findByID(sql, connectionFactory, id);
        return new Analyst(id, analystName);
    }

    @Override
    public List<Analyst> findAll() {
        final String sql = "SELECT * FROM tb_analyst;";
        List<Analyst> analysts = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                Boolean active = resultSet.getBoolean("active");
                analysts.add(new Analyst(id, name, active));
            }

            return analysts;
        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while listing analysts.", e);
        }
    }
}
