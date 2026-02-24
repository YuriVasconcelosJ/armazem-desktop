package br.com.project.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

public final class DataBase {

    private DataBase(){}

    private static final HikariDataSource dataSource;

    static {
        // 1. Carregar o arquivo do Classpath (src/main/resources)
        Properties props = new Properties();
        try (InputStream is = DataBase.class.getClassLoader().getResourceAsStream("config.properties")) {
            
            if (is == null) {
                throw new RuntimeException("Arquivo config.properties não encontrado em src/main/resources");
            }
            
            props.load(is);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            if (url == null || user == null || password == null) {
                throw new RuntimeException("Configuração do banco incompleta no config.properties.");
            }

            // 2. Configura HikariCP
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

            // 3. Roda Flyway usando o PRÓPRIO dataSource do Hikari
            // Isso é melhor do que passar a URL/User de novo, pois evita abrir 
            // conexões extras desnecessárias.
            Flyway flyway = Flyway.configure()
                    .dataSource(dataSource) // Use o objeto dataSource criado acima
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