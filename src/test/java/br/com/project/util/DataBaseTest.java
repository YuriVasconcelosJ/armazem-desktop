package br.com.project.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;

public class DataBaseTest {

    @Test
    void DeveCriarOBancoERetornarDataSource() throws SQLException {

        DataSource ds = DataBase.getDataSource();

        assertNotNull(ds, "O dataSource não deve ser nulo");

        try(Connection conn = ds.getConnection()) {
            assertNotNull(conn, "Conexão não deve ser nula");
            assertTrue(conn.isValid(2), "A conexão deve estar válida");
            System.out.println("Conexão com o banco de dados estabelecida com sucesso");
        }
    }
    
}
