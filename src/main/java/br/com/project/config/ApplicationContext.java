package br.com.project.config;

import java.util.HashMap;
import java.util.Map;

import br.com.project.dao.LoginDao;
import br.com.project.service.LoginService;
public class ApplicationContext {

    private final Map<Class<?>, Object> beans = new HashMap<>();

    private static final ApplicationContext INSTANCE =
            new ApplicationContext();

    public static ApplicationContext getInstance() {
        return INSTANCE;
    }

    private ApplicationContext() {
        registerBeans();
    }

    private void registerBeans() {

        LoginDao loginDao = new LoginDao();

        LoginService loginService = new LoginService(loginDao);

        beans.put(LoginDao.class, loginDao);
        beans.put(LoginService.class, loginService);
    }

    public <T> T getBean(Class<T> type) {
        Object bean = beans.get(type);

        if (bean == null) {
            throw new RuntimeException("No bean registered for: " + type);
        }

        return type.cast(bean);
    }
}