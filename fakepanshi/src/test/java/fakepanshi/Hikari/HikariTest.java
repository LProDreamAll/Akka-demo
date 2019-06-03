package fakepanshi.Hikari;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 * Copyright (C), 2019-2019
 * FileName: HikariTest
 * Author:   s·D·bs
 * Date:     2019/6/3 10:38
 * Description:
 * Motto: 0.45%
 */
@Slf4j
public class HikariTest {

    /**
     * @return void
     * @Author s·D·bs
     * @Description //本地简单测试
     * @Date 11:04 2019/6/3
     * @Param []
     */

    @Test
    public void test_Hikari() throws Exception {

        // 数据库连接池配置
        HikariConfig config = new HikariConfig();
        config.setMinimumIdle(1);
        config.setMaximumPoolSize(2);
        config.setConnectionTestQuery("SELECT 1");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/persontest?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true");
        config.setUsername("root");
        config.setPassword("123456");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
// 创建数据源
        DataSource ds = new HikariDataSource(config);
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 获取数据库连接
            conn = ds.getConnection();
            // 创建 Statement
            stmt = conn.createStatement();
            // 执行 SQL
            rs = stmt.executeQuery("select * from personinfo limit 10");
            // 获取结果
            while (rs.next()) {
                int id = rs.getInt(1);
                String userName = rs.getString(2);
                Timestamp create_time = rs.getTimestamp(3);
                log.info("person id :[{}],userName :[{}],createTime :[{}]", id, userName, create_time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭 ResultSet
            close(rs);
            // 关闭 Statement
            close(stmt);
            // 关闭 Connection
            close(conn);
        }
    }

    private void close(AutoCloseable rs) throws Exception {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
