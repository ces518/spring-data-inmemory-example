package me.june.springdatainmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 2019-05-27
 * Time: 21:31
 **/
@Component
public class H2Runner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            String url = connection.getMetaData().getURL();
            String userName = connection.getMetaData().getUserName();
            System.out.println("url = " + url);
            System.out.println("username = " + userName);


            Statement statement = connection.createStatement();
            String sql = "create table user (id int not null primary key, name varchar(255) not null)";
            statement.executeUpdate(sql);

            jdbcTemplate.execute("insert into user values(1,'jjune')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
