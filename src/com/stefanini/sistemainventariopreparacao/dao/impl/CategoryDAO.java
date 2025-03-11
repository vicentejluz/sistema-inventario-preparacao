package com.stefanini.sistemainventariopreparacao.dao.impl;

import com.stefanini.sistemainventariopreparacao.dao.interf.DAO;
import com.stefanini.sistemainventariopreparacao.dao.util.DatabaseHelper;
import com.stefanini.sistemainventariopreparacao.db.ConnectionFactory;
import com.stefanini.sistemainventariopreparacao.domain.Category;
import com.stefanini.sistemainventariopreparacao.exception.DatabaseSQLException;
import com.stefanini.sistemainventariopreparacao.exception.NotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements DAO<Category> {
    private final ConnectionFactory connectionFactory;

    public CategoryDAO() {
        this.connectionFactory = new ConnectionFactory();
    }

    @Override
    public void create(Category category) {
        final String sql = "INSERT INTO tb_category (name) VALUES (?);";
        DatabaseHelper.create(sql, connectionFactory, category.getName());
    }

    @Override
    public void delete(long id) {
        final String sql = "DELETE FROM tb_category WHERE id = ?;";
        DatabaseHelper.delete(sql, connectionFactory, id);
    }

    @Override
    public void update(Category category) {
        final String sql = "UPDATE tb_category SET name = ? WHERE id = ?;";
        DatabaseHelper.update(sql, connectionFactory, category.getId(), category.getName());
    }

    @Override
    public Category findByID(long id) {
        final String sql = "SELECT name FROM tb_category WHERE id = ?;";
        String categoryName = DatabaseHelper.findByID(sql, connectionFactory, id);
        return new Category(id, categoryName);
    }

    @Override
    public List<Category> findAll() {
        final String sql = "SELECT * FROM tb_category;";
        List<Category> categories = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                categories.add(new Category(id, name));
            }

            if (categories.isEmpty())
                throw new NotFoundException("No category found.");

            return categories;
        } catch (SQLException e) {
            throw new DatabaseSQLException("Database error occurred while listing categories.", e);
        }
    }
}
