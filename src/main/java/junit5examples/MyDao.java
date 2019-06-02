package junit5examples;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.function.Supplier;

public class MyDao {

    private final Supplier<Connection> connectionSupplier;

    public MyDao(Supplier<Connection> connectionSupplier) {
        this.connectionSupplier = connectionSupplier;
    }

    public LocalDateTime fetch() throws SQLException {
        try (Connection cn = connectionSupplier.get();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery("SELECT CURRENT_TIMESTAMP")) {
            rs.next();
            return rs.getTimestamp(1).toLocalDateTime();
        }
    }
}
