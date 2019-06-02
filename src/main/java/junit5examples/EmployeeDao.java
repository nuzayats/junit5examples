package junit5examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.function.Supplier;

public class EmployeeDao {

    private final Supplier<Connection> connectionSupplier;

    EmployeeDao(Supplier<Connection> connectionSupplier) {
        this.connectionSupplier = connectionSupplier;
    }

    Optional<Employee> find(long id) throws SQLException {
        long id1;
        String name;
        Timestamp hiredAt;

        try (Connection cn = connectionSupplier.get();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM employees WHERE id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return Optional.empty();
                }

                id1 = rs.getLong("id");
                name = rs.getString("name");
                hiredAt = rs.getTimestamp("hired_at");
            }
        }

        return Optional.of(new Employee() {

            @Override
            public long getId() {
                return id1;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public LocalDateTime getHiredAt() {
                return LocalDateTime.ofInstant(hiredAt.toInstant(), ZoneOffset.UTC);
            }
        });
    }
}

