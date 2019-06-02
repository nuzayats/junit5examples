package junit5examples;

import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// This also works if you want to run this manually
// ./mvnw docker:start -Ddocker.follow=true
class EmployeeDaoIT {

    @Test
    void name() throws SQLException {
        EmployeeDao sut = new EmployeeDao(() -> {
            try {
                return DriverManager.getConnection("jdbc:mysql://localhost:13306/testdb", "root", "my-secret-pw");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        Optional<Employee> actual = sut.find(1);

        assertThat(actual).isPresent();
        //noinspection OptionalGetWithoutIsPresent
        Employee employee = actual.get();
        assertThat(employee.getName()).isEqualTo("Jane Doe");
        assertThat(employee.getHiredAt()).isEqualTo(LocalDateTime.of(2019, 4, 1, 9, 0, 0));
    }
}
