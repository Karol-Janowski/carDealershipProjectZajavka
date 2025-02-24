package pl.zajavka.infrastructure.configuration;

import org.hibernate.cfg.Environment;

import java.util.Map;

public class HibernateUtil {

    private static final Map<String, Object> HIBERNATE_SETTINGS = Map.ofEntries(
            Map.entry(Environment.DRIVER, "org.postgresql.Driver"),
            Map.entry(Environment.URL, "jdbc:postgresql://localhost:5432/car_dealership"),
            Map.entry(Environment.USER, "postgres"),
            Map.entry(Environment.PASS, "postgres"),
            Map.entry(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect"),
            Map.entry(Environment.CONNECTION_PROVIDER, "org.hibernate.hikaricp.internal.HikariCPConnectionProvider"),
            Map.entry(Environment.HBM2DDL_AUTO, "validate"),
            Map.entry(Environment.SHOW_SQL, true),
            Map.entry(Environment.FORMAT_SQL, false)
    );

    private static final Map<String, Object> HIKARI_CP_SETTINGS = Map.ofEntries(
            Map.entry("hibernate.hikari.connectionTimeout", "20000"),
            Map.entry("hibernate.hikari.minimumIdle", "10"),
            Map.entry("hibernate.hikari.maximumPoolSize", "20"),
            Map.entry("hibernate.hikari.idleTimeout", "300000")
    );
}
