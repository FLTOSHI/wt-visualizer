package prs.fltoshi.infrastructure;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

public class DbFactory {
    private static HikariDataSource dataSource;

    public static DSLContext create() {
        if (dataSource == null) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/wt_db");
            config.setUsername("postgres");
            config.setPassword("root");
            dataSource = new HikariDataSource(config);
        }
        return DSL.using(dataSource, SQLDialect.POSTGRES);
    }
}