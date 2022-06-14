package springdb1.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static springdb1.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    //단순 커넥션 획득
    //java.sql.DriverManager 를 이용한 커넥션 획득
    @Test
    void driverManager() throws SQLException {
        Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection connection2 = DriverManager.getConnection(URL, USERNAME, PASSWORD); //호출 시 매번 접속정보 필요

        log.info("connection={}, class={}", connection1, connection1.getClass());
        log.info("connection={}, class={}", connection2, connection2.getClass());
    }

    //org.springframework.jdbc.datasource.DriverManagerDataSource 를 이용한 커넥션 획득
    // DataSource 인터페이스를 상속받고 있음
    @Test
    void dataSourceDriverManager() throws SQLException {
        //DriverManagerDataSource: 항상 새로운 커넥션 획득
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD); //첫 세팅에만 접속정보 필요
        useDataSource(dataSource);
    }

    //커넥션 풀 커넥션 획득
    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //커넥션 풀링: Hikari - spring default
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");

        useDataSource(dataSource);

        //커넥션을 연결하는 작업은 tcp통신이 필요해서 상대적으로 오래 걸리는 작업이기 때문에
        // 별도의 쓰레드로 커넥션 풀에 채워야 애플리케이션 실행시간에 영향을 주지 않는다
        Thread.sleep(1000);
    }

    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection connection1 = dataSource.getConnection();//모두 사용중이라면 기다린다
        Connection connection2 = dataSource.getConnection();
        log.info("connection={}, class={}", connection1, connection1.getClass());
        log.info("connection={}, class={}", connection2, connection2.getClass());
    }
}
