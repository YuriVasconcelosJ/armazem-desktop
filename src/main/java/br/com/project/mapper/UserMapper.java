package br.com.project.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.project.dto.LoginResponse;
import br.com.project.model.User;

public final class UserMapper {

    private UserMapper() {
    }

    /**
     * Realiza a conversão da entidade usuário para loginResponse.
     * 
     * @param user usuário contendo todas as suas informações.
     * @return um loginReponse contendo o nome do usuário autenticado da aplicação.
     */
    public static LoginResponse toLoginResponse(User user) {

        return LoginResponse.builder().nameUser(user.getName()).build();

    }

    /**
     * Realiza a conversão de resultSet para a entidade de objeto de domínio.
     * 
     * @param resultSet Dados vindo do resultado do banco
     * @throws SQLException caso não seja possível achar o usuário
     */
    public static User toUser(ResultSet resultSet) throws SQLException {
        return User.builder().id(resultSet.getLong("id")).name(resultSet.getString("username"))
                .password(resultSet.getString("password")).build();

    }
}
