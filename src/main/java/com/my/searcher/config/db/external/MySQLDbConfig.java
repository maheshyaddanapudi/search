package com.my.searcher.config.db.external;
import java.time.Duration;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.my.searcher.constants.Constants;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile(Constants.MYSQL)
public class MySQLDbConfig {

	@Bean(Constants.DATASOURCE)
    public DataSource dataSource(
                          @Value("${spring.datasource.username:searcher}") String datasourceUsername,
                          @Value("${spring.datasource.password:searcher}") String datasourcePassword,
                          @Value("${spring.datasource.url:jdbc:mysql://localhost:3306/searcher?createDatabaseIfNotExist=true&autoReconnect=true&verifyServerCertificate=false&useSSL=false&allowPublicKeyRetrieval=true&useMysqlMetadata=true}") String datasourceUrl,
                          @Value("${spring.datasource.driver-class-name:com.mysql.cj.jdbc.Driver}") String datasourceDriver){


		final Properties dataSourceProperties = new Properties();

		dataSourceProperties.setProperty(Constants.POOL_NAME, Constants.CACHE_NAME);
		dataSourceProperties.setProperty(Constants.MAX_LIFETIME, String.valueOf(Duration.ofMinutes(5).toMillis()));
		dataSourceProperties.setProperty(Constants.CONNECTION_INIT_SQL, Constants.CONNECTION_INIT_SQL_VALUE);
		dataSourceProperties.setProperty(Constants.DRIVER_CLASS_NAME, datasourceDriver);
		dataSourceProperties.setProperty(Constants.JDBC_URL, datasourceUrl);
		dataSourceProperties.setProperty(Constants.USERNAME, datasourceUsername);
		dataSourceProperties.setProperty(Constants.PASSWORD, datasourcePassword);
		dataSourceProperties.setProperty(Constants.MAX_POOL_SIZE, Constants._100);
		dataSourceProperties.setProperty(Constants.MIN_IDLE, Constants._2);
		dataSourceProperties.setProperty(Constants.dataSource + Constants.DOT + Constants.CACHE_PREP_STMTS,Constants.TRUE);
		dataSourceProperties.setProperty(Constants.dataSource + Constants.DOT + Constants.PREP_STMT_CACHE_SIZE, Constants._256);
		dataSourceProperties.setProperty(Constants.dataSource + Constants.DOT + Constants.PREP_STMT_CACHE_SQL_LIMIT, Constants._2048);
		dataSourceProperties.setProperty(Constants.dataSource + Constants.DOT + Constants.USER_SERVER_PREP_STMTS,Constants.TRUE);
		dataSourceProperties.setProperty(Constants.dataSource + Constants.DOT + Constants.USE_LEGACY_DATETIME_CODE,Constants.FALSE);
		dataSourceProperties.setProperty(Constants.dataSource + Constants.DOT + Constants.SERVER_TIMEZONE,Constants.UTC);
		dataSourceProperties.setProperty(Constants.dataSource + Constants.DOT + Constants.CONNECTION_COLLATION,Constants.utf8mb4_unicode_ci);
		dataSourceProperties.setProperty(Constants.dataSource + Constants.DOT + Constants.USE_SSL,Constants.FALSE);
		dataSourceProperties.setProperty(Constants.dataSource + Constants.DOT + Constants.AUTO_RECONNECT,Constants.TRUE);

		final HikariConfig hikariConfig = new HikariConfig(dataSourceProperties);

		final HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		
		return hikariDataSource;
    }
}