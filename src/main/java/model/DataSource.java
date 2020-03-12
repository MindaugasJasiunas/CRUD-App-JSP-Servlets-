package model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        URL url= DataSource.class.getResource("/env.properties");
        Path path= Paths.get(url.getPath());
        System.out.println(path.toAbsolutePath());


        try (InputStream input = new FileInputStream( String.valueOf(path.toAbsolutePath())) ) {
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            //set properties
            config.setJdbcUrl( prop.getProperty("db.config.jdbcUrl")+prop.getProperty("db.config.dbName") );
            config.setUsername( prop.getProperty("db.config.username") );
            config.setPassword( prop.getProperty("db.config.password") );
            config.addDataSourceProperty( "cachePrepStmts" , prop.getProperty("hikari.config.cachePrepStmts") );
            config.addDataSourceProperty( "prepStmtCacheSize" , prop.getProperty("hikari.config.prepStmtCacheSize") );
            config.addDataSourceProperty( "prepStmtCacheSqlLimit" , prop.getProperty("hikari.config.prepStmtCacheSqlLimit") );
            ds = new HikariDataSource( config );

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private DataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}