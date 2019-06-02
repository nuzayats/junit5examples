package junit5examples;

import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

// This also works if you want to run this manually
// ./mvnw docker:start -Ddocker.follow=true
class MyDaoIT {

    @Test
    void name() throws SQLException {
        MyDao sut = new MyDao(() -> {
            try {
                return DriverManager.getConnection("jdbc:mysql://localhost:13306/testdb", "root", "my-secret-pw");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        LocalDateTime fetch = sut.fetch();

        assertThat(fetch).isNotNull();
    }
}
