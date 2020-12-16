package com.example.postgres.springpostgresdocker;

import com.opentable.db.postgres.embedded.EmbeddedPostgres;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.Statement;

public abstract class AbstractJPATest {

    private static EmbeddedPostgres embeddedPostgres;

    @BeforeAll
    public static void initDB() throws Exception{
        if(embeddedPostgres == null) {
            //Create an instance of embedded postgress
            embeddedPostgres = EmbeddedPostgres.builder()
                    .setPort(5433).start();

            try (Connection conn = embeddedPostgres.getPostgresDatabase().getConnection()) {
                Statement statement = conn.createStatement();
                statement.execute("CREATE DATABASE postgresdb");
            }
        }
    }
}
