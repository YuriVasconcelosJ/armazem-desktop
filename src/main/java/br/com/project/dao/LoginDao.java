package br.com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import br.com.project.mapper.UserMapper;
import br.com.project.model.User;
import br.com.project.util.DataBase;

public class LoginDao {

    /**
     * Reaaliza a busca na tabela usuario apartir do nome informado.
     * 
     * @param userName nome informado pelo usuário para realizar a busca
     * @return usuario contendo seus respectivos dados 
     */
    public Optional<User> findByUser(String userName) {

        final String sql = "SELECT id, username, password FROM users WHERE username = ?";

        try (
                Connection connection = DataBase.getDataSource().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, userName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {

                if (!resultSet.next()) {
                    return Optional.empty();
                }

                return Optional.of(UserMapper.toUser(resultSet));

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar usuário", e);
        }
    }
}