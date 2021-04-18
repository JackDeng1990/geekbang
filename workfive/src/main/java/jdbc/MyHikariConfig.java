package jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class MyHikariConfig {
    @Bean(destroyMethod = "close")
    public DataSource dataSource() throws SQLException {
        HikariConfig config = new HikariConfig("/jdbc_config.properties");
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
}
