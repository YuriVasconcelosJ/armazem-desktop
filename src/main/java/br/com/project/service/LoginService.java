package br.com.project.service;

import br.com.project.dao.LoginDao;
import br.com.project.dto.LoginDto;
import br.com.project.dto.LoginResponse;
import br.com.project.exception.LoginException;
import br.com.project.mapper.UserMapper;
import br.com.project.model.User;
import br.com.project.util.PasswordUtils;

public class LoginService {

    private final LoginDao loginDao;

    public LoginService(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    /**
     * Realiza a autenticação do usuário com base nas credenciais informadas.
     * 
     * @param loginDto dados de login fornecidos pelo usuário
     * @return objeto contendo as informações do usuário autenticado
     * @throws LoginException caso as credenciais sejam inválidas
     */
    public LoginResponse authentication(LoginDto loginDto) {

        User user = loginDao.findByUser(loginDto.user()).orElseThrow(() -> new LoginException("Usuário ou senha inválidos"));

        boolean result = PasswordUtils.checkPassword(loginDto.password(), user.getPassword());

        if (!result) {
            throw new LoginException("Usuário ou senha inválidos");
        }

        return UserMapper.toLoginResponse(user);

    }

}
