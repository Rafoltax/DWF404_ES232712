package sv.edu.udb.configuration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.EnabledIf;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EnvironmentDbConfigTest {
    @Autowired
    EnvironmentDBConfig environmentDBConfig;
    @Test
    void shouldConfigurationNotNull_When_SpringContextWork() {
        assertNotNull(environmentDBConfig);
    }
    @Test
    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'dev'}",
            loadContext = true)
    void shouldEnvironmentHasValue_When_SpringDevContextWork() {
        final String dbDriverExpected = "org.h2.Driver";
        final String dbUrlExpected = "jdbc:h2:mem:dev";
        final String dbUsernameExpected = "SA";
        final String dbPasswordExpected = "";
        assertEquals(dbDriverExpected, environmentDBConfig.getDbDriver());
        assertEquals(dbUrlExpected, environmentDBConfig.getDbUrl());
        assertEquals(dbUsernameExpected, environmentDBConfig.getDbUsername());
        assertEquals(dbPasswordExpected, environmentDBConfig.getDbPassword());
    }
    @Test
    @EnabledIf(expression = "#{environment['spring.profiles.active'] == 'prod'}",
            loadContext = true)
    void shouldEnvironmentHasValue_When_SpringProdContextWork() {
        final String dbDriverExpected = "org.h2.Driver";
        final String dbUrlExpected = "jdbc:h2:mem:prod";
        final String dbUsernameExpected = "SA";
        final String dbPasswordExpected = "";
        assertEquals(dbDriverExpected, environmentDBConfig.getDbDriver());
        assertEquals(dbUrlExpected, environmentDBConfig.getDbUrl());
        assertEquals(dbUsernameExpected, environmentDBConfig.getDbUsername());
        assertEquals(dbPasswordExpected, environmentDBConfig.getDbPassword());
    }
    @Test
    void shouldDataSourceNotNull_When_EnvironmentConfigurationsNotNull() throws
            SQLException {
        assertNotNull(environmentDBConfig.dataSource());
    }
    @Test
    void shouldGetCurrentDateFromH2Db_When_DataSourceIsNotNull() throws
            SQLException {
        final String QUERY = "SELECT CURRENT_DATE()";
        final ResultSet rs = environmentDBConfig
                .dataSource()
                .getConnection()
                .prepareStatement(QUERY)
                .executeQuery();
        rs.next();
        final LocalDate currentDate = rs.getDate(1).toLocalDate();
        assertEquals(LocalDate.now(), currentDate);
    }
}