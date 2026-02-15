package br.com.project.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.flywaydb.core.Flyway;

public class DataBase {

    private static final HikariDataSource dataSource;

    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("config.properties"));

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            if (url == null || user == null || password == null) {
                throw new RuntimeException("Configuração do banco incompleta.");
            }

            // 🔹 1. Configura Hikari
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(url);
            config.setUsername(user);
            config.setPassword(password);

            config.setMaximumPoolSize(5);
            config.setMinimumIdle(1);
            config.setIdleTimeout(300000);
            config.setConnectionTimeout(5000);
            config.setMaxLifetime(1800000);

            dataSource = new HikariDataSource(config);

            // 🔹 2. Roda Flyway
            Flyway flyway = Flyway.configure()
                    .dataSource(url, user, password)
                    .locations("classpath:db/migration")
                    .load();

            flyway.migrate();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar configuração do banco", e);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
