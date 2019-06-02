package junit5examples;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

// This also works if you want to run this manually
// ./mvnw docker:start -Ddocker.follow=true
class EmployeeDaoIT {

    @Test
    void name() throws SQLException {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:13306/testdb");
        ds.setUsername("root");
        ds.setPassword("my-secret-pw");
        EmployeeDao sut = new EmployeeDao(ds);

        Optional<Employee> actual = sut.find(1);

        assertThat(actual).isPresent();
        //noinspection OptionalGetWithoutIsPresent
        Employee employee = actual.get();
        assertThat(employee.getId()).isEqualTo(1);
        assertThat(employee.getName()).isEqualTo("Jane Doe");
        assertThat(employee.getHiredAt()).isEqualTo(LocalDateTime.of(2019, 4, 1, 9, 0, 0));
    }
}
